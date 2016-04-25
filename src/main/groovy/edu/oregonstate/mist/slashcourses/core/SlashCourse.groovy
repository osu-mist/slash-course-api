package edu.oregonstate.mist.slashcourses.core

import com.fasterxml.jackson.annotation.JsonIgnore


/**
 * Slash Course Class
 */

class SlashCourse {
    Integer crn
    String course_num
    String course_name
    Integer slash
    String term
    @JsonIgnore Integer instructor_id
    Instructor instructor
    String day
    String time
    String location
    String type
}
