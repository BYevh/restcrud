package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;

import java.sql.*;

public class JdbcUtils {

    protected final String DRIVER = "com.mysql.cj.jdbc.Driver";
    protected final String URL = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
    protected final String USER = "root";
    protected final String PASSWORD = "root";

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

    protected Account createAccountFromTableData(long idDeveloper) {
        String sql = "SELECT * FROM accounts WHERE developer_id=" + idDeveloper;
        int idStatus = 0;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                idStatus = resultSet.getInt("id_status");
            }

        } catch (SQLException e) {
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

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
