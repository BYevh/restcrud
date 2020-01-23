package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;

import java.sql.*;

public class JdbcUtils {

    protected static final String URL = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
    protected static final String USER = "root";
    protected static final String PASSWORD = "root";
    protected static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void writeToDB(String sql) {

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static Account createAccountFromTableData(long idDeveloper) {
        String sql = "SELECT * FROM accounts WHERE developer_id=" + idDeveloper;
        int idStatus = 0;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                idStatus = resultSet.getInt("id_status");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Account account = null;
        for (AccountStatus accountStatus : AccountStatus.values()) {
            if (accountStatus.getId() == idStatus) {
                account = new Account(idDeveloper, accountStatus);
            }
        }
        return account;
    }
}
