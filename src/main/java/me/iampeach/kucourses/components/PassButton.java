package me.iampeach.kucourses.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PassButton extends VBox {

    public PassButton() {
        loadFXML();
        onClick(() -> System.out.println("Button click"));
    }

    public void onClick(Runnable callback) {
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
