package me.iampeach.kucourses.models;

public class Prerequisite {
    private final Type type;
    private final String[] courses;

    public enum Type {
        ONE,
        ALL,
        TOGETHER
    }

    public Prerequisite(Type type, String[] courses) {
        this.type = type;
        this.courses = courses;
    }

    public Type getType() {
        return type;
    }

    public String[] getCourses() {
        return courses;
    }
}