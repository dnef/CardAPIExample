package org.example.dao;

import org.example.connect.ConnectDB;
import org.example.entity.AccountForCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountClientDAO implements IDAO<AccountForCustomer> {
    Connection connection;

    public AccountClientDAO() {
        this.connection = new ConnectDB().getConnection();
    }

    @Override
    public List<AccountForCustomer> getAll() {
        return null;
    }

    @Override
    public void add(AccountForCustomer entity) {

    }

    @Override
    public void remove(AccountForCustomer entity) {

    }

    @Override
    public AccountForCustomer getById(Long id) {

        String sql = "SELECT * FROM BANK_ACCOUNT_CLIENT WHERE ID_ACCOUNT_CL= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            AccountForCustomer accountForCustomer = new AccountForCustomer();
            accountForCustomer.setIdAccountCl(resultSet.getLong("ID_ACCOUNT_CL"));
            accountForCustomer.setAccountNumber(resultSet.getString("ACCOUNT_NUMBER"));
            accountForCustomer.setIdClient(resultSet.getLong("ID_CLIENT"));
            accountForCustomer.setBalance(resultSet.getLong("BALANCE"));
            accountForCustomer.setActive(resultSet.getBoolean("ACTIVE"));
            accountForCustomer.setOpenDate(resultSet.getDate("OPEN_DATE"));
            connection.close();
            return accountForCustomer;

        } catch (SQLException throwables) {
            System.out.println("Ошибка SQL : " + throwables.getErrorCode());
            //throwables.printStackTrace();
        }
        return null;

    }

    @Override
    public AccountForCustomer getByStringField(String numberAccount) {

        String sql = "SELECT * FROM BANK_ACCOUNT_CLIENT WHERE ACCOUNT_NUMBER= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, numberAccount);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            AccountForCustomer accountForCustomer = new AccountForCustomer();
            accountForCustomer.setIdAccountCl(resultSet.getLong("ID_ACCOUNT_CL"));
            accountForCustomer.setAccountNumber(resultSet.getString("ACCOUNT_NUMBER"));
            accountForCustomer.setIdClient(resultSet.getLong("ID_CLIENT"));
            accountForCustomer.setBalance(resultSet.getLong("BALANCE"));
            accountForCustomer.setActive(resultSet.getBoolean("ACTIVE"));
            accountForCustomer.setOpenDate(resultSet.getDate("OPEN_DATE"));
            connection.close();
            return accountForCustomer;
        } catch (SQLException throwables) {
            System.out.println("Ошибка SQL : " + throwables.getErrorCode());
            //throwables.printStackTrace();

        }
        return null;
    }

    @Override
    public void update(AccountForCustomer entity) {
        String sql = "UPDATE BANK_ACCOUNT_CLIENT SET BALANCE = ?, ACTIVE = ? WHERE ID_ACCOUNT_CL = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, entity.getBalance());
            preparedStatement.setBoolean(2, entity.getActive());
            preparedStatement.setLong(3, entity.getIdAccountCl());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("Ошибка SQL : " + throwables.getErrorCode());
        }
    }
}
