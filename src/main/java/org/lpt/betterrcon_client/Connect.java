package org.lpt.betterrcon_client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.lpt.util.rcon.RconClient;

public class Connect extends Application {
    private Stage stage;
    private TextField serverTextField;
    private Spinner<Integer> portSpinner;
    private PasswordField pwBox;

    private final String emptyFieldStyle = "-fx-border-color: #ff0000;";

    @Override
    public void start(Stage stage) {
        stage.setTitle("BetterRcon Connect");
        this.stage = stage;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        Scene scene = new Scene(grid);
        stage.setResizable(false);
        stage.setScene(scene);

        // Server
        Label server = new Label("Server:");
        serverTextField = new TextField("localhost");
        serverTextField.setOnAction(e -> connect());
        grid.add(server, 0, 0);
        grid.add(serverTextField, 1, 0);

        // Port
        Label port = new Label("Port:");
        portSpinner = new Spinner<>(1, 65535, 25570);
        portSpinner.setEditable(true);
        grid.add(port, 0, 1);
        grid.add(portSpinner, 1, 1);

        // Password
        Label pw = new Label("Password:");
        pwBox = new PasswordField();
        pwBox.setOnAction(e -> connect());
        grid.add(pw, 0, 2);
        grid.add(pwBox, 1, 2);

        // Connect
        Button btn = new Button("Connect");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 3);
        btn.setOnAction(e -> connect());

        stage.sizeToScene();
        stage.show();

        serverTextField.setMaxSize(serverTextField.getWidth(), serverTextField.getHeight());
        pwBox.setMaxSize(pwBox.getWidth(), pwBox.getHeight());
        resetFieldStyle();
    }

    private void connect() {
        resetFieldStyle();
        if (!checkFields()) return;

        RconClient client;
        try {
            client = RconClient.connect(serverTextField.getText(), portSpinner.getValue());

            if (!client.authenticate(pwBox.getText())) {
                pwBox.requestFocus();
                pwBox.setText("");
                pwBox.setStyle(emptyFieldStyle);
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Password rejected!");
                return;
            }
        } catch (Exception e) {
            serverTextField.requestFocus();
            serverTextField.setStyle(emptyFieldStyle);
            AlertHelper.showAlert(Alert.AlertType.ERROR, "BetterRcon Client", "Error while connecting to server:", e.toString());
            return;
        }

        Main.setClient(client);
        new Terminal().start(new Stage(), serverTextField.getText());
        stage.close();
    }

    private void resetFieldStyle() {
        pwBox.setStyle("");
        pwBox.setPromptText("Password");

        serverTextField.setStyle("");
        serverTextField.setPromptText("Server");
    }

    private boolean checkFields() {
        boolean serverEmpty = serverTextField.getCharacters().isEmpty();
        boolean pwEmpty = pwBox.getCharacters().isEmpty();

        if (serverEmpty || pwEmpty) {
            if (serverEmpty) serverTextField.setStyle(emptyFieldStyle);
            if (pwEmpty) pwBox.setStyle(emptyFieldStyle);
            return false;
        }

        return true;
    }
}
