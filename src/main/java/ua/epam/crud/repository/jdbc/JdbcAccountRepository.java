package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.repository.AccountRepository;

import java.sql.*;
import java.util.ArrayList;

import static ua.epam.crud.repository.jdbc.JdbcUtils.*;

public class JdbcAccountRepository implements AccountRepository {


    @Override
    public Account getById(Long id) {
        String sql = "SELECT * FROM accounts WHERE developer_id = " + id;
        return readFromDB(sql).get(0);
    }

    @Override
    public ArrayList<Account> getAll() {
        String sql = "SELECT * FROM accounts";
        return readFromDB(sql);
    }

    @Override
    public ArrayList<Account> create(Account account) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ArrayList<Account> update(Account account) {
        return null;
    }



    private ArrayList<Account> readFromDB(String sql) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long id = resultSet.getLong("developer_id");
                accounts.add(JdbcUtils.createAccountFromTableData(id));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }


}




