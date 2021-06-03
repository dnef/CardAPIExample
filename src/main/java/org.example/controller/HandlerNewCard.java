package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.entity.BankCard;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

public class HandlerNewCard implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, requestParamValue);
        }

    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        ServiceCard serviceCard = new ServiceCard();
        ObjectMapper mapperIn = new ObjectMapper();
        String message;
        int code;
        List<BankCard> bankCardList1;

        try {
            bankCardList1 = mapperIn.readValue(requestParamValue, new TypeReference<List<BankCard>>() {
            });
            for (BankCard card : bankCardList1) {
                serviceCard.addCardForAccaunt(card);
            }

            message = "Карты добавлены";
            code = 200;

        } catch (ServiceException e) {
            message = "Ошибка добавления карты" + e.getMessage();
            code = 404;
        } catch (JsonMappingException e) {
            message = "Ошибка добавления карты" + e.getMessage();
            code = 404;
        } catch (JsonProcessingException e) {
            message = "Ошибка добавления карты" + e.getMessage();
            code = 404;
        }
        httpExchange.sendResponseHeaders(code, message.getBytes().length);
        OutputStream output = httpExchange.getResponseBody();
        output.write(message.getBytes());
        output.flush();
        httpExchange.close();
    }

    private String handleGetRequest(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder content = new StringBuilder();

        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        br.close();
        return content.toString();

    }
}
