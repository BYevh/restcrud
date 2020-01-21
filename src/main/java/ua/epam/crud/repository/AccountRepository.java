package ua.epam.crud.repository;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.repository.GenericRepository;

public interface AccountRepository<A, L extends Number> extends GenericRepository <Account, Long> {
}
