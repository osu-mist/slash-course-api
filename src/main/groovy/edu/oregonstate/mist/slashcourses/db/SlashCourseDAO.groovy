package edu.oregonstate.mist.slashcourses.db

import edu.oregonstate.mist.slashcourses.core.Instructor
import edu.oregonstate.mist.slashcourses.core.SlashCourse
import edu.oregonstate.mist.slashcourses.mapper.SlashCourseMapper
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

/**
 * Slash Course DAO
 */
@RegisterMapper(SlashCourseMapper)
public interface SlashCourseDAO extends Closeable {

    /** Get all slash courses by filter
     *
     *  @return a list of Slash Course object
     */
    @SqlQuery("""
              SELECT *
              FROM COURSE
              WHERE COURSE_NUM LIKE '%' ||:courseNum|| '%'
              AND TERM LIKE '%' ||:term|| '%'
              AND COURSE_NUM LIKE '%' ||:department|| '%'
              AND SLASH LIKE '%' ||:slash|| '%'
              """)
    List<SlashCourse> getCoursesMatch(@Bind("courseNum") String courseNum,
                                      @Bind("term") String term,
                                      @Bind("department") String department,
                                      @Bind("slash") Integer slash
                                      )

    /** Get course object by CRN
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

    /**
     * Delete a course object
     *
     * @param crn
     */
    @SqlUpdate("""
               DELETE FROM COURSE
               WHERE CRN = :crn
               """)
    void deleteByCRN(@Bind("crn") Integer crn)

    /**
     * POST a course object
     *
     * @param crn
     * @param courseNum
     * @param courseName
     * @param slash
     * @param term
     * @param instructorId
     * @param day
     * @param time
     * @param location
     * @param type
     */
    @SqlUpdate("""
               INSERT INTO COURSE (CRN, COURSE_NUM, COURSE_NAME, SLASH, TERM, INSTRUCTOR_ID, DAY, TIME, LOCATION, TYPE)
               VALUES (:crn, :courseNum, :courseName, :slash, :term, :instructorId, :day, :time, :location, :type)
               """)
    void postCourse(@Bind("crn") Integer crn,
                    @Bind("courseNum") String courseNum,
                    @Bind("courseName") String courseName,
                    @Bind("slash") Integer slash,
                    @Bind("term") String term,
                    @Bind("instructorId") Integer instructorId,
                    @Bind("day") String day,
                    @Bind("time") String time,
                    @Bind("location") String location,
                    @Bind("type") String type,
                    @Bind("instructor") Instructor instructor)

    @Override
    void close()
}
