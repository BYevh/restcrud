package ua.epam.crud.repository;

import ua.epam.crud.model.Skill;

import java.io.IOException;
import java.util.ArrayList;

public interface SkillRepository extends GenericRepository <Skill , Long>  {
    Skill getById(Long id) throws IOException;

    ArrayList<Skill> getAll() throws IOException;

    ArrayList<Skill> create(Skill skill) throws IOException;

    void delete(Long id) throws IOException;

    ArrayList<Skill> update(Skill skill);

}
