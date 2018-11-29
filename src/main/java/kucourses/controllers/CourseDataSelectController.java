package kucourses.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kucourses.WindowManager;
import kucourses.components.DataInfoListItem;
import kucourses.services.CourseData;

public class CourseDataSelectController {
    @FXML
    private VBox selectContainer;

    public void init() {
        for (CourseData.DataInfo dataInfo : CourseData.DataInfo.values()) {
            DataInfoListItem item = new DataInfoListItem(dataInfo.getTitle());
            item.setOnClickListenser(() -> {
                WindowManager.showMainWindow(new Stage(), dataInfo);
                Stage stage = (Stage) selectContainer.getScene().getWindow();
                stage.close();
            });

            selectContainer.getChildren().add(item);
        }
    }
}
