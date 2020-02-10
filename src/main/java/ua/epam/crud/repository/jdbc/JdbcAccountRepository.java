package ua.epam.crud.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.repository.AccountRepository;

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
        logger.debug("get Account by id");
        PreparedStatement preparedStatement;
        Account accountById = null;
        try (Connection connection = jdbcUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            accountById = readFromDB(preparedStatement).get(0);
        } catch (SQLException e) {
            logger.error("wrong sql query");
        } catch (IndexOutOfBoundsException e) {
            accountById = null;
            logger.info("Id doesn't exist: " + e.getMessage());
        }
        return accountById;
    }

    @Override
    public ArrayList<Account> getAll() {
        logger.debug("get all Accounts");
        PreparedStatement preparedStatement;
        ArrayList<Account> listOfAccounts = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            listOfAccounts = readFromDB(preparedStatement);
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return listOfAccounts;
    }

    @Override
    public ArrayList<Account> create(Account account) {
        logger.debug("create Account");
        PreparedStatement preparedStatement;
        try (Connection connection = jdbcUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setLong(1, account.getIdDeveloper());
            preparedStatement.setLong(2, account.getAccountStatus().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return getAll();
    }

    @Override
    public void delete(Long id) {
        logger.debug("delete Account by id");
        PreparedStatement preparedStatement;
        try (Connection connection = jdbcUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
    }

    @Override
    public ArrayList<Account> update(Account account) {
        logger.debug("update Account");
        PreparedStatement preparedStatement;
        try (Connection connection = jdbcUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setLong(1, account.getAccountStatus().getId());
            preparedStatement.setLong(2, account.getIdDeveloper());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return getAll();
    }


    private ArrayList<Account> readFromDB(PreparedStatement preparedStatement) {
        logger.debug("read Accounts from DB");
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("developer_id");
                accounts.add(createAccountFromTableData(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    protected Account createAccountFromTableData(long idDeveloper) {
        logger.debug("create Account From Table Data");
        PreparedStatement preparedStatement;
        int idStatus = 0;
        try (Connection connection = jdbcUtils.getConnection()) {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setLong(1, idDeveloper);
            ResultSet resultSet = preparedStatement.executeQuery();
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


}




