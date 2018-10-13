package me.iampeach.kucourses.models;

import com.google.gson.annotations.SerializedName;

public class PassedCourses {

    @SerializedName("course_year")
    public String courseYear;

    @SerializedName("passed")
    public String[] passedCoursesId;

    public PassedCourses(String courseYear, String[] passedCoursesId) {
        this.courseYear = courseYear;
        this.passedCoursesId = passedCoursesId;
    }
}
