package me.iampeach.kucourses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final int MIN_WIDTH = 1060;
    private static final int MIN_HEIGHT = 690;

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        window.setTitle("KU Courses");
        window.setScene(new Scene(root, MIN_WIDTH, MIN_HEIGHT));
        window.setMinWidth(MIN_WIDTH);
        window.setMinHeight(MIN_HEIGHT);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
