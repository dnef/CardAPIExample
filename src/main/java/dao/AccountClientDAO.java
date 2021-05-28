package dao;

import connect.ConnectDB;
import entity.BankAccountClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountClientDAO implements IDAO<BankAccountClient>{
    Connection connection;
    public AccountClientDAO() {
        this.connection = new ConnectDB().getConnection();
    }
    @Override
    public List<BankAccountClient> getAll() {
        return null;
    }

    @Override
    public void add(BankAccountClient entity) {

    }

    @Override
    public void remove(BankAccountClient entity) {

    }

    @Override
    public BankAccountClient getId(Long id) {

        String sql = "SELECT * FROM BANK_ACCOUNT_CLIENT WHERE ID_ACCOUNT_CL= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            BankAccountClient bankAccountClient = new BankAccountClient();


            return bankAccountClient;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public BankAccountClient getString(String string) {

        return null;
    }
}
