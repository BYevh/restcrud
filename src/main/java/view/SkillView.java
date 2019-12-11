package view;

import controller.SkillController;

public class SkillView {
    private final String MENU_ITEM_1 = "1. Show all skills.";
    private final String MENU_ITEM_2 = "2. Add new skill.";

    public SkillView() {
        System.out.println(MENU_ITEM_1);
        System.out.println(MENU_ITEM_2);
        SkillController skillController = new SkillController();
    }



}
