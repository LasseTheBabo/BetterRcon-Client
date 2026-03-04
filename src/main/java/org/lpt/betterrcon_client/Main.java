package org.lpt.betterrcon_client;

import javafx.application.Application;
import org.lpt.util.Util;
import org.lpt.util.rcon.RconClient;

public class Main {
    private static RconClient client;

    public static void main(String[] args) {
        Util.LOGGER = new UtilLogger();
        Application.launch(Connect.class);
    }

    public static void setClient(RconClient client) {
        Main.client = client;
    }

    public static RconClient getClient() {
        return Main.client;
    }
}
