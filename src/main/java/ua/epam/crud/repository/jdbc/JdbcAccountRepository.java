package ua.epam.crud.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.crud.model.Account;
import ua.epam.crud.repository.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement preparedStatement = null;
        Account accountById = null;
        try (Connection connection = jdbcUtils.getConnection()){
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            accountById = readFromDB(preparedStatement).get(0);
        } catch (SQLException e) {
            logger.error("wrong sql query");
        } catch (IndexOutOfBoundsException e){
            accountById =null;
            logger.error("wrong sql query");
        }
        return accountById;
    }

    @Override
    public ArrayList<Account> getAll() {
        PreparedStatement preparedStatement = null;
        ArrayList<Account> listOfAccounts = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection()){
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
                accounts.add(jdbcUtils.createAccountFromTableData(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }


}




