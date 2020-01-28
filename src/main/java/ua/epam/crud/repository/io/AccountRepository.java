package ua.epam.crud.repository.io;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.repository.GenericRepository;

import java.io.IOException;
import java.util.ArrayList;

public class AccountRepository implements GenericRepository<Account, Long> {
    private String fileName = "src/resources/IOFiles/accounts.txt";
    private UtilsRepository utilsRepository = new UtilsRepository(fileName);

    @Override
    public Account getById(Long id) throws IOException {
        ArrayList<Account> listOfAccounts = getAll();
        for (Account account : listOfAccounts) {
            if (account.getIdDeveloper().equals(id)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Account> getAll() throws IOException {

        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<String> stringOfAccounts = utilsRepository.readAllFile();
        for (String s : stringOfAccounts) {
            accounts.add(stringToAccount(s));
        }
        return accounts;
    }

    @Override
    public ArrayList<Account> create(Account newAccount) throws IOException {
        ArrayList<Account> listOfAccounts = getAll();
//if there is a account, then exit returning the existing set of account
        for (Account account : listOfAccounts) {
            if (account.getIdDeveloper().equals(newAccount.getIdDeveloper())) {
                return listOfAccounts;
            }
        }
// if not, add a account
        listOfAccounts.add(newAccount);
        utilsRepository.writeAllFile(listOfAccountsToStrings (listOfAccounts));
        return getAll();
    }

    @Override
    public void delete(Long id) throws IOException {
        ArrayList<Account> listOfAccounts = getAll();
        for (int i = 0; i < listOfAccounts.size(); i++) {
            Account account = listOfAccounts.get(i);
            if (account.getIdDeveloper().equals(id)) {
                listOfAccounts.remove(i);
                break;
            }
        }
        utilsRepository.writeAllFile(listOfAccountsToStrings (listOfAccounts));
    }

    @Override
    public ArrayList<Account> update(Account newAccount) throws IOException {
        ArrayList<Account> listOfAccounts = getAll();
        for (int i = 0; i < listOfAccounts.size(); i++) {
            Account account = listOfAccounts.get(i);
            if (account.getIdDeveloper().equals(newAccount.getIdDeveloper())) {
                listOfAccounts.remove(i);
                listOfAccounts.add(newAccount);
                break;
            }
        }
        utilsRepository.writeAllFile(listOfAccountsToStrings (listOfAccounts));
        return listOfAccounts;
    }

    private Account stringToAccount(String s) {
        String[] oneAccount = s.split("\\s");
        Long id = Long.valueOf(oneAccount[0]);
        Long status = Long.valueOf(oneAccount[1]);
        Account account = null;
        for (AccountStatus statusDev : AccountStatus.values()) {
            if (statusDev.getId().equals(status)) {
                account = new Account(id, statusDev);
            }
        }
        return account;
    }

    private String accountToString (Account account) {
        return (account.getIdDeveloper() + " " + account.getAccountStatus().getId() + "\n");
    }

    private ArrayList<String> listOfAccountsToStrings (ArrayList<Account> listOfAccounts){
        ArrayList<String> stringOfAccounts = new ArrayList<>();
        for (Account s : listOfAccounts) {
            stringOfAccounts.add(accountToString(s));
        }
        return stringOfAccounts;
    }

}