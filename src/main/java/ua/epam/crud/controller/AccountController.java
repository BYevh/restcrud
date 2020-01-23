package ua.epam.crud.controller;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.model.Skill;
import ua.epam.crud.service.AccountService;

import java.io.IOException;
import java.util.ArrayList;

public class AccountController {
    private final String INPUT_ID = "Input Developer_id:";
    private final String INPUT_ACCOUNT_STATUS = "Input number of ACCOUNT STATUS (1-ACTIVE, 2-BANNED, 3-DELETED):";

    private AccountService accountService;
    private UtilsController utilsController = new UtilsController();

    public AccountController() {
        this.accountService = new AccountService();
    }


    public ArrayList<Account> menuOfSkills() throws IOException {
        ArrayList<Account> listOfAccounts = accountService.getAll();


        int item;
        do {
            item = Integer.parseInt(utilsController.inputData());
            switch (item) {
                case 1: {                                           //1. Show all accounts.
                    System.out.println(listOfAccounts);
                    break;
                }
                case 2: {                                           //2. Show account by id.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    System.out.println(accountService.getById(id));
                    break;
                }

                case 3: {                                           //3. Add new account.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    System.out.println(INPUT_ACCOUNT_STATUS);
                    Account newAccount = createAccount(id, Long.parseLong(utilsController.inputData()));
                    listOfAccounts = accountService.create(newAccount);
                    System.out.println(listOfAccounts);
                    break;
                }
                case 4: {                                           //4. delete
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    accountService.delete(id);
                    listOfAccounts = accountService.getAll();
                    System.out.println(listOfAccounts);
                    break;
                }
                case 5: {                                           //5. Exit
                    break;
                }
            }
        } while (item != 5);

        return listOfAccounts;
    }

    private Account createAccount(Long idDeveloper, Long idStatus) {

        Account account = null;
        for (AccountStatus status : AccountStatus.values()) {
            if (status.getId().equals(idStatus)) {
                account = new Account(idDeveloper,status);
            }
        }
        return account;
    }
}
