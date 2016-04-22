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
                instructor_id: r.getInt('INSTRUCTOR_ID'),
                last_name:     r.getString('LAST_NAME'),
                first_name:    r.getString('FIRST_NAME'),
        )
    }
}
