package ua.epam.crud.repository;

import ua.epam.crud.model.Account;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface AccountRepository extends GenericRepository <Account, Long> {

    @Override
    default Account getById(Long id) {
        return null;
    }

    @Override
    default ArrayList<Account> getAll() {
        return null;
    }

    @Override
    default ArrayList<Account> create(Account account) {
        return null;
    }

    @Override
    default void delete(Long id) {

    }

    @Override
    default ArrayList<Account> update(Account account) {
        return null;
    }
}
