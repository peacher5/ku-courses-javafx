package me.iampeach.kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import me.iampeach.kucourses.models.Course;

import java.io.IOException;

public class CourseDetails extends VBox {

    @FXML
    private Label nameLabel, subLabel, descriptionLabel;

    private SideBar sideBar;

    public CourseDetails(SideBar sideBar, Course course) {
        this.sideBar = sideBar;
        loadFXML();
        init(course);
    }

    private void init(Course course) {
        nameLabel.setText(course.getName());

        Tooltip tooltip = new Tooltip(course.getName());
        tooltip.setShowDelay(Duration.millis(100));
        tooltip.setHideDelay(Duration.millis(100));
        tooltip.setShowDuration(Duration.INDEFINITE);
        nameLabel.setTooltip(tooltip);

        subLabel.setText(course.getId() + " - " + course.getCredit() + " หน่วยกิต");
        descriptionLabel.setText(course.getDescription());

        PrerequisiteList prerequisiteList = new PrerequisiteList(sideBar, course.getPrerequisite());
        getChildren().add(2, prerequisiteList);
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/course_details.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
