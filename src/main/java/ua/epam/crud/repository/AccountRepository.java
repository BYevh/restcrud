package ua.epam.crud.repository;

import ua.epam.crud.model.Account;

public interface AccountRepository<A, L extends Number> extends GenericRepository <Account, Long> {
}
