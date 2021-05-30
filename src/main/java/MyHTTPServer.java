import com.sun.net.httpserver.HttpServer;
import org.example.HandlerAllCard;
import org.example.HandlerBalance;
import org.example.HandlerNewCard;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyHTTPServer {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/apiCard/newCard", new HandlerNewCard());
            server.createContext("/apiCard/all", new HandlerAllCard());
            server.createContext("/apiCard/balance", new HandlerBalance());

            server.setExecutor(threadPoolExecutor);
            server.start();
            //logger.info(" Server started on port 8001");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


