package edu.oregonstate.mist.slashcourses.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

/**
 * Slash Course HealthCheck
 */
class SlashCourseHealthCheck extends HealthCheck {

    final Map<String, String> slashCourseConfiguration

    SlashCourseHealthCheck(Map<String, String> slashCourseConfiguration) {
        this.slashCourseConfiguration = slashCourseConfiguration
    }

    protected Result check() {
        Result.unhealthy("default health check needs to be overwritten")
    }

    protected Result checkurl(String url){
        try {
            String urlText = new URL(url).text

            if (urlText) {
                Result.healthy()
            } else {
                Result.unhealthy("Content of url: (${url}) was empty or null")
            }
        } catch (Exception e) {
            Result.unhealthy(e.message)
        }
    }
}
