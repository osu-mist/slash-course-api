package edu.oregonstate.mist.slashcourses.core

/**
 * Error object
 */
class ErrorPOJO {

    Integer errorCode
    String errorMessage

    public ErrorPOJO(Integer errorCode, String errorMessage) {
        this.errorCode    = errorCode
        this.errorMessage = errorMessage
    }
}
