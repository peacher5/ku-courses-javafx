package kucourses.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import kucourses.models.Course;
import kucourses.models.CourseUtils;

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

        if (course.isPassed() || CourseUtils.isAvailable(course)) {
            // Pass Button
            PassButton passButton = new PassButton(course);
            setBottom(passButton);
            setAlignment(passButton, Pos.CENTER);
            setMargin(passButton, new Insets(0, 0, 44, 0));
        } else {
            Label error = new Label("- ยังไม่ผ่านเงื่อนไข -");
            error.setId("sidebar-error");
            setBottom(error);
            setAlignment(error, Pos.CENTER);
            setMargin(error, new Insets(0, 0, 50, 0));
        }
    }

    private void showDefault() {
        Label hint = new Label("กดเลือกวิชาเพื่อดูข้อมูล");
        hint.setId("sidebar-hint");
        setCenter(hint);
    }
}
