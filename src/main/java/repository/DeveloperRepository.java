package repository;

import controller.UtilsController;
import model.Account;
import model.Developer;
import model.Skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

public class DeveloperRepository implements GenericRepository<Developer> {
    private String fileName = "\\resources\\developers.txt";
    private UtilsRepository utilsRepository = new UtilsRepository(fileName);
    private UtilsController utilsController = new UtilsController();

    public Developer getById(Long id) throws IOException {
        ArrayList<Developer> developers = getAll();
        for (Developer developer : developers) {
            if (developer.getId().equals(id))
                return developer;
        }
        return null;
    }

    public ArrayList<Developer> getAll() throws IOException {
        ArrayList<Developer> developers = new ArrayList<>();
        ArrayList<String> stringOfDevelopers = utilsRepository.readAllFile();
        for (String s : stringOfDevelopers) {
            developers.add(lineOfStringToDeveloper(s));
        }
        return developers;
    }

    public List<Developer> create(Developer newDeveloper) throws IOException {
        ArrayList<Developer> developers = getAll();
        developers.add(newDeveloper);
        ArrayList<String> stringsOfDevelopers = new ArrayList<>();
        for (Developer developer : developers) {
            stringsOfDevelopers.add(developerToLineOfString(developer));
        }
        utilsRepository.writeAllFile(stringsOfDevelopers);

        return getAll();
    }

    public void delete(Long id) throws IOException {
        ArrayList<Developer> developers = getAll();
        Developer developerForDelete = null;
        for (Developer developer : developers) {
            if (developer.getId().equals(id)){
                developerForDelete = developer;
            }
        }
        developers.remove(developerForDelete);
        ArrayList<String> stringsOfDevelopers = new ArrayList<>();
        for (Developer developer : developers) {
            stringsOfDevelopers.add(developerToLineOfString(developer));
        }
        utilsRepository.writeAllFile(stringsOfDevelopers);
    }

    public List<Developer> update(Developer developer) {
        return null;
    }


    private Developer lineOfStringToDeveloper(String s) throws IOException {
        String[] arrayOfOwnDeveloper = s.split("\\s");
        Long id = Long.parseLong(arrayOfOwnDeveloper[0]);
        String name = arrayOfOwnDeveloper[1] + " " + arrayOfOwnDeveloper[2];

        //get set of skills from arrayString
        StringJoiner joinerSkills = new StringJoiner(" ");
        for (int i = 3; i < arrayOfOwnDeveloper.length - 1; i++) {
            joinerSkills.add(arrayOfOwnDeveloper[i]);
        }
        String joinedSkills = joinerSkills.toString();
        HashSet<Skill> setOfSkills = utilsController.createSetOfSkill(joinedSkills);
        //

        Account account = utilsController.createAccount(Long.parseLong(arrayOfOwnDeveloper[arrayOfOwnDeveloper.length - 1]));
        return new Developer(id, name, setOfSkills, account);
    }

    private String developerToLineOfString(Developer developer) {
        String id = String.valueOf(developer.getId());
        String name = developer.getName();

        //get lineStrings of skills from setOfSkills
        HashSet<Skill> setOfSkills = developer.getSkills();
        StringJoiner joinerSkills = new StringJoiner(" ");
        for (Skill skill : setOfSkills) {
            joinerSkills.add(String.valueOf(skill.getId()));
        }
        String joinedSkills = joinerSkills.toString();
        //

        String account = String.valueOf(developer.getAccount().getAccountStatus().getId() + "\n");

        return String.join(" ", id, name, joinedSkills, account);
    }

}
