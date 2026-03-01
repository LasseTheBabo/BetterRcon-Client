package org.lpt.betterrcon_client.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Terminal {

    public void start(Stage stage) {
        stage.setTitle("BetterRcon");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setMaxSize(1000, 1000);
        grid.add(textArea, 0, 0);

        stage.show();
    }
}
