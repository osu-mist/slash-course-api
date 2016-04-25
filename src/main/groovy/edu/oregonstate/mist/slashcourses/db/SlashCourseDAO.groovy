package edu.oregonstate.mist.slashcourses.db

import edu.oregonstate.mist.slashcourses.core.SlashCourse
import edu.oregonstate.mist.slashcourses.mapper.SlashCourseMapper
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

/**
 * Slash Course DAO
 */
@RegisterMapper(SlashCourseMapper)
public interface SlashCourseDAO {

    /** Get slash course by CRN
     *
     *  @param crn
     *  @return Slash Course object
     */
    @SqlQuery("""
              SELECT *
              FROM COURSE
              WHERE CRN = :crn
              """ )
    SlashCourse getByCRN(@Bind("crn") Integer crn)
}

//SELECT COURSE.CRN, COURSE.COURSE_NAME, COURSE.COURSE_NUM, COURSE.SLASH, COURSE.TERM, INSTRUCTOR.LAST_NAME, INSTRUCTOR.FIRST_NAME,  COURSE.DAY, COURSE.TIME, COURSE.LOCATION, COURSE.TYPE
//FROM COURSE
//INNER JOIN INSTRUCTOR
//ON COURSE.INSTRUCTOR_ID = INSTRUCTOR.INSTRUCTOR_ID
//WHERE COURSE.CRN = :crn;