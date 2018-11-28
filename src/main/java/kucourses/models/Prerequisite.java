package kucourses.models;

import com.google.gson.annotations.SerializedName;

public class Prerequisite {
    private final Type type;

    @SerializedName("courses")
    private final String[] courseIds;

    public enum Type {
        ONE,
        ALL,
        TOGETHER
    }

    public Prerequisite(Type type, String[] courseIds) {
        this.type = type;
        this.courseIds = courseIds;
    }

    public Type getType() {
        return type;
    }

    public String[] getCourseIds() {
        return courseIds;
    }
}