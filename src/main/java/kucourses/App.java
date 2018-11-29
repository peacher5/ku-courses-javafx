package kucourses;

import javafx.application.Application;
import javafx.stage.Stage;
import kucourses.services.CourseData;
import kucourses.services.CourseData.DataInfo;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        if (CourseData.isUserDataExists())
            WindowManager.showMainWindow(stage, DataInfo.toDataInfo(CourseData.getDataNameFromUserData()));
        else
            WindowManager.showCourseDataSelectWindow(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
