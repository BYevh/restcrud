package ua.epam.crud.repository.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static BasicDataSource ds = new BasicDataSource();
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
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


    protected Connection getConnection() throws SQLException {
        logger.info("get connection");
        return ds.getConnection();
    }

}
