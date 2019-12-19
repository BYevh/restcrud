package view;

import controller.SkillController;

import java.io.IOException;

public class SkillView {

    public SkillView() throws IOException {
        new UtilsView().showMenuSkillView();
        SkillController skillController = new SkillController();
        System.out.println(skillController.menuOfSkills());


    }



}
