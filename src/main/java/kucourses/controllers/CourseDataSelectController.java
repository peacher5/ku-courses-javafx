package kucourses.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kucourses.WindowManager;
import kucourses.components.DataInfoListItem;
import kucourses.models.Plan;
import kucourses.services.PlanData;
import kucourses.services.UserData;

import java.util.TreeMap;

public class CourseDataSelectController {
    @FXML
    private VBox selectContainer;

    public void init() {
        TreeMap<String, Plan> plans = PlanData.getAllPlans();

        if (plans.size() == 0) {
            Label noPlanLabel = new Label("- ไม่พบข้อมูลแผนการศึกษา -");
            selectContainer.getChildren().add(noPlanLabel);
            return;
        }

        UserData userData = UserData.getInstance();
        plans.forEach((name, plan) -> {
            DataInfoListItem item = new DataInfoListItem(plans.get(name).getName());
            item.setOnClickListenser(() -> {
                userData.initData(name);
                WindowManager.showMainWindow(new Stage(), userData.getData());
                Stage stage = (Stage) selectContainer.getScene().getWindow();
                stage.close();
            });

            selectContainer.getChildren().add(item);
        });
    }
}
