package kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import kucourses.models.Course;
import kucourses.models.CourseUtils;

import java.io.IOException;

public class CourseListItem extends AnchorPane {
    @FXML
    private Label titleLabel, subLabel;

    @FXML
    private ImageView iconView;

    private final SideBar sideBar;
    private final boolean isPrerequisiteMode;
    private Course course;

    public CourseListItem(SideBar sideBar, Course course) {
        this(sideBar, course, false);
    }

    CourseListItem(SideBar sideBar, Course course, boolean isPrerequisiteMode) {
        this.sideBar = sideBar;
        this.isPrerequisiteMode = isPrerequisiteMode;
        this.course = course;

        loadFXML();
        init();
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(getLayoutPath()));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLayoutPath() {
        if (isPrerequisiteMode)
            return "/fxml/prerequisite_list_item.fxml";
        return "/fxml/course_list_item.fxml";
    }

    private void init() {
        titleLabel.setText(course.getName());
        subLabel.setText(course.getId() + " - " + course.getCredit() + " หน่วยกิต");

        if (!isPrerequisiteMode) {
            if (course.isPassed())
                setPassedIcon();
            else if (!CourseUtils.isAvailable(course))
                setLockedIcon();
            else if (course.getPrerequisite() != null)
                setUnlockedIcon();
            else
                setNoIcon();

            CourseUtils.setOnPassToggleListenerToAllCourses(eachCourse -> {
                if (CourseUtils.isAvailable(course)) {
                    if (course.isPassed())
                        setPassedIcon();
                    else if (course.getPrerequisite() != null)
                        setUnlockedIcon();
                    else
                        setNoIcon();
                } else {
                    if (course.isPassed())
                        course.setPassed(false);
                    setLockedIcon();
                }
            });
        }

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                sideBar.showCourseDetails(course);
        });
    }

    private void setPassedIcon() {
        iconView.setImage(new Image("/icons/passed_icon.png"));
    }

    private void setLockedIcon() {
        iconView.setImage(new Image("/icons/lock_icon.png"));
    }

    private void setUnlockedIcon() {
        iconView.setImage(new Image("/icons/unlock_icon.png"));
    }

    private void setNoIcon() {
        iconView.setImage(null);
    }
}
