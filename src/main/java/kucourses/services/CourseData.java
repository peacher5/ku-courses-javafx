package kucourses.services;

import com.google.gson.Gson;
import kucourses.models.Course;
import kucourses.models.CourseGroup;
import kucourses.models.PassedCourses;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class CourseData {
    private static CourseData courseData;

    private static final String courseDataPath = "/data/courses_60.json";
    private static final String userDataPath = "user.json";

    private CourseGroup[] courseGroups;

    private CourseData() {
        String courseDataJson, userDataJson;
        try {
            courseDataJson = FileUtils.readFileInside(courseDataPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Gson gson = new Gson();
        courseGroups = gson.fromJson(courseDataJson, CourseGroup[].class);

        // Init passed courses
        try {
            userDataJson = FileUtils.readFileOutside(userDataPath);
        } catch (IOException | URISyntaxException e) {
            return;
        }
        PassedCourses passedCourses = gson.fromJson(userDataJson, PassedCourses.class);
        for (String courseId : passedCourses.getPassedCoursesId())
            getById(courseId).setPassed(true);
    }

    public static CourseData getInstance() {
        if (courseData != null)
            return courseData;
        courseData = new CourseData();
        return courseData;
    }

    public Course getById(String id) {
        for (CourseGroup courseGroup : courseGroups)
            for (Course course : courseGroup.getCourses())
                if (course.getId().equals(id))
                    return course;
        throw new IllegalArgumentException(String.format("Course not found (id=%s)", id));
    }

    public CourseGroup[] getAll() {
        return courseGroups;
    }

    public Course[] getAll(int year, int semester) {
        for (CourseGroup courseGroup : courseGroups)
            if (courseGroup.getYear() == year && courseGroup.getSemester() == semester)
                return courseGroup.getCourses();
        throw new IllegalArgumentException(String.format("Courses not found (year=%d, semester=%d)", year, semester));
    }

    public void savePassedCourses() {
        ArrayList<String> passedCourseIds = new ArrayList<>();
        for (CourseGroup courseGroup : courseGroups)
            for (Course course : courseGroup.getCourses())
                if (course.isPassed())
                    passedCourseIds.add(course.getId());

        PassedCourses passedCourses = new PassedCourses("2560", passedCourseIds.toArray(new String[0]));
        try {
            FileUtils.writeJsonFile(userDataPath, passedCourses);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
