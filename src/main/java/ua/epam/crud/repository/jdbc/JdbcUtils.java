package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import org.apache.commons.dbcp2.BasicDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    private static BasicDataSource ds = new BasicDataSource();
    private final static String PATH_TO_PROPERTIES = "./src/resources/application.properties";
    static {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        ds.setDriverClassName(properties.getProperty("database.driver"));
        ds.setUrl(properties.getProperty("database.url"));
        ds.setUsername(properties.getProperty("database.username"));
        ds.setPassword(properties.getProperty("database.password"));
    }

    protected void writeToDB(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    protected Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
