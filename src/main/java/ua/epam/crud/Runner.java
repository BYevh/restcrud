package ua.epam.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.crud.controller.UtilsController;
import ua.epam.crud.service.DeveloperService;
import ua.epam.crud.view.AccountView;
import ua.epam.crud.view.DeveloperView;
import ua.epam.crud.view.SkillView;

import java.io.IOException;

public class Runner {

    public static final Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    public static void main(String[] args) throws IOException {

        logger.info("logger started");

        UtilsController utilsController = new UtilsController();
        final String MENU_ITEM_1 = "1. Show menu Skills.";
        final String MENU_ITEM_2 = "2. Show menu Accounts";
        final String MENU_ITEM_3 = "3. Show menu Developers.";
        final String MENU_ITEM_4 = "4. Exit.";

        System.out.println(MENU_ITEM_1);
        System.out.println(MENU_ITEM_2);
        System.out.println(MENU_ITEM_3);
        System.out.println(MENU_ITEM_4);


        int item;
        do {
            item = Integer.parseInt(utilsController.inputData());
            switch (item) {
                case 1: {
                    SkillView skillView = new SkillView();
                    break;
                }
                case 2: {
                    AccountView accountView = new AccountView();
                    break;
                }
                case 3: {
                    DeveloperView developerView = new DeveloperView();
                    break;
                }
                case 4: {
                    break;
                }
            }
        } while (item != 4);
    }
}
