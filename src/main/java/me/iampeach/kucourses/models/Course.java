package me.iampeach.kucourses.models;

public class Course {
    private String id;
    private String name;
    private int credit;
    private String description;
    private Prerequisite prerequisite;

    public Course(String id, String name, int credit, String description, Prerequisite prerequisite) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.description = description;
        this.prerequisite = prerequisite;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public String getDescription() {
        return description;
    }

    public Prerequisite getPrerequisite() {
        return prerequisite;
    }

    public boolean hasPrerequisite() {
        return prerequisite != null;
    }
}
