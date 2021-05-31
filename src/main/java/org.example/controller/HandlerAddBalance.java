package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.service.ServiceCard;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HandlerAddBalance implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Map<String, String> requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);

        }
        handleResponse(httpExchange,requestParamValue);
    }

    private void handleResponse(HttpExchange httpExchange, Map<String,String> requestParamValue) {
        ServiceCard serviceCard = new ServiceCard();
        String card = requestParamValue.get("card");
        String cash = requestParamValue.get("balance");
        serviceCard.addBalanceForCard(Long.valueOf(cash),card);
        try {
            byte[] bytes = ("Баланс карты №:"+card+" изменен").getBytes("utf-8");
            //System.out.println(answer);
            httpExchange.sendResponseHeaders(200, bytes.length);
            OutputStream output = httpExchange.getResponseBody();
            output.write(bytes);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> handleGetRequest(HttpExchange httpExchange) {
        String urlParam = httpExchange.
                getRequestURI()
                .toString().split("\\?")[1];
        String[] params = urlParam.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}
