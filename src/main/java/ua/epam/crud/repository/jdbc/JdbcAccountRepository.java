package ua.epam.crud.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.crud.model.Account;
import ua.epam.crud.repository.AccountRepository;
import ua.epam.crud.service.DeveloperService;

import java.sql.*;
import java.util.ArrayList;

public class JdbcAccountRepository implements AccountRepository {
    JdbcUtils jdbcUtils = new JdbcUtils();
    public static final Logger logger = LoggerFactory.getLogger(JdbcAccountRepository.class);
    private final String SELECT_BY_ID_QUERY = "SELECT * FROM accounts WHERE developer_id=?";
    private final String SELECT_ALL_QUERY = "SELECT * FROM accounts";
    private final String INSERT_QUERY = "INSERT INTO accounts VALUE ( ? , ?)";
    private final String DELETE_QUERY = "DELETE FROM accounts WHERE developer_id=?";
    private final String UPDATE_QUERY = "UPDATE accounts SET id_status=? WHERE developer_id=?";


    @Override
    public Account getById(Long id) {
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            logger.debug("wrong sql query");
        }
        if (preparedStatement == null){
            logger.error("preparedStatement = null");
            return  new Account(null ,null);
        }
        return readFromDB(preparedStatement).get(0);
    }

    @Override
    public ArrayList<Account> getAll() {
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        } catch (SQLException e) {
            logger.debug("wrong sql query");
        }
        if (preparedStatement == null){
            logger.error("preparedStatement = null");
            return  new ArrayList<>();
        }
        return readFromDB(preparedStatement);
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


    private ArrayList<Account> readFromDB(PreparedStatement preparedStatement) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
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




