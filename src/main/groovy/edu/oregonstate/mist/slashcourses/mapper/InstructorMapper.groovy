package edu.oregonstate.mist.slashcourses.mapper

import edu.oregonstate.mist.slashcourses.core.Instructor
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Instructor Mapper
 */
public class InstructorMapper implements ResultSetMapper<Instructor> {
    @Override
    Instructor map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        new Instructor(
                instructorId: r.getInt('INSTRUCTOR_ID'),
                lastName:     r.getString('LAST_NAME'),
                firstName:    r.getString('FIRST_NAME'),
        )
    }
}
