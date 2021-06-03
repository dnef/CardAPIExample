package org.example.dao;

import junit.framework.TestCase;
import org.example.common.CreateDB;
import org.example.common.GlobalConfig;
import org.example.entity.AccountForCustomer;
import org.example.exception.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class AccountClientDAOTest {
    AccountClientDAO accountClientDAO;
    AccountForCustomer accountForCustomerTest;
    @Before
    public void setUp() throws Exception {
        GlobalConfig.initGlobalConfigTest("src/main/resources/configTest.properties");
        CreateDB.createDB();
        accountClientDAO=DAOFactory.getAccountClientDAO();
        accountForCustomerTest = new AccountForCustomer(1L, "11111111111111111111",
                1L, 100000L, true, Date.valueOf("2021-02-01"));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() throws DaoException {
        Assert.assertEquals(accountClientDAO.getById(1L),accountForCustomerTest);
    }

    @Test
    public void getByStringField() throws DaoException {
        Assert.assertEquals(accountClientDAO.getByStringField("11111111111111111111"),accountForCustomerTest);
    }

    @Test
    public void update() throws DaoException {
        accountForCustomerTest.setBalance(200000L);
        accountClientDAO.update(accountForCustomerTest);
        Assert.assertEquals(accountClientDAO.getById(1L),accountForCustomerTest);

        accountForCustomerTest.setBalance(100000L);
        accountClientDAO.update(accountForCustomerTest);
    }
}