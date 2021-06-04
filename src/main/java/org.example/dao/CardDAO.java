package org.example.dao;


import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.connect.ConnectionBuilder;
import org.example.connect.ConnectionBuilderFactory;
import org.example.entity.BankCard;
import org.example.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CardDAO implements IDAO<BankCard> {

    Log logger = LogFactory.getLog(CardDAO.class);
    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public List<BankCard> getAll() throws DaoException {
        List<BankCard> bankCardList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank_card")) {
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
            resultSet.close();
            return bankCardList;
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new DaoException(throwables);
        }
    }

    @Override
    public void add(BankCard entity) throws DaoException {
        String sql = "INSERT INTO bank_card VALUES (null,?,?,?,?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getCardNumber());
            preparedStatement.setLong(2, entity.getAccountNumberId());
            preparedStatement.setBoolean(3, entity.getActive());
            preparedStatement.setDate(4, entity.getOpenDate());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new DaoException(throwables);
        }

    }

    @Override
    public void remove(BankCard entity) throws DaoException {
        String sql = "DELETE FROM bank_card WHERE ID_CARD = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, entity.getIdCard());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new DaoException(throwables);
        }
    }

    @Override
    public BankCard getById(Long id) throws DaoException {
        String sql = "SELECT * FROM BANK_CARD WHERE ID_CARD = ?";
        BankCard bankCard;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bankCard = new BankCard();
                bankCard.setIdCard(resultSet.getLong("ID_CARD"));
                bankCard.setCardNumber(resultSet.getString("CARD_NUMBER"));
                bankCard.setAccountNumberId(resultSet.getLong("ACCOUNT_NUMBER_ID"));
                bankCard.setActive(resultSet.getBoolean("ACTIVE"));
                bankCard.setOpenDate(resultSet.getDate("OPEN_DATE"));
                resultSet.close();
                return bankCard;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new DaoException(throwables);
        }
    }

    @Override
    public BankCard getByStringField(String cardNumber) throws DaoException {
        String sql = "SELECT * FROM BANK_CARD WHERE CARD_NUMBER = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            BankCard bankCard = new BankCard();
            bankCard.setIdCard(resultSet.getLong("ID_CARD"));
            bankCard.setCardNumber(resultSet.getString("CARD_NUMBER"));
            bankCard.setAccountNumberId(resultSet.getLong("ACCOUNT_NUMBER_ID"));
            bankCard.setActive(resultSet.getBoolean("ACTIVE"));
            bankCard.setOpenDate(resultSet.getDate("OPEN_DATE"));
            return bankCard;
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(BankCard entity) throws DaoException {
        String sql = "UPDATE BANK_CARD SET ID_CARD=?, CARD_NUMBER = ?,ACCOUNT_NUMBER_ID=?,ACTIVE=?,OPEN_DATE=? WHERE ID_CARD=?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, entity.getIdCard());
            preparedStatement.setString(2, entity.getCardNumber());
            preparedStatement.setLong(3, entity.getAccountNumberId());
            preparedStatement.setBoolean(4, entity.getActive());
            preparedStatement.setDate(5, entity.getOpenDate());
            preparedStatement.setLong(6, entity.getIdCard());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throw new DaoException(throwables);
        }
    }
}
