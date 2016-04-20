package edu.oregonstate.mist.slashcourses

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory

/**
 * Slash Courses Application Configuration
 */
public class SlashCoursesApplicationConfiguration extends Configuration {
    DataSourceFactory database = new DataSourceFactory()
}
