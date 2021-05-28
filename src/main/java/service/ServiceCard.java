package service;

import dao.AccountClientDAO;
import dao.CardDAO;

public class ServiceCard {
    CardDAO cardDAO;
    AccountClientDAO accountClientDAO;

    public ServiceCard() {
        cardDAO = new CardDAO();
        accountClientDAO = new AccountClientDAO();
    }

    public Long getBalanceAccountForCard(String cartNumber){

        return accountClientDAO.getId(cardDAO.getString(cartNumber).getIdAccountClient()).getBalance();
    }
}
