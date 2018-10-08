package me.iampeach.kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import me.iampeach.kucourses.models.CourseList;
import me.iampeach.kucourses.models.Prerequisite;

import java.io.IOException;

class PrerequisiteList extends VBox {

    @FXML
    private Label type;

    private SideBar sideBar;

    PrerequisiteList(SideBar sideBar, Prerequisite prerequisite) {
        if (prerequisite != null) {
            this.sideBar = sideBar;
            loadFXML();
            init(prerequisite);
        }
    }

    private void init(Prerequisite prerequisite) {
        type.setText(getTypeText(prerequisite.getType()));
        for (String course : prerequisite.getCourses()) {
            CourseListItem item = new CourseListItem(sideBar, CourseList.getInstance().getById(course), true);
            getChildren().add(item);
        }
    }

    private String getTypeText(Prerequisite.Type type) {
        switch (type) {
            case ONE:
                return "อย่างน้อยหนึ่งตัว";
            case ALL:
                return "ทั้งหมด";
            case TOGETHER:
                return "หรือพร้อมกัน";
        }
        return null;
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/prerequisite_list.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
