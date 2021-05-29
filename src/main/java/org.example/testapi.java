package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.BankCard;
import org.example.service.ServiceCard;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class testapi {
    public static void main(String[] args) throws IOException {
       ServiceCard serviceCard = new ServiceCard();
       serviceCard.addBalanceForCard(900000L,"2635678940023467");

        BankCard bankCard= new BankCard();
        bankCard.setCardNumber("1111111111111111");
        bankCard.setIdAccountClient(5L);
        bankCard.setActive(true);
        bankCard.setOpenDate(Date.valueOf(LocalDate.now()));
        serviceCard.addCardForAccaunt(bankCard);
        System.out.println(serviceCard.getBalanceAccountForCard("2635678940023467"));
        serviceCard.getAllCard().forEach(System.out::println);
        System.out.println("_____________________________________________");
        List<BankCard> bankCardList = serviceCard.getAllCard();
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer,bankCardList);
        System.out.println(writer.toString());


    }
}
