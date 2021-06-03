package org.example.connect;

import org.example.common.GlobalConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class SimpleConnectionBuilder implements ConnectionBuilder {
    private ComboPooledDataSource dataSource;
    public SimpleConnectionBuilder() {
        try {
            Class.forName(GlobalConfig.getProperty("db.driver.class"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = GlobalConfig.getProperty("db.host");
        String login = GlobalConfig.getProperty("db.login");
        String password = GlobalConfig.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}