package kucourses.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DataInfoListItem extends AnchorPane {
    @FXML
    private Label titleLabel;

    public DataInfoListItem(String title) {
        loadFXML();
        setId("item");
        titleLabel.setText(title);
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/data_info_list_item.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnClickListenser(Runnable onClick) {
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY)
                onClick.run();
        });
    }
}
