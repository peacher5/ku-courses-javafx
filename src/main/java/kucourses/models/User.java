package kucourses.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("plan_name")
    private String planName;

    @SerializedName("passed")
    private String[] passedCoursesId;

    public User(String planName, String[] passedCoursesId) {
        this.planName = planName;
        this.passedCoursesId = passedCoursesId;
    }

    public String getPlanName() {
        return planName;
    }

    public String[] getPassedCourseIds() {
        return passedCoursesId;
    }
}
