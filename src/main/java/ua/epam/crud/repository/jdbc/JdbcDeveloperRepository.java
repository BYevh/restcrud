package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.DeveloperRepository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeveloperRepository implements DeveloperRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public Developer getById(Long id) {
        String sql = "SELECT * FROM developers WHERE id = " + id;
        return readFromDB(sql).get(0);
    }

    @Override
    public List<Developer> getAll() {
        String sql = "SELECT * FROM developers";
        return readFromDB(sql);
    }

    @Override
    public List<Developer> create(Developer developer) {
        String sql = "INSERT INTO developers VALUE (" + developer.getId() + ", '" + developer.getName() + ", '" + developer.getAccount().getAccountStatus().getId() + "')";
        writeToDB(sql);
        return getAll();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM skills WHERE id=" + id;
        writeToDB(sql);
        sql = "DELETE FROM developers WHERE id=" + id;
        writeToDB(sql);

    }

    @Override
    public List<Developer> update(Developer developer) {
        String sql = "UPDATE skills SET name=" + developer.getName() + ", " + +", WHERE id=" + developer.getId();
        writeToDB(sql);
        return getAll();
    }


    private ArrayList<Developer> readFromDB(String sql) {
        ArrayList<Developer> developers = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                developers.add(new Developer(resultSet.getLong("id"), resultSet.getString("name")));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return developers;
    }

    private void writeToDB(String sql) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Account createAccount(int id) {
        String sql ="SELECT ALL FROM accounts WHERE developer_id=" + id;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
