package repository;

import model.Skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository implements GenericRepository<Skill> {
    private String fileName = "C:\\Users\\Yevhen\\IdeaProjects\\consolecrude\\src\\resources\\skills.txt";
    private UtilsFileProcessor utilsFileProcessor = new UtilsFileProcessor(fileName);

    public Skill getById(Long id) throws IOException {
        ArrayList<Skill> listOfSkills = getAll();
        for (Skill skill : listOfSkills) {
            if (skill.getId().equals(id)) {
                return skill;
            }
        }
        return null;
    }

    //read
    public ArrayList<Skill> getAll() throws IOException {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        ArrayList<String> stringOfSkills = utilsFileProcessor.readAllFile();
        for (String s : stringOfSkills) {
            skills.add(stringToSkill(s));
        }
        return skills;
    }

    //create
    public ArrayList<Skill> create(Skill newSkill) throws IOException {
        ArrayList<Skill> listOfSkills = getAll();
        ArrayList<String> stringOfSkills = new ArrayList<String>();

        //if there is a skill, then exit returning the existing set of skills
        for (Skill skill : listOfSkills) {
            if (skill.getName().equals(newSkill.getName()) | skill.getId().equals(newSkill.getId())) {
                return listOfSkills;
            }
        }
        // if not, add a skill
        listOfSkills.add(newSkill);

        for (Skill s : listOfSkills) {
            stringOfSkills.add(skillToString(s));
        }

        utilsFileProcessor.writeAllFile(stringOfSkills);
        return getAll();
    }

    //(delete / update) - not safe, only if all developers do not have this skill
    public void delete(Skill skill) {
    }

    public List<Skill> update(Skill skill) {
        return null;
    }

    private Skill stringToSkill(String s) {
        String[] oneSkill = s.split("\\s");
        return new Skill(Long.parseLong(oneSkill[0]), oneSkill[1]);
    }

    private String skillToString(Skill skill) {
        return (skill.getId() + " " + skill.getName() + "\n");
    }

}
