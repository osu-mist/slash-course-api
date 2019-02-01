package edu.oregonstate.mist.slashcourses.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import edu.oregonstate.mist.slashcourses.db.InstructorDAO

/**
 * Instructor HealthCheck
 */
class InstructorHealthCheck extends HealthCheck{

    private final InstructorDAO instructorDAO

    public InstructorHealthCheck (InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO
    }

    @Override
    protected Result check() throws Exception {
        try {
            instructorDAO.getByInstructorID(0)
            Result.healthy()
        } catch (Exception e) {
            Result.unhealthy(e.message)
        }
    }
}
