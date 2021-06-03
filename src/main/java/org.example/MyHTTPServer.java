package org.example;

import com.sun.net.httpserver.HttpServer;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.common.CreateDB;
import org.example.common.GlobalConfig;
import org.example.controller.HandlerAddBalance;
import org.example.controller.HandlerAllCard;
import org.example.controller.HandlerBalance;
import org.example.controller.HandlerNewCard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyHTTPServer {

    public static void run(String property) {
        Log logger = LogFactory.getLog(MyHTTPServer.class);
        try {
            GlobalConfig.initGlobalConfig(property);
        } catch (IOException e) {
            logger.error("Error download config - " + e);
        }
        CreateDB.createDB();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        try {
            System.out.println("HttpServer start.");
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/apiCard/newCard", new HandlerNewCard());
            server.createContext("/apiCard/allCard", new HandlerAllCard());
            server.createContext("/apiCard/getBalance", new HandlerBalance());
            server.createContext("/apiCard/addBalance", new HandlerAddBalance());
            server.setExecutor(threadPoolExecutor);
            server.start();
            logger.debug(" Server started on port 8001");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}


