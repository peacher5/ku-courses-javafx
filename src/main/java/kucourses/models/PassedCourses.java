package kucourses.models;

import com.google.gson.annotations.SerializedName;

public class PassedCourses {

    @SerializedName("course_year")
    private String courseYear;

    @SerializedName("passed")
    private String[] passedCoursesId;

    public PassedCourses(String courseYear, String[] passedCoursesId) {
        this.courseYear = courseYear;
        this.passedCoursesId = passedCoursesId;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public String[] getPassedCoursesId() {
        return passedCoursesId;
    }
}
