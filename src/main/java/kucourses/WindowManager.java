package kucourses;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kucourses.controllers.CourseDataSelectController;
import kucourses.controllers.MainController;
import kucourses.services.CourseData.DataInfo;

import java.io.IOException;

public class WindowManager {
    private enum Window {
        MAIN("/fxml/main.fxml", "KU Courses", 1060, 690),
        COURSE_DATA_SELECT("/fxml/course_data_select.fxml", "Select Plan", 340, 380);

        private String path, title;
        private int width, height;

        Window(String path, String title, int width, int height) {
            this.path = path;
            this.title = title;
            this.width = width;
            this.height = height;
        }

        public String getPath() {
            return path;
        }

        public String getTitle() {
            return title;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    static void showCourseDataSelectWindow(Stage stage) {
        CourseDataSelectController controller = (CourseDataSelectController) showWindow(stage, Window.COURSE_DATA_SELECT);
        controller.init();
    }

    public static void showMainWindow(Stage stage, DataInfo dataInfo) {
        MainController controller = (MainController) showWindow(stage, Window.MAIN);
        controller.init(dataInfo);
    }

    private static Object showWindow(Stage stage, Window window) {
        FXMLLoader loader = new FXMLLoader(WindowManager.class.getResource(window.getPath()));
        stage.setTitle(window.getTitle());

        try {
            stage.setScene(new Scene(loader.load(), window.getWidth(), window.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setMinWidth(window.getWidth());
        stage.setMinHeight(window.getHeight());
        stage.show();

        return loader.getController();
    }
}
