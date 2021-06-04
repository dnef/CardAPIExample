package org.example.service;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.dao.AccountClientDAO;
import org.example.dao.CardDAO;
import org.example.dao.DAOFactory;
import org.example.entity.AccountForCustomer;
import org.example.entity.BankCard;
import org.example.exception.DaoException;
import org.example.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;


public class ServiceCard {
    private CardDAO cardDAO;
    private AccountClientDAO accountClientDAO;
    Log logger = LogFactory.getLog(CardDAO.class);

    public ServiceCard() {
        cardDAO = DAOFactory.getCardDAO();
        accountClientDAO = DAOFactory.getAccountClientDAO();
    }

    public void addCardForAccaunt(BankCard bankCard) throws ServiceException {

        Long accountNumberId;
        try {
            accountNumberId = accountClientDAO.getByStringField(bankCard.getAccountNumber()).getIdAccountCl();
            bankCard.setAccountNumberId(accountNumberId);
            cardDAO.add(bankCard);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public List<BankCard> getAllCard() throws ServiceException {
        List<BankCard> resultBankCard = new ArrayList<>();
        try {
            for (BankCard bankCard : cardDAO.getAll()) {
                String accountNumber = accountClientDAO.getById(bankCard.getAccountNumberId()).getAccountNumber();
                bankCard.setAccountNumber(accountNumber);
                resultBankCard.add(bankCard);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultBankCard;
    }

    public void addBalanceForCard(Long cash, String cartNumber) throws ServiceException {
        try {
            AccountForCustomer accountForCustomer = accountClientDAO.getById(cardDAO.getByStringField(cartNumber).getAccountNumberId());
            Long balance = accountForCustomer.getBalance() + cash;
            System.out.println(balance);
            accountForCustomer.setBalance(balance);
            accountClientDAO.update(accountForCustomer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long getBalanceAccountForCard(String cartNumber) throws ServiceException {
        try {
            return accountClientDAO.getById(cardDAO.getByStringField(cartNumber).getAccountNumberId()).getBalance();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public BankCard getCardByNumber(String cartNumber) throws ServiceException {
        try {
            return cardDAO.getByStringField(cartNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
