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
    private Label nameLabel, subLabel;

    public CourseListItem(Course course) {
        loadFXML();
        setText(course);
    }

    public void onClick(Runnable callback) {
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                callback.run();
        });
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/course_list_item.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setText(Course course) {
        nameLabel.setText(course.getName());
        subLabel.setText(course.getId() + " - " + course.getCredit() + " หน่วยกิต");
    }
}
