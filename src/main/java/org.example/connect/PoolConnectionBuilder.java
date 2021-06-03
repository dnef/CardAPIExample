package org.example.connect;

import org.example.common.GlobalConfig;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class PoolConnectionBuilder implements ConnectionBuilder {
    private ComboPooledDataSource dataSource;
    public PoolConnectionBuilder() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(GlobalConfig.getProperty("db.driver.class"));
            dataSource.setJdbcUrl(GlobalConfig.getProperty("db.host"));
            dataSource.setUser(GlobalConfig.getProperty("db.login"));
            dataSource.setPassword(GlobalConfig.getProperty("db.password"));
            dataSource.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}