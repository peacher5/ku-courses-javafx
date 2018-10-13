package me.iampeach.kucourses.controllers;

import me.iampeach.kucourses.models.PassedCourses;
import me.iampeach.kucourses.models.Prerequisite;
import me.iampeach.kucourses.utils.DatabaseUtils;
import me.iampeach.kucourses.utils.NotifyUtils;

import java.util.Arrays;

public class CourseController {
    public static void setPassedCourse(String courseId, Prerequisite prerequisite) {
        PassedCourses passedCourses = DatabaseUtils.getPassedCourses();
        int length = passedCourses.passedCoursesId.length;

        if (prerequisite.getType().equals(Prerequisite.Type.ALL)) {
            // check all is pass

        }

        if (prerequisite.getType().equals(Prerequisite.Type.ONE)) {
            // check is one is there
        }

        String[] passedCoursesId = Arrays.copyOf(passedCourses.passedCoursesId, length + 1);
        passedCoursesId[length] = courseId;

        passedCourses.passedCoursesId = passedCoursesId;

        DatabaseUtils.writePassesCourses(passedCourses);
    }

    public static void unsetPassedCourse(String courseId) {
        PassedCourses passedCourses = DatabaseUtils.getPassedCourses();
        boolean isContain = Arrays.stream(passedCourses.passedCoursesId).anyMatch(courseId::equals);

        if (!isContain) {
            NotifyUtils.error(String.format("%s does not found in database", courseId));
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
