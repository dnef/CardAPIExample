package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.entity.BankCard;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;

import java.io.*;
import java.util.List;

public class HandlerNewCard implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //String requestParamValue=null;
        if ("POST".equals(httpExchange.getRequestMethod())) {
            //requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange);

        }
        //handleResponse(httpExchange);
    }

    private void handleResponse(HttpExchange httpExchange) {
        ServiceCard serviceCard = new ServiceCard();
        try {
            ObjectMapper mapperIn = new ObjectMapper();
            InputStreamReader isr =  new InputStreamReader(httpExchange.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder content = new StringBuilder();
            String message;
            while ((line= br.readLine())!=null){
                content.append(line);
            }
            br.close();
            List<BankCard> bankCardList1= mapperIn.readValue(content.toString(), new TypeReference<List<BankCard>>(){});
            try {
                for (BankCard card : bankCardList1) {
                    serviceCard.addCardForAccaunt(card);
                }
                message = "Cards added";
            }catch (ServiceException e){
                message = "Ошибка добавления";
            }

            httpExchange.sendResponseHeaders(200,message.getBytes().length);
            OutputStream output = httpExchange.getResponseBody();
            output.write(message.getBytes());
            output.flush();
            httpExchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\[")[1];
    }
}
