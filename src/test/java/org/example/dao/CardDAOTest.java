package org.example.dao;

import junit.framework.TestCase;
import org.example.connect.ConnectDB;
import org.example.entity.BankCard;
import org.example.service.ServiceCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CardDAOTest {
    CardDAO cardDAO = new CardDAO();
    List<BankCard> bankCardList = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        BankCard bankCard1 = new BankCard(1l,"2635678946123467","12345367281835461728",true, Date.valueOf("2021-03-06"));
        BankCard bankCard2 = new BankCard(2l,"2635678940023467","12345367281835461728",true, Date.valueOf("2021-04-05"));
        BankCard bankCard3 = new BankCard(3l,"2630078946123467","89356726789534678356",true, Date.valueOf("2021-04-05"));
        BankCard bankCard4 = new BankCard(4l,"2635000946123467","89356726755534678356",true, Date.valueOf("2021-04-05"));
        BankCard bankCard5 = new BankCard(5l,"1111111111111113","89356726755534678356",true, Date.valueOf("2021-05-31"));
        bankCardList.add(bankCard1);bankCardList.add(bankCard2);bankCardList.add(bankCard3);bankCardList.add(bankCard4);bankCardList.add(bankCard5);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() {
        Assert.assertEquals(cardDAO.getAll(),bankCardList);
    }

    @Test
    public void add() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void getIdAccount() {
    }

    @Test
    public void getByStringField() {
    }

    @Test
    public void update() {
    }
}