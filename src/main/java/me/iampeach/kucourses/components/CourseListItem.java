package me.iampeach.kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import me.iampeach.kucourses.models.Course;

import java.io.IOException;

public class CourseListItem extends AnchorPane {
    @FXML
    private Label titleLabel, subLabel;

    private SideBar sideBar;
    private boolean isBigSize;

    public CourseListItem(SideBar sideBar, Course course) {
        this(sideBar, course, true);
    }

    public CourseListItem(SideBar sideBar, Course course, boolean isBigSize) {
        this.sideBar = sideBar;
        this.isBigSize = isBigSize;

        loadFXML();
        init(course);
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(getLayoutPath()));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLayoutPath() {
        if (isBigSize)
            return "/fxml/course_list_item.fxml";
        return "/fxml/prerequisite_list_item.fxml";
    }

    private void init(Course course) {
        titleLabel.setText(course.getName());
        subLabel.setText(course.getId() + " - " + course.getCredit() + " หน่วยกิต");

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                sideBar.showCourseDetails(course);
        });
    }
}
