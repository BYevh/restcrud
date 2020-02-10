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
                accounts.add(createAccountFromTableData(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    protected Account createAccountFromTableData(long idDeveloper) {
        String sql = "SELECT * FROM accounts WHERE developer_id=" + idDeveloper;
        int idStatus = 0;
        try (Connection connection = jdbcUtils.getConnection()) {
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


}




