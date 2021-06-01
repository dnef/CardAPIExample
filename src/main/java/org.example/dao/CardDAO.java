package org.example.dao;

import org.example.connect.ConnectDB;
import org.example.entity.BankCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CardDAO implements IDAO<BankCard> {
    Connection connection;
    Logger logger = Logger.getLogger(CardDAO.class.getName());
    public CardDAO() {
        this.connection = new ConnectDB().getConnection();
    }

    @Override
    public List<BankCard> getAll() {
        List<BankCard> bankCardList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank_card")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BankCard bankCard = new BankCard();
                bankCard.setIdCard(resultSet.getInt("ID_CARD"));
                bankCard.setCardNumber(resultSet.getString("CARD_NUMBER"));
                bankCard.setAccountNumberId(resultSet.getLong("ACCOUNT_NUMBER_ID"));
                bankCard.setActive(resultSet.getBoolean("ACTIVE"));
                bankCard.setOpenDate(resultSet.getDate("OPEN_DATE"));
                bankCardList.add(bankCard);
            }
            logger.info("DAO getAllCard run");
            return bankCardList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(BankCard entity) {
        String sql = "INSERT INTO bank_card VALUES (null,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getCardNumber());
            preparedStatement.setLong(2, entity.getAccountNumberId());
            preparedStatement.setBoolean(3, entity.getActive());
            preparedStatement.setDate(4, entity.getOpenDate());
            preparedStatement.executeUpdate();
            logger.info("DAO addCard run");
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            //System.out.println(throwables.getMessage());
            if (throwables.getErrorCode() == 23505) {
                System.out.println("Карта существует №: " + entity.getCardNumber());
            }
        }

    }

    @Override
    public void remove(BankCard entity) {
        String sql = "DELETE FROM bank_card WHERE ID_CARD = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, entity.getIdCard());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public BankCard getById(Long id) {
        String sql = "SELECT * FROM BANK_CARD WHERE ID_CARD = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            BankCard bankCard = new BankCard();
            bankCard.setIdCard(resultSet.getLong("ID_CARD"));
            bankCard.setCardNumber(resultSet.getString("CARD_NUMBER"));
            bankCard.setAccountNumberId(resultSet.getLong("ACCOUNT_NUMBER_ID"));
            bankCard.setActive(resultSet.getBoolean("ACTIVE"));
            bankCard.setOpenDate(resultSet.getDate("OPEN_DATE"));
            logger.info("DAO getCardByCardNumber run");
            return bankCard;
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            logger.info(throwables.toString());
            if (throwables.getErrorCode() == 2000) {
                System.out.println("Карта не существует");
            }
        }
        return null;
    }

    @Override
    public BankCard getByStringField(String cardNumber) {
        String sql = "SELECT * FROM BANK_CARD WHERE CARD_NUMBER = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            BankCard bankCard = new BankCard();
            bankCard.setIdCard(resultSet.getLong("ID_CARD"));
            bankCard.setCardNumber(resultSet.getString("CARD_NUMBER"));
            bankCard.setAccountNumberId(resultSet.getLong("ACCOUNT_NUMBER_ID"));
            bankCard.setActive(resultSet.getBoolean("ACTIVE"));
            bankCard.setOpenDate(resultSet.getDate("OPEN_DATE"));
            logger.info("DAO getCardByCardNumber run");
            return bankCard;
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            logger.info(throwables.toString());
            if (throwables.getErrorCode() == 2000){
                System.out.println("Карта не существует");
            }
        }
        return null;
    }

    @Override
    public void update(BankCard entity) {
        String sql = "UPDATE BANK_CARD SET ID_CARD=null, CARD_NUMBER = ?,ACCOUNT_NUMBER_ID=?,ACTIVE=?,OPEN_DATE=? WHERE ID_CARD=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getCardNumber());
            preparedStatement.setLong(2, entity.getAccountNumberId());
            preparedStatement.setBoolean(3, entity.getActive());
            preparedStatement.setDate(4, entity.getOpenDate());
            preparedStatement.setLong(5,entity.getIdCard());
            preparedStatement.executeUpdate();
            logger.info("DAO updateCard run");
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            //System.out.println(throwables.getMessage());
            if (throwables.getErrorCode() == 2000) {
                System.out.println("Карта отсутствует №: " + entity.getCardNumber());
            }
        }
    }
}
