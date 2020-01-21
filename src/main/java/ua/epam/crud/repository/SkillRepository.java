package ua.epam.crud.repository;

import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.GenericRepository;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface SkillRepository extends GenericRepository <Skill , Long>  {
    Skill getById(Long id) throws IOException;

    ArrayList<Skill> getAll() throws IOException;

    ArrayList<Skill> create(Skill skill) throws IOException;

    void delete(Long id) throws IOException;

    ArrayList<Skill> update(Skill skill);

}
