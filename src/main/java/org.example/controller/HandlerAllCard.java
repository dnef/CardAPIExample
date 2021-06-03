package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.entity.BankCard;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;

public class HandlerAllCard implements HttpHandler {
    private Log logger = LogFactory.getLog(HandlerAddBalance.class);
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ServiceCard serviceCard = new ServiceCard();
        List<BankCard> bankCardList;
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        int code;
        try {
            bankCardList = serviceCard.getAllCard();
            mapper.writeValue(writer,bankCardList);
            code=200;
            logger.debug("Получение списка карт.");
        } catch (ServiceException e) {
            writer.write("Ошибка сервера.");
            code = 400;
            logger.error(e.getMessage());
        }
        httpExchange.sendResponseHeaders(code, writer.toString().length());
        OutputStream output = httpExchange.getResponseBody();
        output.write(writer.toString().getBytes());
        output.flush();
        httpExchange.close();
    }
}
