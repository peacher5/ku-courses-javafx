package kucourses.models;

public class Plan {
    private String name;
    private CourseGroup[] data;

    public Plan(String name, CourseGroup[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public CourseGroup[] getData() {
        return data;
    }
}
