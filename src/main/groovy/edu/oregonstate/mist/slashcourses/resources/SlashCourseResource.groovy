package edu.oregonstate.mist.slashcourses.resources

import com.google.common.base.Optional
import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.slashcourses.core.Instructor
import edu.oregonstate.mist.slashcourses.core.SlashCourse
import edu.oregonstate.mist.slashcourses.db.InstructorDAO
import edu.oregonstate.mist.slashcourses.db.SlashCourseDAO

import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.MediaType

/**
 * Slash Course Resource class.
 */
@Path('/slash_courses/')
@Produces(MediaType.APPLICATION_JSON)
class SlashCourseResource extends Resource {
    private final SlashCourseDAO slashCourseDAO
    private final InstructorDAO instructorDAO

    public SlashCourseResource (SlashCourseDAO slashCourseDAO, InstructorDAO instructorDAO) {
        this.slashCourseDAO = slashCourseDAO
        this.instructorDAO  = instructorDAO
    }

    /**
     * Respond to GET requests and show all courses information.
     *
     * @return founded course objects
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SlashCourse> getAll (@QueryParam("course_num") Optional<String> courseNum,
                                     @QueryParam("term") Optional<String> term,
                                     @QueryParam("department") Optional<String> department,
                                     @QueryParam("slash") Optional<Integer> slash) {
        // get all slash courses or filtered by parameters
        List<SlashCourse> slashCourseList = slashCourseDAO.getCoursesMatch(courseNum.or(""), term.or(""), department.or(""), slash.or(1))
        for (slashCourse in slashCourseList) {
            slashCourse.instructor  = instructorDAO.getByInstructorID(slashCourse.instructorId)
        }
        slashCourseList
    }

    /**
     * Respond to GET requests and show course information according to course CRN.
     *
     * @param crn
     * @return founded course object or error message
     */
    @GET
    @Path('{crn: \\d+}')
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCRN (@PathParam('crn') Integer crn) {
        Response returnResponse
        SlashCourse slashCourse = slashCourseDAO.getByCRN(crn)
        if (slashCourse == null) {
            returnResponse = notFound().build()
        } else {
            slashCourse.instructor  = instructorDAO.getByInstructorID(slashCourse.instructorId)
            returnResponse = ok(slashCourse).build()
        }
            returnResponse
    }

    /**
     * Respond to DELETE requests and removes a course object according to course CRN.
     *
     * @param crn
     * @return response containing the result or error message
     */
    @DELETE
    @Path('{crn: \\d+}')
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByCRN (@PathParam('crn') Integer crn) {
        slashCourseDAO.deleteByCRN(crn)
        Response.ok().build()
    }

    /**
     * Respond to POST requests by creating a course object.
     *
     * @param newCourse
     * @return response containing the result or error message
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCourse (@Valid SlashCourse newCourse) {
        Response returnResponse
        try {
            List<Integer> crnList = slashCourseDAO.getAllCRN()
            if (newCourse.instructor != null && !crnList.contains(newCourse.crn)) {
                instructorDAO.postInstructor(newCourse.instructor.lastName,
                                             newCourse.instructor.firstName)
                slashCourseDAO.postCourse(newCourse.crn,
                                          newCourse.courseNum,
                                          newCourse.courseName,
                                          newCourse.slash,
                                          newCourse.term,
                                          // retrieve the instructorId just created as instructorId in the new course object
                                          instructorDAO.getLatestInstructorId().toInteger(),
                                          newCourse.day,
                                          newCourse.time,
                                          newCourse.location,
                                          newCourse.type,
                                          newCourse.instructor)
                URI createdURI = URI.create("/" + instructorDAO.getLatestInstructorId())
                returnResponse = Response.created(createdURI).build()
            } else {
                slashCourseDAO.postCourse(newCourse.crn,
                                          newCourse.courseNum,
                                          newCourse.courseName,
                                          newCourse.slash,
                                          newCourse.term,
                                          newCourse.instructorId,
                                          newCourse.day,
                                          newCourse.time,
                                          newCourse.location,
                                          newCourse.type,
                                          newCourse.instructor)
                returnResponse = Response.ok().build()
            }
        } catch (Exception e) {
            String constraintError = e.cause.toString()
            if (constraintError.contains("COURSE_PK")) {
                returnResponse = badRequest("CRN is not unique").build()
            } else {
                returnResponse = internalServerError("Internal server error").build()
            }
        }
        returnResponse
    }

    /**
     * Respond to PUT requests by updating a course object according to course CRN.
     * If the course object doesn't exist, create one with POST requests.
     *
     * @param crn
     * @param newCourse
     * @return response containing the result or error message
     */
    @PUT
    @Path('{crn: \\d+}')
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putByCRN (@PathParam('crn') Integer crn, @Valid SlashCourse newCourse) {
        Response returnResponse
        SlashCourse checkForCourseCRN = slashCourseDAO.getByCRN(crn)

        // if the course object doesn't exist, call postCourse() to create one
        if (!checkForCourseCRN) {
            postCourse(newCourse)
            URI createdURI = URI.create("/" + instructorDAO.getLatestInstructorId())
            returnResponse = Response.created(createdURI).build()
        } else {
            try {
                // courseNum, CourseName and slash must not be null
                String newCourseNum      = getNewField(newCourse.courseNum, checkForCourseCRN.courseNum, false)
                String newCourseName     = getNewField(newCourse.courseName, checkForCourseCRN.courseName, false)
                Integer newSlash         = (Integer) getNewField(newCourse.slash, checkForCourseCRN.slash, false)
                // term, instructorId, day, time, location, type, instructor is possible to be null
                String newTerm           = getNewField(newCourse.term, checkForCourseCRN.term, true)
                Integer newInstructorId  = (Integer) getNewField(newCourse.instructorId, checkForCourseCRN.instructorId, true)
                String newDay            = getNewField(newCourse.day, checkForCourseCRN.day, true)
                String newTime           = getNewField(newCourse.time, checkForCourseCRN.time, true)
                String newLocation       = getNewField(newCourse.location, checkForCourseCRN.location, true)
                String newType           = getNewField(newCourse.type, checkForCourseCRN.type, true)
                Instructor newInstructor = (Instructor) getNewField(newCourse.instructor, checkForCourseCRN.instructor, true)

                // if newInstructor isn't null, create a new instructor object and retrieve its instructor id
                if (newInstructor) {
                    instructorDAO.postInstructor(newInstructor.lastName, newInstructor.firstName)
                    newInstructorId = instructorDAO.getLatestInstructorId().toInteger()
                }
                slashCourseDAO.putByCRN(crn, newCourseNum, newCourseName, newSlash, newTerm, newInstructorId, newDay, newTime, newLocation, newType, newInstructor)
                returnResponse = Response.ok().build()
            } catch (Exception e) {
                System.out.println("Catch exception: " + e.cause)
                returnResponse = internalServerError("Internal server error").build()
            }
        }
        returnResponse
    }

    /**
     * get new field for updating purpose
     */
    static public Object getNewField (Object newCourseField, Object checkForCourseCRNField, Boolean nullable) {
        if (nullable) {
            return Optional.fromNullable(newCourseField).or(Optional.fromNullable(checkForCourseCRNField)).orNull()
        } else {
            return Optional.fromNullable(newCourseField).or(checkForCourseCRNField)
        }
    }
}
