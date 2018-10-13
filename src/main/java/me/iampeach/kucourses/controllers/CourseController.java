package me.iampeach.kucourses.controllers;

import me.iampeach.kucourses.models.PassedCourses;
import me.iampeach.kucourses.models.Prerequisite;
import me.iampeach.kucourses.utils.DatabaseUtils;
import me.iampeach.kucourses.utils.Helper;
import me.iampeach.kucourses.utils.NotifyUtils;

import java.util.Arrays;

public class CourseController {
    public static boolean setPassedCourse(String courseId, Prerequisite prerequisite) {
        PassedCourses passedCourses = DatabaseUtils.getPassedCourses();
        int length = passedCourses.passedCoursesId.length;

        if (prerequisite != null) {
            String[] prerequisiteCourses = prerequisite.getCourses();

            if (prerequisite.getType().equals(Prerequisite.Type.ALL)) {
                if (!Helper.containAllString(passedCourses.passedCoursesId, prerequisiteCourses)) {
                    NotifyUtils.warning("you must passes all prerequisite courses before");
                    return false;
                }
            }

            if (prerequisite.getType().equals(Prerequisite.Type.ONE)) {
                boolean passes = false;
                for (int i = 0; i < prerequisiteCourses.length; ++i) {
                    if (Helper.containString(passedCourses.passedCoursesId, prerequisiteCourses[i])) {
                        passes = true;
                    }
                }
                if (!passes) {
                    NotifyUtils.warning("you must passes prerequisite courses at least one course");
                    return false;
                }
            }

        }

        String[] passedCoursesId = Arrays.copyOf(passedCourses.passedCoursesId, length + 1);
        passedCoursesId[length] = courseId;

        passedCourses.passedCoursesId = passedCoursesId;

        DatabaseUtils.writePassesCourses(passedCourses);

        return true;
    }

    public static boolean unsetPassedCourse(String courseId) {
        PassedCourses passedCourses = DatabaseUtils.getPassedCourses();

        if (!Helper.containString(passedCourses.passedCoursesId, courseId)) {
            NotifyUtils.error(String.format("%s does not found in database", courseId));
            return false;
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

        return true;
    }
}
