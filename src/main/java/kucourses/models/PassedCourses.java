package kucourses.models;

import com.google.gson.annotations.SerializedName;

public class PassedCourses {

    @SerializedName("data_name")
    private String dataName;

    @SerializedName("passed")
    private String[] passedCoursesId;

    public PassedCourses(String dataName, String[] passedCoursesId) {
        this.dataName = dataName;
        this.passedCoursesId = passedCoursesId;
    }

    public String getDataName() {
        return dataName;
    }

    public String[] getPassedCoursesId() {
        return passedCoursesId;
    }
}
