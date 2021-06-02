package org.example.connect;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

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
    Log logger = LogFactory.getLog(ConnectDB.class);

    private void setProperty() {
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            host = property.getProperty("db.host");
            login = property.getProperty("db.login");
            password = property.getProperty("db.password");
            logger.debug("Property read");
        } catch (FileNotFoundException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        setProperty();


        try(Connection conn = DriverManager.getConnection(host, login, password)) {
            return conn;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.debug("Connection databace...");

return null;


    }
}
