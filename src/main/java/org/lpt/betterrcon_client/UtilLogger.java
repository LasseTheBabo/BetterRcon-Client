package org.lpt.betterrcon_client;

import org.lpt.util.Logger;

public class UtilLogger implements Logger {
    @Override
    public void info(String s) {
        System.out.println(s);
    }

    @Override
    public void error(String s) {
        System.err.println(s);
    }
}
