package org.example.service;

import junit.framework.TestCase;
import org.example.common.CreateDB;
import org.example.common.GlobalConfig;
import org.example.dao.DAOFactory;
import org.example.entity.AccountForCustomer;
import org.example.entity.BankCard;
import org.example.exception.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceCardTest {
    ServiceCard serviceCard;
    BankCard bankCard;
    List<BankCard> bankCardList = new ArrayList<BankCard>();
    @Before
    public void setUp() throws Exception {
        GlobalConfig.initGlobalConfigTest("src/main/resources/configTest.properties");
        CreateDB.createDB();
        serviceCard = new ServiceCard();
        bankCard=new BankCard(5L,"5555555555555555",3L,true,Date.valueOf(LocalDate.now()));
        bankCard.setAccountNumber("11111111111111111113");
        BankCard bankCard1 = new BankCard(1l,"1111111111111111",1L,true, Date.valueOf("2021-03-06"));
        BankCard bankCard2 = new BankCard(2l,"1111111111111112",2L,true, Date.valueOf("2021-04-05"));
        BankCard bankCard3 = new BankCard(3l,"1111111111111113",3L,true, Date.valueOf("2021-04-05"));
        BankCard bankCard4 = new BankCard(4l,"1111111111111114",3L,true, Date.valueOf("2021-04-05"));
        bankCardList.add(bankCard1);bankCardList.add(bankCard2);bankCardList.add(bankCard3);bankCardList.add(bankCard4);

        //        accountClientDAO= DAOFactory.getAccountClientDAO();
//        accountForCustomerTest = new AccountForCustomer(1L, "11111111111111111111",
//                1L, 100000L, true, Date.valueOf("2021-02-01"));

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getAllCard() throws ServiceException {
        Assert.assertEquals(serviceCard.getAllCard(),bankCardList);
    }

    @Test
    public void addCardForAccaunt() throws ServiceException {
        serviceCard.addCardForAccaunt(bankCard);
        Assert.assertEquals(serviceCard.getCardByNumber("5555555555555555"),bankCard);
    }

    @Test
    public void getBalanceAccountForCard() throws ServiceException {
        Long cash = 200000L;
        Assert.assertEquals(serviceCard.getBalanceAccountForCard("1111111111111112"),cash);
    }

    @Test
    public void addBalanceForCard() throws ServiceException {
        serviceCard.addBalanceForCard(99000L,"1111111111111111");
        Long cash = 199000L;
        Assert.assertEquals(serviceCard.getBalanceAccountForCard("1111111111111111"),cash);
    }

    @Test
    public void getCardByNumber() throws ServiceException {
        Assert.assertEquals(serviceCard.getCardByNumber("1111111111111111"),bankCardList.get(0));
    }
}