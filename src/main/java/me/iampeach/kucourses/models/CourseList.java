package me.iampeach.kucourses.models;

import com.google.gson.Gson;

public class CourseList {

    private static CourseList courseList;
    private final CourseGroup[] courseGroups;

    private CourseList(String courseGroupsJson, String passedCoursesJson) {
        Gson gson = new Gson();
        this.courseGroups = gson.fromJson(courseGroupsJson, CourseGroup[].class);

        if (passedCoursesJson != null) {
            PassedCourses passedCourses = gson.fromJson(passedCoursesJson, PassedCourses.class);
            for (String courseId : passedCourses.getPassedCoursesId())
                getById(courseId).setPassed(true);
        }
    }

    public static CourseList getInstance() {
        return getInstance(null, null);
    }

    public static CourseList getInstance(String courseGroupsJson, String passedCoursesJson) {
        if (courseList != null)
            return courseList;
        if (courseGroupsJson == null)
            throw new IllegalStateException("CourseList must have courses data provided for first time");
        courseList = new CourseList(courseGroupsJson, passedCoursesJson);
        return courseList;
    }

    public Course getById(String id) {
        for (CourseGroup courseGroup : courseGroups)
            for (Course course : courseGroup.getCourses())
                if (course.getId().equals(id))
                    return course;
        throw new IllegalArgumentException(String.format("Course not found (id=%s)", id));
    }

    public Course[] getAll(int year, int semester) {
        for (CourseGroup courseGroup : courseGroups)
            if (courseGroup.getYear() == year && courseGroup.getSemester() == semester)
                return courseGroup.getCourses();
        throw new IllegalArgumentException(String.format("Courses not found (year=%d, semester=%d)", year, semester));
    }
}
