package kucourses.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kucourses.components.CourseListItem;
import kucourses.components.CourseSectionLabel;
import kucourses.components.SideBar;
import kucourses.models.Course;
import kucourses.services.CourseData;

public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    private VBox listContainer;

    @FXML
    private ScrollPane scrollWrapper;

    public void init() {
        // Fix slow scrolling with mouse in Windows
        if (System.getProperty("os.name").startsWith("Windows"))
            fixSlowScrolling();

        // Init SideBar
        SideBar sideBar = new SideBar();
        root.setRight(sideBar);

        // Init CourseList for 1st time
        CourseData courseData = CourseData.getInstance();

        // Display courses table
        for (int year = 1; year <= 4; year++) {
            HBox yearContainer = new HBox();
            yearContainer.setSpacing(18);
            yearContainer.setAlignment(Pos.CENTER);

            for (int semester = 1; semester <= 2; semester++) {
                VBox coursesContainer = new VBox();
                coursesContainer.setSpacing(10);

                String semesterText = semester == 1 ? "ต้น" : "ปลาย";
                CourseSectionLabel label = new CourseSectionLabel(String.format("ปีที่ %d ภาค%s", year, semesterText));
                coursesContainer.getChildren().add(label);

                Course[] courses = courseData.getAll(year, semester);
                for (Course course : courses)
                    coursesContainer.getChildren().add(new CourseListItem(sideBar, course));

                yearContainer.getChildren().add(coursesContainer);
            }
            listContainer.getChildren().add(yearContainer);
        }

        initSavePassedOnClose();
    }

    private void initSavePassedOnClose() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setOnHiding(event -> CourseData.getInstance().savePassedCourses());
    }

    private void fixSlowScrolling() {
        listContainer.setOnScroll(event -> {
            double deltaY = event.getDeltaY() * 6;
            double width = scrollWrapper.getContent().getBoundsInLocal().getWidth();
            double vValue = scrollWrapper.getVvalue();
            scrollWrapper.setVvalue(vValue - deltaY / width);
        });
    }
}
