package org.example.connect;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.common.GlobalConfig;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class PoolConnectionBuilder implements ConnectionBuilder {
    private ComboPooledDataSource dataSource;
    private Log logger = LogFactory.getLog(PoolConnectionBuilder.class);
    public PoolConnectionBuilder() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(GlobalConfig.getProperty("db.driver.class"));
            dataSource.setJdbcUrl(GlobalConfig.getProperty("db.host"));
            dataSource.setUser(GlobalConfig.getProperty("db.login"));
            dataSource.setPassword(GlobalConfig.getProperty("db.password"));
            dataSource.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            logger.error("Error PoolConnectionBuilder : "+e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        logger.debug("Get pool connection");
        return dataSource.getConnection();
    }
}