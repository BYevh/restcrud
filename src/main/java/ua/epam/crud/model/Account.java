package ua.epam.crud.model;

public class Account {
    private Long idDeveloper;
    private AccountStatus accountStatus;

    public Account(Long idDeveloper, AccountStatus accountStatus) {
        this.idDeveloper = idDeveloper;
        this.accountStatus = accountStatus;
    }

    public Long getIdDeveloper() {
        return idDeveloper;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Account: " +
                "idDeveloper=" + idDeveloper +
                ", accountStatus = " + accountStatus;
    }
}
