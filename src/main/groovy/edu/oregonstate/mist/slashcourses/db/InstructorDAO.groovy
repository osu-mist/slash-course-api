package edu.oregonstate.mist.slashcourses.db

import edu.oregonstate.mist.slashcourses.core.Instructor
import edu.oregonstate.mist.slashcourses.mapper.InstructorMapper
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

/**
 * Instructor DAO
 */
@RegisterMapper(InstructorMapper)
public interface InstructorDAO {

    @SqlQuery("""
              SELECT *
              FROM INSTRUCTOR
              WHERE CRN = :crn
              """ )
    Instructor getByCRN(@Bind("crn") Integer crn)
}