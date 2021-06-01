package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.service.ServiceCard;

import java.io.IOException;
import java.io.OutputStream;

public class HandlerBalance implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue=null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);

        }
        handleResponse(httpExchange,requestParamValue);
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) {
        ServiceCard serviceCard = new ServiceCard();
        String message;
        if (serviceCard.getCardByNumber(requestParamValue) == null){
            message = "Карта отсутствует";
        }else{message = "Карта №: " + requestParamValue + " баланс: " +
                serviceCard.getBalanceAccountForCard(requestParamValue);}
        byte[] balance = message.getBytes();

        try {
            httpExchange.sendResponseHeaders(200, balance.length);
            OutputStream output = httpExchange.getResponseBody();
            output.write(balance);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("card=")[1];
    }
}
