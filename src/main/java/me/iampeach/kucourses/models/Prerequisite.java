package me.iampeach.kucourses.models;

public class Prerequisite {
    private Type type;
    private Course[] courses;

    public enum Type {
        ONE,
        ALL,
        TOGETHER
    }

    public Prerequisite(Type type, Course[] courses) {
        this.type = type;
        this.courses = courses;
    }

    public Type getType() {
        return type;
    }

    public Course[] getCourses() {
        return courses;
    }
}