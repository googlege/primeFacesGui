package de.homedev.primefaces.frontend.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrontendSettings {
    private FrontendSettings() {
    }

    private static Integer rmiPort = null;
    private static String rmiHost = null;

    public static FrontendSettings getSettings() {
        if ((rmiHost == null) || (rmiPort == null)) {
            try {
                Properties prop = new Properties();
                InputStream in = FrontendSettings.class.getResourceAsStream("/settings.properties");
                prop.load(in);
                in.close();
                rmiHost = prop.getProperty("rmiHost");
                if (rmiHost == null) {
                    throw new RuntimeException("Can't find property 'rmiHost'");
                }
                String rmiPortStr = prop.getProperty("rmiPort");
                if (rmiPortStr == null) {
                    throw new RuntimeException("Can't find property 'rmiPort'");
                }
                rmiPort = Integer.valueOf(rmiPortStr);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        return new FrontendSettings();
    }

    public static FrontendSettings reload() {
        rmiHost = null;
        rmiPort = null;
        return getSettings();
    }

    public Integer getRmiPort() {
        return rmiPort;
    }

    public String getRmiHost() {
        return rmiHost;
    }

}
