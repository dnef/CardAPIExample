package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HandlerAddBalance implements HttpHandler {
    private Log logger = LogFactory.getLog(HandlerAddBalance.class);

    @Override
    public void handle(HttpExchange httpExchange) {
        Map<String, String> requestParamValue = null;
        if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
            handleResponse(httpExchange, requestParamValue);
        }

    }

    private void handleResponse(HttpExchange httpExchange, Map<String, String> requestParamValue) {
        ServiceCard serviceCard = new ServiceCard();
        String card = requestParamValue.get("card");
        String cash = requestParamValue.get("balance");
        String message;
        int code;
        if ((card.matches("[0-9]+") && card.length() == 16) && (cash.matches("^-?\\d*\\.{0,1}\\d+$"))) {
            try {
                serviceCard.addBalanceForCard(Long.valueOf(cash), card);
                message = "Баланс карты №:" + card + " : "+ serviceCard.getBalanceAccountForCard(card);
                code=200;
            } catch (ServiceException e) {
                message = "Карта отсутствует";
                logger.debug("Карта отсутствует");
                code = 400;
            }
        } else {
            message = "Не корректные входные данные.";
            code = 400;
            logger.debug("Не корректные входные данные.");
        }
        try {
            byte[] bytes = message.getBytes("utf-8");
            httpExchange.sendResponseHeaders(code, bytes.length);
            OutputStream output = httpExchange.getResponseBody();
            output.write(bytes);
            output.flush();
            httpExchange.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}

