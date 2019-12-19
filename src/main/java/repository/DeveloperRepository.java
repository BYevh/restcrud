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

    private UtilsController utilsController = new UtilsController();

    public Developer getById(Long id) throws IOException {
        return null;
    }

    public ArrayList<Developer> getAll() throws IOException {
        return null;
    }

    public List<Developer> create(Developer developer) throws IOException {
        return null;
    }

    public void delete(Long id) {

    }

    public List<Developer> update(Developer developer) {
        return null;
    }



    private Developer stringToDeveloper(String s) throws IOException {
        String[] arrayOfOwnDeveloper = s.split("\\s");
        Long id = Long.parseLong(arrayOfOwnDeveloper[0]);
        String name = arrayOfOwnDeveloper[1] + " " + arrayOfOwnDeveloper[2];
        StringJoiner joinerSkills = new StringJoiner(" ");
        for (int i = 3; i < arrayOfOwnDeveloper.length-1; i++) {
            joinerSkills.add(arrayOfOwnDeveloper[i]);
        }
        String joinedSkills = joinerSkills.toString();
        HashSet<Skill> setOfString = utilsController.createSetOfSkill(joinedSkills);
        Account account = utilsController.createAccount(Integer.parseInt(arrayOfOwnDeveloper[arrayOfOwnDeveloper.length - 1]));
        return new Developer(id, name, setOfString, account);
    }

    private String developerToString (Developer developer){
        String id = String.valueOf(developer.getId());
        String name = developer.getName();
        String skills = developer.getSkill();
        String account = developer.getAccount();


        return new String();
    }

}
