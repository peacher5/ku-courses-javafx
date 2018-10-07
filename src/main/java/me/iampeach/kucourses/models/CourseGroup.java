package me.iampeach.kucourses.models;

public class CourseGroup {
    private int year;
    private int semester;
    private Course[] courses;

    public CourseGroup(int year, int semester, Course[] courses) {
        this.year = year;
        this.semester = semester;
        this.courses = courses;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public Course[] getCourses() {
        return courses;
    }
}
