package org.example.connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectDB {
    private FileInputStream fis;
    private Properties property = new Properties();
    private String host;
    private String login;
    private String password;
    Logger logger = Logger.getLogger(ConnectDB.class.getName());

    private void setProperty() {
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            host = property.getProperty("db.host");
            login = property.getProperty("db.login");
            password = property.getProperty("db.password");
            logger.info("Property read");
        } catch (FileNotFoundException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
            setProperty();
            Connection conn = DriverManager.getConnection(host, login, password);
            logger.info("Connection databace...");
            return conn;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}
