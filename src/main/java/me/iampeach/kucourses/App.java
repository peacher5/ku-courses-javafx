package me.iampeach.kucourses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("KU Courses");
        primaryStage.setScene(new Scene(root, 1060, 690));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
