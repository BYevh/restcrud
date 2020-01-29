package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.repository.AccountRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JdbcAccountRepository implements AccountRepository {
    JdbcUtils jdbcUtils = new JdbcUtils();

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
        String sql = "INSERT INTO accounts VALUE (" + account.getIdDeveloper() + ", " + account.getAccountStatus().getId() + ")";
        jdbcUtils.writeToDB(sql);
        return getAll();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM accounts WHERE developer_id=" + id;
        jdbcUtils.writeToDB(sql);
    }

    @Override
    public ArrayList<Account> update(Account account) {
        String sql = "UPDATE accounts SET id_status=" + account.getAccountStatus().getId() + " WHERE developer_id=" + account.getIdDeveloper();
        jdbcUtils.writeToDB(sql);
        return getAll();
    }


    private ArrayList<Account> readFromDB(String sql) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Connection connection = jdbcUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long id = resultSet.getLong("developer_id");
                accounts.add(jdbcUtils.createAccountFromTableData(id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }


}




