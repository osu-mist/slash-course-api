package edu.oregonstate.mist.slashcourses.db

import edu.oregonstate.mist.slashcourses.core.Instructor
import edu.oregonstate.mist.slashcourses.mapper.InstructorMapper
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

/**
 * Instructor DAO
 */
@RegisterMapper(InstructorMapper)
public interface InstructorDAO extends Closeable{

    @SqlQuery("""
              SELECT *
              FROM INSTRUCTOR
              WHERE INSTRUCTOR_ID = :instructorId
              """ )
    Instructor getByInstructorID(@Bind("instructorId") Integer instructorId)

    /**
     * POST a instructor object
     *
     * @param lastName
     * @param firstName
     */
    @SqlUpdate("""
               INSERT INTO INSTRUCTOR (INSTRUCTOR_ID, LAST_NAME, FIRST_NAME)
               VALUES (INSTRUCTOR_SEQ.NEXTVAL, :lastName, :firstName)
               """)
    void postInstructor(@Bind("lastName") String lastName,
                        @Bind("firstName") String firstName)

    /**
     * GET specific instructor object instance number using Oracle sequences.
     *
     * @return Updated sequence number
     */
    @SqlQuery("""
              SELECT INSTRUCTOR_SEQ.CURRVAL FROM DUAL
              """)
    Integer getLatestInstructorId()

    @Override
    void close()
}