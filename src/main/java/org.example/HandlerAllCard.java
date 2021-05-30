package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.entity.BankCard;
import org.example.service.ServiceCard;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;

public class HandlerAllCard implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ServiceCard serviceCard = new ServiceCard();
        List<BankCard> bankCardList = serviceCard.getAllCard();
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer,bankCardList);
        httpExchange.sendResponseHeaders(200, writer.toString().length());
        OutputStream output = httpExchange.getResponseBody();
        output.write(writer.toString().getBytes());
        output.flush();
    }
}
