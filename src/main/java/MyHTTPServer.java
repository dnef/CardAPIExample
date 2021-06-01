import com.sun.net.httpserver.HttpServer;
import org.example.controller.HandlerAddBalance;
import org.example.controller.HandlerAllCard;
import org.example.controller.HandlerBalance;
import org.example.controller.HandlerNewCard;
import org.example.connect.ConnectDB;
import org.apache.ibatis.jdbc.ScriptRunner;


import java.io.*;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyHTTPServer {

    public static void main(String[] args) {
        try(Connection connection = new ConnectDB().getConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Reader readerTable = new BufferedReader(new FileReader("src/main/resources/create_table.sql"));
            scriptRunner.runScript(readerTable);
            Reader readerInsert = new BufferedReader(new FileReader("src/main/resources/insert_data.sql"));
            scriptRunner.runScript(readerInsert);
            readerTable.close();
            readerInsert.close();
            scriptRunner.closeConnection();
            System.out.println("SQL script runner");
        } catch (FileNotFoundException e) {
            System.out.println("File SQL script not found: "+e);
        }  catch (SQLException throwables) {
            System.out.println("Error SQL :"+throwables);
            //throwables.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        try {
            System.out.println("HttpServer start.");
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/apiCard/newCard", new HandlerNewCard());
            server.createContext("/apiCard/allCard", new HandlerAllCard());
            server.createContext("/apiCard/getBalance", new HandlerBalance());
            server.createContext("/apiCard/addBalance",new HandlerAddBalance());

            server.setExecutor(threadPoolExecutor);
            server.start();
            //logger.info(" Server started on port 8001");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


