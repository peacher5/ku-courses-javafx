package kucourses.models;

import java.util.ArrayList;

public class Course {
    private final String id;
    private final String name;
    private final int credit;
    private final String description;
    private final Prerequisite prerequisite;
    private boolean isPassed;
    private ArrayList<OnPassToggleListener> listeners;

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

        if (listeners != null) {
            for (OnPassToggleListener listener : listeners)
                listener.onToggle(this);
        }
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setOnPassToggleListener(OnPassToggleListener listener) {
        if (listeners == null)
            listeners = new ArrayList<>();
        listeners.add(listener);
    }
}
