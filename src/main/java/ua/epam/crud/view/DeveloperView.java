package ua.epam.crud.view;

import ua.epam.crud.controller.DeveloperController;

import java.io.IOException;

public class DeveloperView {

    public DeveloperView() throws IOException {
        new UtilsView().showMenuDevelopers();
        DeveloperController developerController = new DeveloperController();
        System.out.println(developerController.menuOfDevelopers());
    }


}