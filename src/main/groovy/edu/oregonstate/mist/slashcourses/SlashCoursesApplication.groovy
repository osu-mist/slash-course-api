package edu.oregonstate.mist.slashcourses

import edu.oregonstate.mist.slashcourses.db.InstructorDAO
import edu.oregonstate.mist.slashcourses.db.SlashCourseDAO
import edu.oregonstate.mist.slashcourses.resources.InstructorResource
import edu.oregonstate.mist.slashcourses.resources.SlashCourseResource
import io.dropwizard.Application
import io.dropwizard.jdbi.DBIFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.skife.jdbi.v2.DBI

/**
 * Main application class.
 */
class SlashCoursesApplication extends Application<SlashCoursesApplicationConfiguration> {
    /**
     * Initializes application bootstrap.
     *
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<SlashCoursesApplicationConfiguration> bootstrap) {}

    /**
     * Parses command-line arguments and runs the application.
     *
     * @param configuration
     * @param environment
     */
    @Override
    public void run(SlashCoursesApplicationConfiguration configuration, Environment environment) {

        final DBIFactory factory = new DBIFactory()
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "jdbi")
        final SlashCourseDAO slashCourseDAO = jdbi.onDemand(SlashCourseDAO.class)
        final InstructorDAO instructorDAO = jdbi.onDemand(InstructorDAO.class)

        environment.jersey().register(new SlashCourseResource(slashCourseDAO))
        environment.jersey().register(new InstructorResource(instructorDAO))
    }

    /**
     * Instantiates the application class with command-line arguments.
     *
     * @param arguments
     * @throws Exception
     */
    public static void main(String[] arguments) throws Exception {
        new SlashCoursesApplication().run(arguments)
    }
}
