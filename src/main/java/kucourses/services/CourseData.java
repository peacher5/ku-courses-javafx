package kucourses.services;

import kucourses.models.Course;
import kucourses.models.CourseGroup;
import kucourses.models.Plan;
import kucourses.models.User;

import java.util.ArrayList;

public class CourseData {
    private static CourseData courseData;
    private Plan plan;

    private CourseData(User user) {
        plan = PlanData.getPlan(user.getPlanName());

        // Init passed courses
        for (String courseId : user.getPassedCourseIds())
            getById(courseId).setPassed(true);
    }

    public static CourseData getInstance() {
        if (courseData != null)
            return courseData;
        throw new IllegalStateException("User data must be provided for 1st time used");
    }

    public static CourseData getInstance(User user) {
        if (courseData != null)
            throw new IllegalStateException("User data is already provided");
        courseData = new CourseData(user);
        return courseData;
    }

    public Course getById(String id) {
        for (CourseGroup courseGroup : plan.getData())
            for (Course course : courseGroup.getCourses())
                if (course.getId().equals(id))
                    return course;
        throw new IllegalArgumentException(String.format("Course not found (id=%s)", id));
    }

    public CourseGroup[] getAll() {
        return plan.getData();
    }

    public Course[] getByGroup(int year, int semester) {
        for (CourseGroup courseGroup : plan.getData())
            if (courseGroup.getYear() == year && courseGroup.getSemester() == semester)
                return courseGroup.getCourses();
        throw new IllegalArgumentException(String.format("Courses not found (year=%d, semester=%d)", year, semester));
    }

    public String getPlanTitle() {
        return plan.getName();
    }

    String[] getPassedCourseIds() {
        ArrayList<String> passedCourseIds = new ArrayList<>();
        for (CourseGroup courseGroup : plan.getData())
            for (Course course : courseGroup.getCourses())
                if (course.isPassed())
                    passedCourseIds.add(course.getId());
        return passedCourseIds.toArray(new String[0]);
    }
}
