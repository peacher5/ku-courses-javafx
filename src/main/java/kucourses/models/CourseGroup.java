package kucourses.models;

public class CourseGroup {
    private final int year;
    private final int semester;
    private final Course[] courses;

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
