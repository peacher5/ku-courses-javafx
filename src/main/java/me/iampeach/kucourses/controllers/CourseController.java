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

    public static void unsetPassedCourse(String courseId) {
        PassedCourses passedCourses = DatabaseUtils.getPassedCourses();
        boolean isContain = Arrays.stream(passedCourses.passedCoursesId).anyMatch(courseId::equals);

        if (!isContain) {
            // notify err here
            return;
        }

        int index = 0;
        int length = passedCourses.passedCoursesId.length;

        String[] passedCoursesId = new String[length - 1];

        for (int i = 0; i < length; ++i) {
            if (!passedCourses.passedCoursesId[i].equalsIgnoreCase(courseId)) {
                passedCoursesId[index++] = passedCourses.passedCoursesId[i];
            }
        }

        passedCourses.passedCoursesId = passedCoursesId;

        DatabaseUtils.writePassesCourses(passedCourses);
    }
}
