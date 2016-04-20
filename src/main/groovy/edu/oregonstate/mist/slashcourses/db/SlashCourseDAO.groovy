package edu.oregonstate.mist.slashcourses.db

import edu.oregonstate.mist.slashcourses.core.SlashCourse
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery

/**
 * Slash Course DAO
 */
public interface SlashCourseDAO {

    /** Get slash course by CRN
     *
     *  @param crn
     *  @return Slash Course object
     */
    @SqlQuery("""
              SELECT * FROM COURSE
              WHERE CRN = :crn
              """ )
    SlashCourse getByCRN(@Bind("crn") Integer crn)
}