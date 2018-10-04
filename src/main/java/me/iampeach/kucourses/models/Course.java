package me.iampeach.kucourses.models;

public class Course {
    private String id;
    private String name;
    private String description;
    private int credit;
    private Course[] prerequisites;

    public Course(String id, String name, String description, int credit, Course[] prerequisites) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credit = credit;
        this.prerequisites = prerequisites;
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

    public Course[] getPrerequisites() {
        return prerequisites;
    }
}
