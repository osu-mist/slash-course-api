package edu.oregonstate.mist.slashcourses.resources

import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.slashcourses.core.ErrorPOJO
import edu.oregonstate.mist.slashcourses.core.Sample
import edu.oregonstate.mist.api.AuthenticatedUser
import edu.oregonstate.mist.slashcourses.core.SlashCourse
import edu.oregonstate.mist.slashcourses.db.SlashCourseDAO
import io.dropwizard.auth.Auth
import io.dropwizard.jersey.params.IntParam

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.Consumes
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ResponseBuilder
import javax.ws.rs.core.MediaType

/**
 * Sample resource class.
 */
@Path('/slash_courses/')
@Produces(MediaType.APPLICATION_JSON)
class SlashCourseResource extends Resource {

    private final SlashCourseDAO slashCourseDAO

    public SlashCourseResource(SlashCourseDAO slashCourseDAO){
        this.slashCourseDAO = slashCourseDAO
    }

    /**
     * Responds to GET requests by returning a message.
     *
     * @return message
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMessage(@Auth AuthenticatedUser authenticatedUser) {
        ResponseBuilder responseBuilder = ok(new Sample().message)
        responseBuilder.build()
    }

    @GET
    @Path('{crn}')
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCRN(@PathParam('crn') IntParam crn) {

        Response returnResponse
        SlashCourse slashCourse = slashCourseDAO.getByCRN(crn.get())

        if (slashCourse == null) {
            Integer errorCode     = Response.Status.NOT_FOUND.getStatusCode()
            String errorMessage   = "Resource Not Found."
            ErrorPOJO returnError = new ErrorPOJO(errorCode: errorCode, errorMessage: errorMessage)
            returnResponse        = Response.status(Response.Status.NOT_FOUND).entity(returnError).build()
        } else {
            returnResponse = Response.ok(SlashCourse).build()
        }
            returnResponse
    }

    /**
     * Responds to POST requests by reading and returning a message.
     *
     * @param message
     * @return message
     */
//    @POST
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response postMessage(String message, @Auth AuthenticatedUser authenticatedUser) {
//        ResponseBuilder responseBuilder = ok(message)
//        responseBuilder.build()
//    }
}
