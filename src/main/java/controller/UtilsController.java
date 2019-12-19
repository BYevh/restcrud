package controller;

import model.Account;
import model.AccountStatus;
import model.Skill;
import repository.SkillRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class UtilsController {

    String inputData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Account createAccount(Long id) {

        Account account = null;
        for (AccountStatus status: AccountStatus.values()) {
            if (status.getId().equals(id)) {
                account = new Account(status);
            }
        }
        return account;
    }

    public HashSet<Skill> createSetOfSkill(String lineOfSkills) throws IOException {
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
