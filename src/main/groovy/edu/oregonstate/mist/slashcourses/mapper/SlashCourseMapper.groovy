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
                crn:           r.getInt('CRN'),
                courseNum:     r.getString('COURSE_NUM'),
                courseName:    r.getString('COURSE_NAME'),
                slash:         r.getInt('SLASH'),
                term:          r.getString('TERM'),
                instructorId:  r.getInt('INSTRUCTOR_ID'),
                day:           r.getString('DAY'),
                time:          r.getString('TIME'),
                location:      r.getString('LOCATION'),
                type:          r.getString('TYPE')
        )
    }
}
