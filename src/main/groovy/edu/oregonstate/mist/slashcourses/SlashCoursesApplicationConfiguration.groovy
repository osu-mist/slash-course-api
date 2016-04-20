package edu.oregonstate.mist.slashcourses

import edu.oregonstate.mist.api.Configuration
import io.dropwizard.db.DataSourceFactory

/**
 * Slash Courses Application Configuration
 */
public class SlashCoursesApplicationConfiguration extends Configuration {
    DataSourceFactory database = new DataSourceFactory()
}
