package controller;

import model.Developer;
import model.Skill;
import org.omg.PortableInterceptor.ACTIVE;
import repository.DeveloperRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


public class DeveloperController {
    private final String INPUT_ID = "Input ID:";
    private final String INPUT_NAME = "Input NAME (FirstName LastName, separated by space):";
    private final String INPUT_SKILL = "Input SKILL, numbers separated by spaces:";
    private final String INPUT_ACCOUNT_STATUS = "Input ACCOUNT STATUS:";

    DeveloperRepository developerRepository = new DeveloperRepository();
    ArrayList<Developer> listOfDevelopers = developerRepository.getAll();
    Utils utils = new Utils();

    public DeveloperController() throws IOException {
    }


    public ArrayList<Developer> menuOfDevelopers() throws IOException {
        int item;
        do {
            item = Integer.parseInt(utils.inputData());
            switch (item) {
                case 1: {                                           //    1. Show all developers
                    System.out.println(listOfDevelopers);
                    break;
                }
                case 2: {                                           //    2. Show developer by ID
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utils.inputData());
                    for (Developer developer : listOfDevelopers) {
                        if (developer.getId().equals(id)) {
                            System.out.println(developer);
                            break;
                        }
                    }

                    break;
                }
                case 3: {                                           //    3. Add new developer.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utils.inputData());
                    System.out.println(INPUT_NAME);
                    String name  = utils.inputData();
//                    System.out.println(INPUT_SKILL);
//                    String  skills = utils.inputData();
//                    System.out.println(INPUT_ACCOUNT_STATUS);
//                    String accountStatus = utils.inputData();
//                    developerRepository.create(new Developer(id, name, new Set <Skill>(), ACTIVE))

                    break;
                }
                case 4: {                                           //    4. Update developer.
                    break;
                }
                case 5: {                                           //    5. Delete a developer.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utils.inputData());
                    developerRepository.delete(id);
                    break;
                }
                case 6: {                                           //    6. Exit.
                    break;
                }
            }
        } while (item != 3);

        return developerRepository.getAll();
    }
}
