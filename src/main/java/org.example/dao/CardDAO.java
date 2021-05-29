package org.example.dao;

import org.example.entity.BankCard;
import org.example.connect.ConnectDB;
import org.example.connect.ConnectDB;
import org.example.dao.IDAO;
import org.example.entity.BankCard;
import org.h2.jdbc.JdbcSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements IDAO<BankCard> {
    Connection connection;

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
                bankCard.setIdAccountClient(resultSet.getInt("ID_ACCOUNT_CLIENT"));
                bankCard.setActive(resultSet.getBoolean("ACTIVE"));
                bankCard.setOpenDate(resultSet.getDate("OPEN_DATE"));
                bankCardList.add(bankCard);
            }
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
            preparedStatement.setLong(2, entity.getIdAccountClient());
            preparedStatement.setBoolean(3, entity.getActive());
            preparedStatement.setDate(4, entity.getOpenDate());
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            //throwables.printStackTrace();
            //System.out.println(throwables.getMessage());
            if (throwables.getErrorCode() == 23505){
                System.out.println("Карта существует №: "+entity.getCardNumber());
            }
        }

    }

    @Override
    public void remove(BankCard entity) {
        String sql = "DELETE FROM bank_card WHERE ID_CARD = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, entity.getIdCard());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public BankCard getIdAccount(Long id) {
        return null;
    }

    @Override
    public BankCard getString(String string) {
        String sql = "SELECT * FROM bank_card WHERE CARD_NUMBER = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, string);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            BankCard bankCard = new BankCard();
            bankCard.setIdCard(resultSet.getLong("ID_CARD"));
            bankCard.setCardNumber(resultSet.getString("CARD_NUMBER"));
            bankCard.setIdAccountClient(resultSet.getLong("ID_ACCOUNT_CLIENT"));
            bankCard.setActive(resultSet.getBoolean("ACTIVE"));
            bankCard.setOpenDate(resultSet.getDate("OPEN_DATE"));
            return bankCard;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public void update(BankCard entity) {

    }
}
