package edu.oregonstate.mist.slashcourses.resources

import edu.oregonstate.mist.slashcourses.core.Instructor
import org.junit.Test
import static org.junit.Assert.*

class SlashCourseResourceTest {
    @Test
    public void testGetNewField() {
        Instructor testInstructor = new Instructor()
        // not nullable fields testing
        assertTrue(SlashCourseResource.getNewField("CS544", "CS589", false) == "CS544")  // String testing
        assertTrue(SlashCourseResource.getNewField(null, "CS589", false) == "CS589")
        assertTrue(SlashCourseResource.getNewField(0, 1, false) == 0)  // Integer testing
        assertTrue(SlashCourseResource.getNewField(null, 1, false) == 1)
        // nullable fields testing
        assertTrue(SlashCourseResource.getNewField("TR", "MWF", true) == "TR")  // String testing
        assertTrue(SlashCourseResource.getNewField(null, "MWF", true) == "MWF")
        assertTrue(SlashCourseResource.getNewField(10, 11, true) == 10)  // Integer testing
        assertTrue(SlashCourseResource.getNewField(null, 11, true) == 11)
        assertTrue(SlashCourseResource.getNewField(testInstructor, null, true) == testInstructor)  // Instructor object testing
        assertTrue(SlashCourseResource.getNewField(null, testInstructor, true) == testInstructor)
        assertTrue(SlashCourseResource.getNewField(null, null, true) == null)  // null testing
    }
}
