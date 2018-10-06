package me.iampeach.kucourses.models;

public class Course {
    private String id;
    private String name;
    private String description;
    private int credit;
    private Prerequisite prerequisite;

    public Course(String id, String name, String description, int credit, Prerequisite prerequisite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credit = credit;
        this.prerequisite = prerequisite;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCredit() {
        return credit;
    }

    public Prerequisite getPrerequisite() {
        return prerequisite;
    }
}
