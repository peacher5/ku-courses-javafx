package kucourses.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kucourses.components.CourseListItem;
import kucourses.components.CourseSectionLabel;
import kucourses.components.SideBar;
import kucourses.models.Course;
import kucourses.models.User;
import kucourses.services.CourseData;
import kucourses.services.UserData;

public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    private VBox listContainer;

    @FXML
    private ScrollPane scrollWrapper;

    @FXML
    private Label headerLabel;

    public void init(User user) {

        // Init CourseData for 1st time
        CourseData courseData;
        try {
            courseData = CourseData.getInstance(user);
        } catch (IllegalArgumentException e) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error with user selected plan");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

        // Fix slow scrolling with mouse in Windows
        if (System.getProperty("os.name").startsWith("Windows"))
            fixSlowScrolling();

        // Init SideBar
        SideBar sideBar = new SideBar();
        root.setRight(sideBar);

        headerLabel.setText("วิชาบังคับใน" + courseData.getPlanTitle());

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

                Course[] courses = courseData.getByGroup(year, semester);
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
        stage.setOnHiding(event -> UserData.getInstance().save());
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
