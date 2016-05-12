package edu.oregonstate.mist.slashcourses.core

import com.fasterxml.jackson.annotation.JsonIgnore


/**
 * Slash Course Class
 */

class SlashCourse {
    Integer crn
    String courseNum
    String courseName
    Integer slash
    String term
    Integer instructorId
    Instructor instructor
    String day
    String time
    String location
    String type
}
