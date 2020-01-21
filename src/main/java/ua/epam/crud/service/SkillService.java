package ua.epam.crud.service;

import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.SkillRepository;
import ua.epam.crud.repository.jdbc.JdbcSkillRepository;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
}
