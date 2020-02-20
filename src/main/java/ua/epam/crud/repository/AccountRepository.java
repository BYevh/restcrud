package ua.epam.crud.repository;

import ua.epam.crud.model.Account;

import java.util.ArrayList;

public interface AccountRepository extends GenericRepository<Account, Long> {

    @Override
    Account getById(Long id);

    @Override
    ArrayList<Account> getAll();

    @Override
    ArrayList<Account> create(Account account);

    @Override
    void delete(Long id);

    @Override
    ArrayList<Account> update(Account account);
}
