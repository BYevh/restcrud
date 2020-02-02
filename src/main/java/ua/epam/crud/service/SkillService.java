package ua.epam.crud.service;

import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.SkillRepository;
import ua.epam.crud.repository.jdbc.JdbcSkillRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SkillService {
    private SkillRepository skillRepository;

    public SkillService() {
        this.skillRepository = new JdbcSkillRepository();
    }

    public Skill getById(Long id) throws IOException {
        return skillRepository.getById(id);
    }

    public ArrayList<Skill> getAll() throws IOException {
        return skillRepository.getAll();
    }

    public ArrayList<Skill> create(Skill skill) throws IOException {
        return skillRepository.create(skill);
    }

    public void delete(Long id) throws IOException {
        skillRepository.delete(id);
    }

    public ArrayList<Skill> update(Skill skill) {
        return skillRepository.update(skill);
    }

    public HashSet<Skill> createSetOfSkills(String lineOfSkills) {
        long[] numberOfSkill = Arrays.stream(lineOfSkills.split("\\s")).mapToLong(Long::parseLong).toArray();
        ArrayList<Skill> allSkills = new JdbcSkillRepository().getAll();
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
