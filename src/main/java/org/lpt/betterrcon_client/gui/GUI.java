package org.lpt.betterrcon_client.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage stage) {
        new Terminal().start(new Stage());
    }
}
