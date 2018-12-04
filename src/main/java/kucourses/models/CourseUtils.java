package kucourses.models;

import kucourses.services.CourseData;

public class CourseUtils {
    public static boolean isAvailable(Course course) {
        Prerequisite prerequisite = course.getPrerequisite();
        if (prerequisite == null)
            return true;

        switch (prerequisite.getType()) {
            case ONE:
                return isAnyPassed(prerequisite.getCourseIds());
            case TOGETHER:
            case ALL:
                return isAllPassed(prerequisite.getCourseIds());
        }
        return true;
    }

    public static void setOnPassToggleListenerToAllCourses(OnPassToggleListener listener) {
        for (CourseGroup courseGroup : CourseData.getInstance().getAll())
            for (Course course : courseGroup.getCourses())
                course.setOnPassToggleListener(listener);
    }

    private static boolean isAnyPassed(String[] courseIds) {
        for (String courseId : courseIds) {
            Course preCourse = CourseData.getInstance().getById(courseId);
            if (preCourse.isPassed())
                return true;
        }
        return false;
    }

    private static boolean isAllPassed(String[] courseIds) {
        for (String courseId : courseIds) {
            Course preCourse = CourseData.getInstance().getById(courseId);
            if (!preCourse.isPassed())
                return false;
        }
        return true;
    }
}
