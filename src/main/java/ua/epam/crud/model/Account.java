package ua.epam.crud.model;

public class Account {
    private int id;
    private AccountStatus accountStatus;

    public Account(int id, AccountStatus accountStatus) {
        this.id = id;
        this.accountStatus = accountStatus;
    }

    public int getId() {
        return id;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
