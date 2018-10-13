package me.iampeach.kucourses.utils;

import javafx.scene.control.Alert;

public class NotifyUtils {
    public static void error(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);

        showAndWait(alert);
    }

    public static void warning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);

        showAndWait(alert);
    }

    public static void info(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);

        showAndWait(alert);
    }

    private static void showAndWait(Alert alert) {
        alert.setTitle("KU Courses");
        alert.setHeaderText(null);

        alert.showAndWait();
    }
}
