package me.iampeach.kucourses.models;

class CourseGroup {
    private final int year;
    private final int semester;
    private final Course[] courses;

    public CourseGroup(int year, int semester, Course[] courses) {
        this.year = year;
        this.semester = semester;
        this.courses = courses;
    }

    int getYear() {
        return year;
    }

    int getSemester() {
        return semester;
    }

    Course[] getCourses() {
        return courses;
    }
}
