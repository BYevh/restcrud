package controller;

import model.Skill;
import repository.SkillRepository;

import java.io.IOException;
import java.util.ArrayList;

public class SkillController {
    private final String INPUT_ID = "Input new ID:";
    private final String INPUT_SKILL = "Input new SKILL:";

    private SkillRepository skillRepository = new SkillRepository();
    private Utils utils = new Utils();

    public ArrayList<Skill> menuOfSkills() throws IOException {
        ArrayList<Skill> listOfSkills = skillRepository.getAll();

        //пока без проверки на валидность
        int item;
        do {
            item = Integer.parseInt(utils.inputData());
            switch (item) {
                case 1: {                                           //1. Show all skills.
                    System.out.println(listOfSkills);
                    break;
                }
                case 2: {                                           //2. Add new skill.
                    System.out.println(INPUT_ID);
                    Long id = Long.parseLong(utils.inputData());
                    System.out.println(INPUT_SKILL);
                    String skill = utils.inputData();
                    listOfSkills = skillRepository.create(new Skill (id, skill));
                    System.out.println(listOfSkills);
                    break;
                }
                case 3: {                                           //3. Exit
                    break;
                }
            }
        } while (item != 3);

        return listOfSkills;
    }



}
