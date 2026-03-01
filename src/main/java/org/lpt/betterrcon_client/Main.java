package org.lpt.betterrcon_client;

import javafx.application.Application;
import org.lpt.betterrcon_client.gui.Connect;
import org.lpt.betterrcon_client.gui.GUI;
import org.lpt.betterrcon_client.gui.Terminal;
import org.lpt.util.Logger;
import org.lpt.util.Util;
import org.lpt.util.rcon.RconClient;

public class Main {
    public static RconClient client;

    static void main(String[] args) {
        Util.LOGGER = new Logger() {
            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void error(String message) {
                System.err.println(message);
            }
        };

        if (System.console() == null) {
            Application.launch(GUI.class);
        }
    }

    public static void setClient(RconClient client) {
        Main.client = client;
    }

    public static RconClient getClient(RconClient client) {
        return Main.client;
    }
}
