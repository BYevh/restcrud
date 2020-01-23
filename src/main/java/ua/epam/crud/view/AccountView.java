package ua.epam.crud.view;

import ua.epam.crud.controller.AccountController;
import ua.epam.crud.controller.SkillController;
import ua.epam.crud.model.Account;

import java.io.IOException;

public class AccountView {
    public AccountView() throws IOException {
        new UtilsView().showMenuAccounts();
        AccountController accountController = new AccountController();
        System.out.println(accountController.menuOfSkills());


    }
}
