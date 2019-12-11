import model.Skill;
import repository.SkillRepository;
import view.SkillView;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        SkillRepository skillRepository = new SkillRepository();
        System.out.println(skillRepository.getAllSkills());
        System.out.println(skillRepository.updateSkills(4L, "C++"));

    }
}
