package org.example.service;

import org.example.dao.AccountClientDAO;
import org.example.dao.CardDAO;
import org.example.entity.AccountForCustomer;
import org.example.entity.BankCard;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServiceCard {
    CardDAO cardDAO;
    AccountClientDAO accountClientDAO;
    Logger logger = Logger.getLogger(CardDAO.class.getName());

    public ServiceCard() {
        cardDAO = new CardDAO();
        accountClientDAO = new AccountClientDAO();
    }
    public void addCardForAccaunt(BankCard bankCard){
        try {
            Long  accountNumberId = accountClientDAO.getByStringField(bankCard.getAccountNumber()).getIdAccountCl();
            bankCard.setAccountNumberId(accountNumberId);
        }catch (NullPointerException e){
            System.out.println("Счет не существует");
        }
        cardDAO.add(bankCard);
    }
    public List<BankCard> getAllCard(){
        List<BankCard> resultBankCard = new ArrayList<>();
        for (BankCard bankCard:cardDAO.getAll()){
            String accountNumber = accountClientDAO.getById(bankCard.getAccountNumberId()).getAccountNumber();
            bankCard.setAccountNumber(accountNumber);
            resultBankCard.add(bankCard);
        }
        return resultBankCard;
    }
    public void addBalanceForCard(Long cash,String  cartNumber){
        AccountForCustomer accountForCustomer = accountClientDAO.getById(cardDAO.getByStringField(cartNumber).getAccountNumberId());
        Long balance = accountForCustomer.getBalance()+cash;
        System.out.println(balance);
        accountForCustomer.setBalance(balance);
        accountClientDAO.update(accountForCustomer);
    }
    public Long getBalanceAccountForCard(String cartNumber) {
        logger.info("RUN getBalanceAccountForCard");
        return accountClientDAO.getById(cardDAO.getByStringField(cartNumber).getAccountNumberId()).getBalance();
    }

    public BankCard getCardByNumber(String cartNumber){
        return cardDAO.getByStringField(cartNumber);
    }

}
