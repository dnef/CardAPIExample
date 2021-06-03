package org.example.common;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalConfig {
    private static Log logger = LogFactory.getLog(GlobalConfig.class);
    private static final String CONFIG_NAME = "config.properties";
    private static final Properties GLOBAL_COFIG = new Properties();

    public static void initGlobalConfig() throws IOException {
        initGlobalConfig(null);
        logger.debug("Load config default");
    }

    public static void initGlobalConfig(String name) throws IOException {
        if (name != null && !name.trim().isEmpty()) {
            GLOBAL_COFIG.load(new FileReader(name));
        } else {
            GLOBAL_COFIG.load(new FileReader(CONFIG_NAME));
        }
        logger.debug("Load config param parameters");
    }

    public static String getProperty(String property) {
        return GLOBAL_COFIG.getProperty(property);
    }
}
