package org.example.dao;

import org.example.entity.BankCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CardDAOTest {
    CardDAO cardDAO = new CardDAO();
    List<BankCard> bankCardList = new ArrayList<>();
    BankCard bankCard5;
    @Before
    public void setUp() throws Exception {
        BankCard bankCard1 = new BankCard(1l,"1111111111111111",1L,true, Date.valueOf("2021-03-06"));
        BankCard bankCard2 = new BankCard(2l,"1111111111111112",2L,true, Date.valueOf("2021-04-05"));
        BankCard bankCard3 = new BankCard(3l,"1111111111111113",3L,true, Date.valueOf("2021-04-05"));
        BankCard bankCard4 = new BankCard(4l,"1111111111111114",3L,true, Date.valueOf("2021-04-05"));
        bankCard5 = new BankCard(5l,"1111111111111115",3L,true, Date.valueOf("2021-04-05"));
        bankCardList.add(bankCard1);bankCardList.add(bankCard2);bankCardList.add(bankCard3);bankCardList.add(bankCard4);
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
        cardDAO.add(bankCard5);
        Assert.assertEquals(cardDAO.getById(5L),bankCard5);
    }

    @Test
    public void remove() {
        cardDAO.remove(bankCard5);
        Assert.assertNull(cardDAO.getById(5L));
    }

    @Test
    public void getById() {
        Assert.assertEquals(cardDAO.getById(bankCardList.get(0).getIdCard()),bankCardList.get(0));
    }

    @Test
    public void getByStringField() {
        Assert.assertEquals(cardDAO.getByStringField(bankCardList.get(0).getCardNumber()),bankCardList.get(0));
    }

    @Test
    public void update() {

    }
}