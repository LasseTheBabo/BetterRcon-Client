package org.lpt.betterrcon_client;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertHelper {
    public static Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert infoAlert = new Alert(type);
        infoAlert.setTitle(title);
        infoAlert.setHeaderText(header);
        infoAlert.setContentText(content);

        return infoAlert.showAndWait();
    }

    public static Optional<ButtonType> showAlert(Alert.AlertType type, String title, String content) {
        return showAlert(type, title, null, content);
    }

    public static Optional<ButtonType> showAlert(Alert.AlertType type, String content) {
        return showAlert(type, "BetterRcon Client", content);
    }
}
