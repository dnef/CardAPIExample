package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;

import java.io.IOException;
import java.io.OutputStream;

public class HandlerBalance implements HttpHandler {
    Log logger = LogFactory.getLog(HandlerBalance.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, requestParamValue);
        }
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) {
        ServiceCard serviceCard = new ServiceCard();
        String message;
        int code;
        if (requestParamValue.matches("[0-9]+") && requestParamValue.length() == 16) {
            try {
                message = "Карта №: " + requestParamValue + " баланс: " +
                        serviceCard.getBalanceAccountForCard(requestParamValue);
                code = 200;
            } catch (ServiceException e) {
                message = "Карта отсутствует.";
                logger.error("Карта отсутствует." + e);
                code = 400;
            }
        } else {
            message = "Не корректные входные данные.";
            code = 400;
        }
        byte[] balance = message.getBytes();

        try {
            httpExchange.sendResponseHeaders(code, balance.length);
            OutputStream output = httpExchange.getResponseBody();
            output.write(balance);
            output.flush();
            httpExchange.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
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
