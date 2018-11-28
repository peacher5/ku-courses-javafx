package kucourses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kucourses.controllers.MainController;

public class App extends Application {
    private static final int MIN_WIDTH = 1060;
    private static final int MIN_HEIGHT = 690;

    @Override
    public void start(Stage window) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        window.setTitle("KU Courses");
        window.setScene(new Scene(loader.load(), MIN_WIDTH, MIN_HEIGHT));
        window.setMinWidth(MIN_WIDTH);
        window.setMinHeight(MIN_HEIGHT);
        window.show();

        MainController controller = loader.getController();
        controller.init();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
