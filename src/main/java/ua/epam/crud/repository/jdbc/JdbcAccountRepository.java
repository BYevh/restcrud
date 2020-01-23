package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.repository.AccountRepository;

import java.util.ArrayList;

public class JdbcAccountRepository implements AccountRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public Account getById(Long id) {
        return null;
    }

    @Override
    public ArrayList<Account> getAll() {
        return null;
    }

    @Override
    public ArrayList<Account> create(Account account) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ArrayList<Account> update(Account account) {
        return null;
    }
}
