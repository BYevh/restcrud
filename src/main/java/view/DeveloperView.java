package view;

import controller.DeveloperController;

import java.io.IOException;

public class DeveloperView {

    private final String MENU_ITEM_1 = "1. Show all developers.";
    private final String MENU_ITEM_2 = "2. Show developer by ID";
    private final String MENU_ITEM_3 = "3. Add new developer.";
    private final String MENU_ITEM_4 = "4. Update developer.";
    private final String MENU_ITEM_5 = "5. Delete a developer.";
    private final String MENU_ITEM_6 = "6. Exit.";


    public DeveloperView() throws IOException {
        System.out.println(MENU_ITEM_1);
        System.out.println(MENU_ITEM_2);
        System.out.println(MENU_ITEM_3);
        System.out.println(MENU_ITEM_4);
        System.out.println(MENU_ITEM_5);
        System.out.println(MENU_ITEM_6);
        DeveloperController developerController = new DeveloperController();
        System.out.println(developerController.menuOfDevelopers());

    }
}