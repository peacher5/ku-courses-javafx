package kucourses.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kucourses.WindowManager;
import kucourses.components.DataInfoListItem;
import kucourses.models.Plan;
import kucourses.models.User;
import kucourses.services.PlanData;
import kucourses.services.UserData;

import java.util.Set;
import java.util.TreeMap;

public class CourseDataSelectController {
    @FXML
    private VBox selectContainer;

    public void init() {
        TreeMap<String, Plan> plans = PlanData.getInstance().getAllPlans();
        Set<String> planNames = plans.keySet();

        if (planNames.size() == 0) {
            Label noPlanLabel = new Label("- ไม่พบข้อมูลแผนการศึกษา -");
            selectContainer.getChildren().add(noPlanLabel);
            return;
        }

        for (String planName : planNames) {
            DataInfoListItem item = new DataInfoListItem(plans.get(planName).getName());
            item.setOnClickListenser(() -> {
                WindowManager.showMainWindow(new Stage(), new User(planName, new String[0]));
                UserData.getInstance().initData(planName);
                Stage stage = (Stage) selectContainer.getScene().getWindow();
                stage.close();
            });

            selectContainer.getChildren().add(item);
        }
    }
}
