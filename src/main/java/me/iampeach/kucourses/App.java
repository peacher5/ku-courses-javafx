package me.iampeach.kucourses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        window.setTitle("KU Courses");
        window.setScene(new Scene(root, 1060, 690));
        window.setMinWidth(1060);
        window.setMinHeight(690);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
