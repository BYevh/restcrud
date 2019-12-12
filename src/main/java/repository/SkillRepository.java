package repository;

import model.Skill;

import java.io.*;
import java.util.TreeSet;

public class SkillRepository {
    private String fileName = "C:\\Users\\Yevhen\\IdeaProjects\\consolecrude\\src\\resources\\skills.txt";


    public SkillRepository(){
    }

    //delete / update - not safe, only if all developers do not have this skill

    //read
    public TreeSet<Skill> getAllSkills() throws IOException {
        return readAllFile();
    }

    //create
    public TreeSet<Skill> updateSkills(Long id, String nameSkill) throws IOException {
        TreeSet<Skill> setOfSkills = readAllFile();

        //если есть скил, то выходим возвращая существующий сет скилов
        for (Skill skill : setOfSkills) {
            if (skill.getNameOfSkill().equals(nameSkill) | skill.getId().equals(id)) {
                return setOfSkills;
            }
        }
        // если нет, то добавляем
        setOfSkills.add(new Skill(id, nameSkill));

        writeAllFile(setOfSkills);
        return readAllFile();
    }


    private TreeSet<Skill> readAllFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        TreeSet<Skill> setOfSkills = new TreeSet<Skill>();

        while (reader.ready()) {
            String[] oneSkill = reader.readLine().split(" ");
            setOfSkills.add(new Skill(Long.parseLong(oneSkill[0]), oneSkill[1]));
        }

        reader.close();
        return setOfSkills;
    }


    private void writeAllFile(TreeSet<Skill> newListOfSkills) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Skill set : newListOfSkills) {
            writer.write(set.getId() + " " + set.getNameOfSkill() + "\n");
        }
        writer.close();
    }

}
