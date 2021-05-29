package org.example.service;

import org.example.dao.AccountClientDAO;
import org.example.dao.CardDAO;
import org.example.entity.AccountForCustomer;
import org.example.entity.BankCard;

import java.util.List;

public class ServiceCard {
    CardDAO cardDAO;
    AccountClientDAO accountClientDAO;

    public ServiceCard() {
        cardDAO = new CardDAO();
        accountClientDAO = new AccountClientDAO();
    }
    public void addCardForAccaunt(BankCard bankCard){
        try {
            Long accountId = accountClientDAO.getIdAccount(bankCard.getIdAccountClient()).getIdAccountCl();
        }catch (NullPointerException e){
            System.out.println("Счет не существует");
        }
        cardDAO.add(bankCard);
    }
    public List<BankCard> getAllCard(){
        return cardDAO.getAll();
    }
    public void addBalanceForCard(Long cash,String  cartNumber){
        AccountForCustomer accountForCustomer = accountClientDAO.getIdAccount(cardDAO.getString(cartNumber).getIdAccountClient());
        accountForCustomer.setBalance(cash);
        accountClientDAO.update(accountForCustomer);
    }
    public Long getBalanceAccountForCard(String cartNumber) {
        return accountClientDAO.getIdAccount(cardDAO.getString(cartNumber).getIdAccountClient()).getBalance();
    }
}
