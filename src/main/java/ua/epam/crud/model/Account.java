package ua.epam.crud.model;

public class Account {
    public Account(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    private AccountStatus accountStatus;


    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "accountStatus=" + accountStatus;
    }
}
