package org.example.service;

import org.example.dao.AccountClientDAO;
import org.example.dao.CardDAO;

public class ServiceCard {
    CardDAO cardDAO;
    AccountClientDAO accountClientDAO;

    public ServiceCard() {
        cardDAO = new CardDAO();
        accountClientDAO = new AccountClientDAO();
    }

    public Long getBalanceAccountForCard(String cartNumber) {
        //System.out.println(cardDAO.getString(cartNumber).getIdAccountClient());
        return accountClientDAO.getIdAccount(cardDAO.getString(cartNumber).getIdAccountClient()).getBalance();
    }
}
