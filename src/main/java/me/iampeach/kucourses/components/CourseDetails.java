package me.iampeach.kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import me.iampeach.kucourses.models.Course;

import java.io.IOException;

public class CourseDetails extends VBox {

    @FXML
    private Label nameLabel, subLabel, descriptionLabel;

    public CourseDetails(Course course) {
        loadFXML();
        setText(course);
    }

    private void setText(Course course) {
        nameLabel.setText(course.getName());
        subLabel.setText(course.getId() + " - " + course.getCredit() + " หน่วยกิต");
        descriptionLabel.setText(course.getDescription());
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
