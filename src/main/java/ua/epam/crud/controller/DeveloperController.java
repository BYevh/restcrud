package ua.epam.crud.controller;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.jdbc.JdbcSkillRepository;
import ua.epam.crud.service.DeveloperService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class DeveloperController {
    private final String INPUT_ID = "Input ID:";
    private final String INPUT_NAME = "Input NAME (FirstName LastName):";
    private final String INPUT_SKILLS = "Input SKILL (ID Skills separated by spaces):";
    private final String INPUT_ACCOUNT_STATUS = "Input number of ACCOUNT STATUS (1-ACTIVE, 2-BANNED, 3-DELETED):";
    private final String DEVELOPER_CREATED = "Developer Created";
    private final String DEVELOPER_UPDATED = "Developer Updated";
    private final String DEVELOPER_DELETED = "Developer Deleted";
    private final String LIST_OF_DEVELOPERS = "Updated list of developers:";

    private DeveloperService developerService = new DeveloperService();
    private UtilsController utilsController = new UtilsController();

    public DeveloperController() {
    }


    public ArrayList<Developer> menuOfDevelopers() {
        int item;
        do {
            item = Integer.parseInt(utilsController.inputData());
            switch (item) {
                case 1: {                                           //    1. Show all developers
                    System.out.println(developerService.getAll());
                    break;
                }

                case 2: {                                           //    2. Show developer by ID
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    System.out.println(developerById(id));
                    break;
                }

                case 3: {                                           //    3. Add new developer.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());

                    System.out.println(INPUT_NAME);
                    String name = utilsController.inputData();

                    System.out.println(INPUT_SKILLS);
                    HashSet<Skill> setOfSkillsThisDeveloper = createSetOfSkill(utilsController.inputData());

                    System.out.println(INPUT_ACCOUNT_STATUS);
                    Account account = createAccount(id, Long.parseLong(utilsController.inputData()));

                    developerService.create(new Developer(id, name, setOfSkillsThisDeveloper, account));

                    System.out.println(DEVELOPER_CREATED);

                    break;
                }

                case 4: {                                           //    4. Update developer.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    System.out.println(developerById(id));

                    System.out.println("\n" + INPUT_NAME);
                    String name = utilsController.inputData();

                    System.out.println(INPUT_SKILLS);
                    System.out.println(new JdbcSkillRepository().getAll());
                    HashSet<Skill> setOfSkillsThisDeveloper = createSetOfSkill(utilsController.inputData());

                    System.out.println(INPUT_ACCOUNT_STATUS);
                    Account account = createAccount(id, Long.parseLong(utilsController.inputData()));

                    developerService.update(new Developer(id, name, setOfSkillsThisDeveloper, account));

                    System.out.println(DEVELOPER_UPDATED);

                    break;
                }

                case 5: {                                           //    5. Delete a developer.
                    System.out.println(INPUT_ID);
                    developerService.delete(Long.parseLong(utilsController.inputData()));
                    System.out.println(DEVELOPER_DELETED);
                    break;
                }

                case 6: {                                           //    6. Exit.
                    break;
                }
            }
        } while (item != 6);
        System.out.println(LIST_OF_DEVELOPERS);
        return developerService.getAll();
    }

    private Developer developerById(Long id) {
        Developer developerById = null;
        ArrayList<Developer> listOfDevelopers = developerService.getAll();
        for (Developer developers : listOfDevelopers) {
            if (developers.getId().equals(id)) {
                developerById = developers;
                break;
            }
        }
        return developerById;
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

    private HashSet<Skill> createSetOfSkill(String lineOfSkills) {
        long[] numberOfSkill = Arrays.stream(lineOfSkills.split("\\s")).mapToLong(Long::parseLong).toArray();
        ArrayList<Skill> allSkills = new JdbcSkillRepository().getAll();
        HashSet<Skill> setOfSkills = new HashSet<>();
        for (long i : numberOfSkill) {
            for (Skill skills : allSkills) {
                if (skills.getId().equals(i)) {
                    setOfSkills.add(skills);
                }
            }
        }
        return setOfSkills;
    }

}
