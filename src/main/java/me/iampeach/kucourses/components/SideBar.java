package me.iampeach.kucourses.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import me.iampeach.kucourses.models.Course;

public class SideBar extends BorderPane {

    public SideBar() {
        setId("sidebar");
        showDefault();
    }

    void showCourseDetails(Course course) {
        // Course Details
        CourseDetails courseDetails = new CourseDetails(this, course);
        setCenter(courseDetails);
        setAlignment(courseDetails, Pos.TOP_LEFT);

        // Pass Button
        PassButton passButton = new PassButton(course);
        setBottom(passButton);
        setAlignment(passButton, Pos.CENTER);
        setMargin(passButton, new Insets(0, 0, 44, 0));
    }

    private void showDefault() {
        Label hint = new Label("กดเลือกวิชาเพื่อดูข้อมูล");
        hint.setId("sidebar-hint");
        setCenter(hint);
    }
}
