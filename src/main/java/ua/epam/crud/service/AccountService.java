package ua.epam.crud.service;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.repository.AccountRepository;
import ua.epam.crud.repository.jdbc.JdbcAccountRepository;

import java.util.ArrayList;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new JdbcAccountRepository();
    }

    public Account getById(Long id) {
        return accountRepository.getById(id);
    }

    public ArrayList<Account> getAll() {
        return accountRepository.getAll();
    }

    public ArrayList<Account> create(Account account) {
        return accountRepository.create(account);
    }

    public void delete(Long id) {
        accountRepository.delete(id);
    }

    public ArrayList<Account> update(Account account) {
        return accountRepository.update(account);
    }

    public Account createAccount(Long idDeveloper, Long idStatus) {
        Account account = null;
        for (AccountStatus status : AccountStatus.values()) {
            if (status.getId().equals(idStatus)) {
                account = new Account(idDeveloper, status);
            }
        }
        return account;
    }
}
