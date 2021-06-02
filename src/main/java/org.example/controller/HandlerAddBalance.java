package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HandlerAddBalance implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Map<String, String> requestParamValue = null;
        if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
        }
        handleResponse(httpExchange, requestParamValue);
    }

    private void handleResponse(HttpExchange httpExchange, Map<String, String> requestParamValue) {
        ServiceCard serviceCard = new ServiceCard();
        String card = requestParamValue.get("card");
        String cash = requestParamValue.get("balance");
        String message;
        int code = 200;
        if ((card.matches("[0-9]+") && card.length() == 16) && (cash.matches("[0-9]+"))) {
//            if (serviceCard.getCardByNumber(card) == null) {
//                message = "Карта отсутствует";
//            } else {
//                message = "Баланс карты №:" + card + " изменен";
//            }
            try {
                serviceCard.addBalanceForCard(Long.valueOf(cash), card);
                message = "Баланс карты №:" + card + " изменен";
            } catch (ServiceException e) {
                // logger  ------ e.printStackTrace();
                message = "Карта отсутствует";
            }
        }else{message = "Не корректные входные данные.";code=400;}
        try {
            byte[] bytes = message.getBytes("utf-8");
            httpExchange.sendResponseHeaders(code, bytes.length);
            OutputStream output = httpExchange.getResponseBody();
            output.write(bytes);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> handleGetRequest(HttpExchange httpExchange) {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder urlParam = new StringBuilder();
            while ((line = br.readLine()) != null) {
                urlParam.append(line);
            }
            br.close();
            String[] params = urlParam.toString().split("&");
            Map<String, String> map = new HashMap<String, String>();
            for (String param : params) {
                String name = param.split("=")[0];
                String value = param.split("=")[1];
                map.put(name, value);
            }
            return map;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

