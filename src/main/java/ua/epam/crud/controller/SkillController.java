package ua.epam.crud.controller;

import ua.epam.crud.model.Skill;
import ua.epam.crud.service.SkillService;

import java.io.IOException;
import java.util.ArrayList;

public class SkillController {
    private final String INPUT_ID = "Input ID:";
    private final String INPUT_SKILL = "Input SKILL:";

    private SkillService skillService;
    private UtilsController utilsController = new UtilsController();

    public SkillController() {
        this.skillService = new SkillService();
    }



    public ArrayList<Skill> menuOfSkills() throws IOException {
        ArrayList<Skill> listOfSkills = skillService.getAll();


        int item;
        do {
            item = Integer.parseInt(utilsController.inputData());
            switch (item) {
                case 1: {                                           //1. Show all skills.
                    System.out.println(listOfSkills);
                    break;
                }
                case 2: {                                           //2. Show skills by id.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    System.out.println(skillService.getById(id));
                    break;
                }

                case 3: {                                           //3. Add new skill.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    System.out.println(INPUT_SKILL);
                    String skill = utilsController.inputData();
                    listOfSkills = skillService.create(new Skill (id, skill));
                    System.out.println(listOfSkills);
                    break;
                }
                case 4: {                                           //4. delete
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utilsController.inputData());
                    skillService.delete(id);
                    listOfSkills = skillService.getAll();
                    System.out.println(listOfSkills);
                    break;
                }
                case 5: {                                           //5. Exit
                    break;
                }
            }
        } while (item != 5);

        return listOfSkills;
    }



}
