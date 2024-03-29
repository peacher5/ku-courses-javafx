package kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import kucourses.models.Course;

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
            course.setPassed(!course.isPassed());

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
