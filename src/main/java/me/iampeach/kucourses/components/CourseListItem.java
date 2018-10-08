package me.iampeach.kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import me.iampeach.kucourses.models.Course;

import java.io.IOException;

public class CourseListItem extends AnchorPane {
    @FXML
    private Label titleLabel, subLabel;

    @FXML
    private ImageView iconView;

    private final SideBar sideBar;
    private final boolean isPrerequisiteMode;

    public CourseListItem(SideBar sideBar, Course course) {
        this(sideBar, course, false);
    }

    CourseListItem(SideBar sideBar, Course course, boolean isPrerequisiteMode) {
        this.sideBar = sideBar;
        this.isPrerequisiteMode = isPrerequisiteMode;

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
        if (isPrerequisiteMode)
            return "/fxml/prerequisite_list_item.fxml";
        return "/fxml/course_list_item.fxml";
    }

    private void init(Course course) {
        titleLabel.setText(course.getName());
        subLabel.setText(course.getId() + " - " + course.getCredit() + " หน่วยกิต");

        if (!isPrerequisiteMode) {
            if (course.isPassed())
                setPassedIcon();

            course.setOnPassToggleListener(isPassed -> {
                if (isPassed)
                    setPassedIcon();
                else
                    clearIcon();
            });
        }

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                sideBar.showCourseDetails(course);
        });
    }

    private void setPassedIcon() {
        iconView.setImage(new Image("/icons/passed_icon.png"));
    }

    private void clearIcon() {
        iconView.setImage(null);
    }
}
