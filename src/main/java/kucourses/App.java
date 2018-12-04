package kucourses;

import javafx.application.Application;
import javafx.stage.Stage;
import kucourses.services.UserData;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        if (UserData.isExists())
            WindowManager.showMainWindow(stage, UserData.getInstance().getData());
        else
            WindowManager.showCourseDataSelectWindow(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
