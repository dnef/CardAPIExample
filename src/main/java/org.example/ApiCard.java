package org.example;

import org.example.MyHTTPServer;

import java.util.Properties;

public class ApiCard {
    public static void main(String[] args) {
        MyHTTPServer.run("src/main/resources/config.properties");
    }

}
