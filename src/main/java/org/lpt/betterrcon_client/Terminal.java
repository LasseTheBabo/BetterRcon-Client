package org.lpt.betterrcon_client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.lpt.util.History;
import org.lpt.util.rcon.RconClient;

import java.util.List;

public class Terminal {
    private TextArea output;
    private TextField input;
    private final History<String> commandHistory = new History<>();

    public void start(Stage stage, String server) {
        stage.setTitle("BetterRcon: " + server);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Command output
        output = new TextArea();
        output.setEditable(false);
        grid.add(output, 0, 0);
        GridPane.setHgrow(output, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setVgrow(output, javafx.scene.layout.Priority.ALWAYS);

        // Command input
        input = new TextField();
        input.setPromptText("Command");
        input.setOnAction(e -> handleCommand());
        addHistoryHandler(input);
        grid.add(input, 0, 1);

        Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.show();

        input.requestFocus();
    }

    private void addHistoryHandler(TextField input) {
        input.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.UP) {
                commandHistory.navigateHistory(-1);
                input.setText(commandHistory.getCommand());
                e.consume();
            } else if (e.getCode() == KeyCode.DOWN) {
                commandHistory.navigateHistory(1);
                input.setText(commandHistory.getCommand());
                e.consume();
            }
        });
    }

    private void handleCommand() {
        String command = input.getText();
        if (!command.isEmpty()) {
            commandHistory.addCommand(command);
        }

        RconClient client = Main.getClient();
        List<String> response = client.sendCommand(command);

        if (response.stream().anyMatch(line -> !line.isBlank())) {
            for (String line : response) {
                if (line.isBlank()) continue;
                output.appendText(line + "\n");
            }
            output.appendText("\n");
        }

        input.clear();
    }
}
