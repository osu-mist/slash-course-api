package edu.oregonstate.mist.slashcourses.resources

import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.slashcourses.core.Instructor
import edu.oregonstate.mist.slashcourses.db.InstructorDAO

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Instructor Resource class.
 */
@Path('/instructor/')
@Produces(MediaType.APPLICATION_JSON)
class InstructorResource extends Resource{

    private final InstructorDAO instructorDAO

    public InstructorResource(InstructorDAO instructorDAO){
        this.instructorDAO = instructorDAO
        System.println("------------Enter----------")
    }

    @GET
    @Path('{instructor_id: \\d+}')
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByInstructorID(@PathParam('instructor_id') Integer instructor_id) {
        Response returnResponse
        Instructor instructor = instructorDAO.getByInstructorID(instructor_id)

        System.println("------------Get----------")
        if(instructor == null) {
            returnResponse = notFound()
        } else {
            returnResponse = ok(instructor).build()
        }
        returnResponse
    }
}
