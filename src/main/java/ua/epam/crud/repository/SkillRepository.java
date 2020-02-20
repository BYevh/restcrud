package ua.epam.crud.repository;

import ua.epam.crud.model.Skill;

import java.io.IOException;
import java.util.ArrayList;

public interface SkillRepository extends GenericRepository<Skill, Long> {
    @Override
    Skill getById(Long id) throws IOException;

    @Override
    ArrayList<Skill> getAll() throws IOException;

    @Override
    ArrayList<Skill> create(Skill skill) throws IOException;

    @Override
    void delete(Long id) throws IOException;

    @Override
    ArrayList<Skill> update(Skill skill);

}
