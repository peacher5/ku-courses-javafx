package me.iampeach.kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import me.iampeach.kucourses.controllers.CourseController;
import me.iampeach.kucourses.models.Course;

import java.io.IOException;

class PassButton extends HBox {

    @FXML
    private Label label;

    @FXML
    private ImageView iconView;

    PassButton(Course course) {
        loadFXML();
        init(course);
    }

    private void init(Course course) {
        if (course.isPassed())
            setCancelState();
        else
            setPassState();

        onClick(() -> {
            boolean success;
            boolean prevStage = course.isPassed();

            course.setPassed(!course.isPassed());

            if (course.isPassed()) {
                success = CourseController.setPassedCourse(course.getId(), course.getPrerequisite());
            } else {
                success = CourseController.unsetPassedCourse(course.getId());
            }

            if (!success) {
                course.setPassed(prevStage);
                return;
            }

            course.updateListener();

            if (course.isPassed())
                setCancelState();
            else
                setPassState();
        });
    }

    private void setPassState() {
        label.setText("เรียนผ่านแล้ว");
        iconView.setImage(new Image("/icons/pass_gray_icon.png"));
    }

    private void setCancelState() {
        label.setText("ยกเลิก");
        iconView.setImage(new Image("/icons/cancel_gray_icon.png"));
    }

    private void onClick(Runnable callback) {
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                callback.run();
        });
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pass_button.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
