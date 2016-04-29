package edu.oregonstate.mist.slashcourses.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import edu.oregonstate.mist.slashcourses.db.InstructorDAO
import edu.oregonstate.mist.slashcourses.db.SlashCourseDAO

/**
 * Slash Course HealthCheck
 */
class SlashCourseHealthCheck extends HealthCheck {

    private final SlashCourseDAO slashCourseDAO
    private final InstructorDAO instructorDAO

    public SlashCourseHealthCheck(SlashCourseDAO slashCourseDAO, InstructorDAO instructorDAO) {
        this.slashCourseDAO = slashCourseDAO
        this.instructorDAO  = instructorDAO
    }

    @Override
    protected Result check() throws Exception {
        try {
            return Result.healthy()
        } catch (Exception e) {
            Result.unhealthy(e.message)
        }
    }
}
