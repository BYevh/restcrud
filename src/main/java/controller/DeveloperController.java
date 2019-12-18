package controller;

import model.Account;
import model.Developer;
import model.Skill;
import repository.DeveloperRepository;
import repository.SkillRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;


public class DeveloperController {
    private final String INPUT_ID = "Input ID:";
    private final String INPUT_NAME = "Input NAME (FirstName LastName, separated by space):";
    private final String INPUT_SKILLS = "Input SKILL (ID Skills separated by spaces):";
    private final String INPUT_ACCOUNT_STATUS = "Input number of ACCOUNT STATUS (1-ACTIVE, 2-BANNED, 3-DELETED):";

    private DeveloperRepository developerRepository = new DeveloperRepository();
    private ArrayList<Developer> listOfDevelopers = developerRepository.getAll();
    private Utils utils = new Utils();

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
                    String name = utils.inputData();

                    System.out.println(INPUT_SKILLS);
                    String lineOfSkills =  utils.inputData();
                    long[] numberOfSkill = Arrays.stream(lineOfSkills.split("\\s")).mapToLong(Long::parseLong).toArray();
                    SkillRepository skillRepository = new SkillRepository();
                    ArrayList<Skill> allSkills = skillRepository.getAll();
                    Set<Skill> setOfSkillsThisDeveloper = new HashSet<>();
                    for (long i : numberOfSkill) {
                        for (Skill skills:allSkills) {
                            if (skills.getId().equals(i)){
                                setOfSkillsThisDeveloper.add(skills);
                            }
                        }
                    }

                    System.out.println(INPUT_ACCOUNT_STATUS);
                    Account account = utils.createAccount(Integer.parseInt(utils.inputData()));

                    developerRepository.create(new Developer(id, name, setOfSkillsThisDeveloper, account));

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
