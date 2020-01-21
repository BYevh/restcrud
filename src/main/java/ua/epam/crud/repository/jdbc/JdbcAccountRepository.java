package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.repository.AccountRepository;

import java.io.IOException;
import java.util.List;

public class JdbcAccountRepository implements AccountRepository <Account, Long>{
    @Override
    public Account getById(Long id) throws IOException {
        return null;
    }

    @Override
    public List<Account> getAll() throws IOException {
        return null;
    }

    @Override
    public List<Account> create(Account account) throws IOException {
        return null;
    }

    @Override
    public void delete(Long id) throws IOException {

    }

    @Override
    public List<Account> update(Account account) {
        return null;
    }
}
