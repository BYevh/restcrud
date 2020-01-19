package ua.epam.crud.repository.io;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.GenericRepository;

import java.io.IOException;
import java.util.*;

public class DeveloperRepository implements GenericRepository<Developer> {
    private String fileName = "C:\\Users\\Yevhen\\IdeaProjects\\consolecrude\\src\\resources\\developers.txt";
    private UtilsRepository utilsRepository = new UtilsRepository(fileName);

    @Override
    public Developer getById(Long id) throws IOException {
        ArrayList<Developer> developers = getAll();
        for (Developer developer : developers) {
            if (developer.getId().equals(id))
                return developer;
        }
        return null;
    }

    @Override
    public ArrayList<Developer> getAll() throws IOException {
        ArrayList<Developer> developers = new ArrayList<>();
        ArrayList<String> stringOfDevelopers = utilsRepository.readAllFile();
        for (String s : stringOfDevelopers) {
            developers.add(lineOfStringToDeveloper(s));
        }
        return developers;
    }

    @Override
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

    @Override
    public void delete(Long id) throws IOException {
        ArrayList<Developer> developers = getAll();
        Developer developerForDelete = null;
        for (Developer developer : developers) {
            if (developer.getId().equals(id)) {
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

    @Override
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
        HashSet<Skill> setOfSkills = createSetOfSkill(joinedSkills);
        //

        Account account = createAccount(Long.parseLong(arrayOfOwnDeveloper[arrayOfOwnDeveloper.length - 1]));
        return new Developer(id, name, setOfSkills, account);
    }

    private String developerToLineOfString(Developer developer) {
        String id = String.valueOf(developer.getId());
        String name = developer.getName();

        //get lineStrings of skills from setOfSkills
        Set<Skill> setOfSkills = developer.getSkills();
        StringJoiner joinerSkills = new StringJoiner(" ");
        for (Skill skill : setOfSkills) {
            joinerSkills.add(String.valueOf(skill.getId()));
        }
        String joinedSkills = joinerSkills.toString();
        //

        String account = developer.getAccount().getAccountStatus().getId() + "\n";

        return String.join(" ", id, name, joinedSkills, account);
    }

    private Account createAccount(Long id) {

        Account account = null;
        for (AccountStatus status : AccountStatus.values()) {
            if (status.getId().equals(id)) {
                account = new Account(status);
            }
        }
        return account;
    }

    private HashSet<Skill> createSetOfSkill(String lineOfSkills) throws IOException {
        long[] numberOfSkill = Arrays.stream(lineOfSkills.split("\\s")).mapToLong(Long::parseLong).toArray();
        ArrayList<Skill> allSkills = new SkillRepository().getAll();
        HashSet<Skill> setOfSkills = new HashSet<>();
        for (long i : numberOfSkill) {
            for (Skill skills : allSkills) {
                if (skills.getId().equals(i)) {
                    setOfSkills.add(skills);
                }
            }
        }
        return setOfSkills;
    }

}
