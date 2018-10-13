package me.iampeach.kucourses.controllers;

import me.iampeach.kucourses.models.PassedCourses;
import me.iampeach.kucourses.utils.DatabaseUtils;

import java.util.Arrays;

public class CourseController {
    public static void setPassedCourse(String courseId) {
        PassedCourses passedCourses = DatabaseUtils.getPassedCourses();
        int length = passedCourses.passedCoursesId.length;

        String[] passedCoursesId = Arrays.copyOf(passedCourses.passedCoursesId, length + 1);
        passedCoursesId[length] = courseId;

        passedCourses.passedCoursesId = passedCoursesId;

        DatabaseUtils.writePassesCourses(passedCourses);
    }
}
