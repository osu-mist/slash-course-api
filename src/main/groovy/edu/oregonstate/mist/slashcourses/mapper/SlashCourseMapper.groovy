package edu.oregonstate.mist.slashcourses.mapper

import edu.oregonstate.mist.slashcourses.core.SlashCourse
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Slash Course Mapper
 */
public class SlashCourseMapper implements ResultSetMapper<SlashCourse> {
    @Override
    SlashCourse map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        new SlashCourse(
                crn:         r.getInt('CRN'),
                course_num:  r.getString('COURSE_NUM'),
                course_name: r.getString('COURSE_NAME'),
                slash:       r.getInt('SLASH'),
                term:        r.getString('TERM'),
                instructor:  r.getString('INSTRUCTOR'),
                day:         r.getString('DAY'),
                time:        r.getString('TIME'),
                location:    r.getString('LOCATION'),
                type:        r.getString('TYPE')
        )
    }
}
