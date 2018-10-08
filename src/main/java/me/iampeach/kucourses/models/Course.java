package me.iampeach.kucourses.models;

public class Course {
    private final String id;
    private final String name;
    private final int credit;
    private final String description;
    private final Prerequisite prerequisite;
    private boolean isPassed;
    private onPassToggleListener listener;

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

    public void setPassed(boolean passed) {
        isPassed = passed;

        if (listener != null)
            listener.onToggle(isPassed);
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setOnPassToggleListener(onPassToggleListener listener) {
        this.listener = listener;
    }
}
