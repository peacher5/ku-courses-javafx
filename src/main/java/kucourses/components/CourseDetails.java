package kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import kucourses.models.Course;
import kucourses.models.CourseDifficulty;
import kucourses.models.CourseDifficulty.Level;
import kucourses.models.Prerequisite;

import java.io.IOException;

class CourseDetails extends VBox {

    @FXML
    private Label nameLabel, subLabel, descriptionLabel, diffLabel;
    @FXML
    private VBox diffBg;

    CourseDetails(SideBar sideBar, Course course) {
        loadFXML();
        init(sideBar, course);
    }

    private void init(SideBar sideBar, Course course) {
        nameLabel.setText(course.getName());

        Tooltip tooltip = new Tooltip(course.getName());
        tooltip.setShowDelay(Duration.millis(100));
        tooltip.setHideDelay(Duration.millis(100));
        tooltip.setShowDuration(Duration.INDEFINITE);
        nameLabel.setTooltip(tooltip);

        subLabel.setText(course.getId() + " - " + course.getCredit() + " หน่วยกิต");
        descriptionLabel.setText(course.getDescription());

        Level level = CourseDifficulty.getLevel(course.getDifficulty());

        diffLabel.setText(level.getText());

        if (level == CourseDifficulty.Level.EASY)
            diffBg.getStyleClass().add("diff-easy");
        else if (level == CourseDifficulty.Level.MEDIUM)
            diffBg.getStyleClass().add("diff-medium");
        else
            diffBg.getStyleClass().add("diff-hard");

        Prerequisite prerequisite = course.getPrerequisite();
        if (prerequisite == null)
            return;
        PrerequisiteList prerequisiteList = new PrerequisiteList(sideBar, prerequisite);
        getChildren().add(2, prerequisiteList);
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/course_details.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
