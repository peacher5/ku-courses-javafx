package me.iampeach.kucourses.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import me.iampeach.kucourses.components.CourseListItem;
import me.iampeach.kucourses.components.CourseSectionLabel;
import me.iampeach.kucourses.components.SideBar;
import me.iampeach.kucourses.models.Course;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private GridPane gridContainer;

    @FXML
    private ScrollPane scrollWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Fix slow scrolling with mouse in Windows
        if (System.getProperty("os.name").startsWith("Windows")) {
            gridContainer.setOnScroll(event -> {
                double deltaY = event.getDeltaY() * 6;
                double width = scrollWrapper.getContent().getBoundsInLocal().getWidth();
                double vValue = scrollWrapper.getVvalue();
                scrollWrapper.setVvalue(vValue - deltaY / width);
            });
        }

        SideBar sideBar = new SideBar();
        root.setRight(sideBar);

        Course python = new Course("01418112", "Fundamental Programming Concepts", "None", 3, null);
        Course cal = new Course("01417111", "Calculus I", "None", 3, null);

        CourseListItem calItem = new CourseListItem(cal);
        calItem.onClick(() -> sideBar.showCourseDetails(cal));

        CourseListItem pyItem = new CourseListItem(python);
        pyItem.onClick(() -> sideBar.showCourseDetails(python));

        gridContainer.add(new CourseSectionLabel("ปีที่ 1 ภาคต้น"), 0, 0);
        gridContainer.add(calItem, 0, 1);
        gridContainer.add(pyItem, 0, 2);
        gridContainer.add(new CourseListItem(cal), 0, 3);
        gridContainer.add(new CourseListItem(cal), 0, 4);
        gridContainer.add(new CourseListItem(cal), 0, 5);

        gridContainer.add(new CourseSectionLabel("ปีที่ 1 ภาคปลาย"), 1, 0);
        gridContainer.add(new CourseListItem(cal), 1, 1);
        gridContainer.add(new CourseListItem(cal), 1, 2);
        gridContainer.add(new CourseListItem(cal), 1, 3);

        gridContainer.add(new CourseSectionLabel("ปีที่ 2 ภาคต้น"), 0, 6);
        gridContainer.add(new CourseListItem(cal), 0, 7);
        gridContainer.add(new CourseListItem(cal), 0, 8);
        gridContainer.add(new CourseListItem(cal), 0, 9);

        gridContainer.add(new CourseSectionLabel("ปีที่ 2 ภาคปลาย"), 1, 6);
        gridContainer.add(new CourseListItem(cal), 1, 7);
        gridContainer.add(new CourseListItem(cal), 1, 8);
        gridContainer.add(new CourseListItem(cal), 1, 9);
        gridContainer.add(new CourseListItem(cal), 1, 10);
    }
}
