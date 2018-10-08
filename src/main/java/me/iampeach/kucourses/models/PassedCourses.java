package me.iampeach.kucourses.models;

import com.google.gson.annotations.SerializedName;

class PassedCourses {

    @SerializedName("course_year")
    private final String courseYear;

    @SerializedName("passed")
    private final String[] passedCoursesId;

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
