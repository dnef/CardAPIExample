package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
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
        serviceCard.addBalanceForCard(Long.valueOf(cash), card);
        try {
            byte[] bytes = ("Баланс карты №:" + card + " изменен").getBytes("utf-8");
            httpExchange.sendResponseHeaders(200, bytes.length);
            OutputStream output = httpExchange.getResponseBody();
            output.write(bytes);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> handleGetRequest(HttpExchange httpExchange) {
//        String urlParam = httpExchange.
//                getRequestURI()
//                .toString().split("\\?")[1];
        //String urlParam = httpExchange.getRequestBody().toString();
        //System.out.println(urlParam);
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

