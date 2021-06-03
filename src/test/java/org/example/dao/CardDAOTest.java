package org.example.dao;

import org.example.common.CreateDB;
import org.example.common.GlobalConfig;
import org.example.entity.BankCard;
import org.example.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CardDAOTest {
    CardDAO cardDAO;
    List<BankCard> bankCardList = new ArrayList<>();
    BankCard bankCard5;

    @Before
    public void setUp() throws Exception {
        GlobalConfig.initGlobalConfig("src/main/resources/configTest.properties");
        CreateDB.createDB();
        cardDAO = DAOFactory.getCardDAO();
        BankCard bankCard1 = new BankCard(1l, "1111111111111111", 1L, true, Date.valueOf("2021-03-06"));
        BankCard bankCard2 = new BankCard(2l, "1111111111111112", 2L, true, Date.valueOf("2021-04-05"));
        BankCard bankCard3 = new BankCard(3l, "1111111111111113", 3L, true, Date.valueOf("2021-04-05"));
        BankCard bankCard4 = new BankCard(4l, "1111111111111114", 3L, true, Date.valueOf("2021-04-05"));
        bankCard5 = new BankCard(5l, "1111111111111115", 3L, true, Date.valueOf("2021-04-05"));
        bankCardList.add(bankCard1);
        bankCardList.add(bankCard2);
        bankCardList.add(bankCard3);
        bankCardList.add(bankCard4);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() throws DaoException {
        Assert.assertEquals(cardDAO.getAll(), bankCardList);
    }

    @Test
    public void add() throws DaoException {
        cardDAO.add(bankCard5);
        Assert.assertEquals(cardDAO.getById(5L), bankCard5);
        cardDAO.remove(bankCard5);
    }

    @Test
    public void remove() throws DaoException {
        cardDAO.remove(cardDAO.getById(4L));
        Assert.assertNull(cardDAO.getById(4L));
    }

    @Test
    public void getById() throws DaoException {
        Assert.assertEquals(cardDAO.getById(bankCardList.get(1).getIdCard()), bankCardList.get(1));
    }

    @Test
    public void getByStringField() throws DaoException {
        Assert.assertEquals(cardDAO.getByStringField(bankCardList.get(0).getCardNumber()), bankCardList.get(0));
    }

    @Test
    public void update() throws DaoException {
        bankCardList.get(0).setAccountNumberId(3L);
        cardDAO.update(bankCardList.get(0));
        Assert.assertEquals(bankCardList.get(0).getAccountNumber(), cardDAO.getById(1L).getAccountNumber());
    }
}