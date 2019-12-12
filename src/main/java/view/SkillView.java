package view;

import controller.SkillController;

import java.io.IOException;

public class SkillView {
    private final String MENU_ITEM_1 = "1. Show all skills.";
    private final String MENU_ITEM_2 = "2. Add new skill.";
    private final String MENU_ITEM_3 = "3. Exit.";



    public SkillView() throws IOException {
        System.out.println(MENU_ITEM_1);
        System.out.println(MENU_ITEM_2);
        System.out.println(MENU_ITEM_3);
        SkillController skillController = new SkillController();
        System.out.println(skillController.menuOfSkills());


    }



}
