package ua.epam.crud.repository;

import ua.epam.crud.model.Developer;

import java.util.ArrayList;


public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    @Override
    Developer getById(Long id);

    @Override
    ArrayList<Developer> getAll();

    @Override
    ArrayList<Developer> create(Developer developer);

    @Override
    void delete(Long id);

    @Override
    ArrayList<Developer> update(Developer developer);
}
