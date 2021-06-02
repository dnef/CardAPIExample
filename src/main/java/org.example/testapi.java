package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.GlobalConfig;
import org.example.entity.BankCard;
import org.example.exception.ServiceException;
import org.example.service.ServiceCard;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class testapi {
    public static void main(String[] args) throws IOException {

     GlobalConfig.initGlobalConfig("src/main/resources/config.properties");
     ServiceCard serviceCard = new ServiceCard();

     try {
      serviceCard.addBalanceForCard(900000L,"1111111111111113");
     } catch (ServiceException e) {
      //e.printStackTrace();
      System.out.println("1--------------------------");
     }

     BankCard bankCard= new BankCard();
        bankCard.setCardNumber("1111111111111118");
        bankCard.setAccountNumberId(3L);
        bankCard.setAccountNumber("11111111111111111113");
        bankCard.setActive(true);
        bankCard.setOpenDate(Date.valueOf(LocalDate.now()));
     try {
      serviceCard.addCardForAccaunt(bankCard);
     } catch (ServiceException e) {
      //e.printStackTrace();
      System.out.println("2--------------------------");
      e.printStackTrace();
     }
     try {
      System.out.println(serviceCard.getBalanceAccountForCard("1111111111111113"));
     } catch (ServiceException e) {
      //e.printStackTrace();
      System.out.println("3--------------------------");
     }

     try {
      serviceCard.getAllCard().forEach(System.out::println);
     } catch (ServiceException e) {
      //e.printStackTrace();
      System.out.println("4--------------------------");
     }

     List<BankCard> bankCardList = null;
     try {
      bankCardList = serviceCard.getAllCard();
     } catch (ServiceException e) {
     // e.printStackTrace();
      System.out.println("5--------------------------");
     }
     StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer,bankCardList);
        System.out.println(writer.toString());

        String jsonString = "[{\"id\":1,\"cardNumber\":\"2635678946123467\",\"accountNumber\":\"12345367281835461728\",\"active\":true,\"date_open\":1614970800000}," +
                "{\"id\":2,\"cardNumber\":\"2635678940023467\",\"accountNumber\":\"12345367281835461728\",\"active\":true,\"date_open\":1617562800000}," +
                "{\"id\":3,\"cardNumber\":\"2630078946123467\",\"accountNumber\":\"89356726789534678356\",\"active\":true,\"date_open\":1617562800000}," +
                "{\"id\":4,\"cardNumber\":\"2635000946123467\",\"accountNumber\":\"89356726755534678356\",\"active\":true,\"date_open\":1617562800000}," +
                "{\"id\":6,\"cardNumber\":\"2111111111111112\",\"accountNumber\":\"89356726755534678356\",\"active\":true,\"date_open\":1622228400000}]";
        ObjectMapper mapperIn = new ObjectMapper();
        List<BankCard> bankCardList1= mapperIn.readValue(jsonString, new TypeReference<List<BankCard>>(){});
        bankCardList1.forEach(System.out::println);


    }
}
