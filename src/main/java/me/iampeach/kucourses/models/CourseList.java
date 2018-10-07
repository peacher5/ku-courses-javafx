package me.iampeach.kucourses.models;

import com.google.gson.Gson;

public class CourseList {

    private static CourseList courseList;
    private CourseGroup[] courseGroups;

    private CourseList(String json) {
        Gson gson = new Gson();
        this.courseGroups = gson.fromJson(json, CourseGroup[].class);
    }

    public static CourseList getInstance() {
        if (courseList == null)
            throw new IllegalStateException("CourseList must have courses data provided for first time");
        return courseList;
    }

    public static CourseList getInstance(String json) {
        if (courseList != null)
            return courseList;
        courseList = new CourseList(json);
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
