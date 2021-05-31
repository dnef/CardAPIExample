package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.entity.BankCard;
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
            while ((line= br.readLine())!=null){
                content.append(line);
            }
            br.close();
            List<BankCard> bankCardList1= mapperIn.readValue(content.toString(), new TypeReference<List<BankCard>>(){});
            for (BankCard card:bankCardList1){
                serviceCard.addCardForAccaunt(card);
            }
            String ok = "Cards added";
            httpExchange.sendResponseHeaders(200,ok.length());
            OutputStream output = httpExchange.getResponseBody();
            output.write(ok.getBytes());
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
