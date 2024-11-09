package org.example;

import org.example.util.R;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            Properties configuration = new Properties();
            configuration.load(R.getProperties("database.properties"));
            String host = configuration.getProperty("host");
            String port = configuration.getProperty("port");
            String name = configuration.getProperty("name");
            String username = configuration.getProperty("username");
            String password = configuration.getProperty("password");
            System.out.println(host + "  " + port + "  " + name + "  " + username + "  " + password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}