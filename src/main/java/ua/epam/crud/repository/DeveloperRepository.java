package ua.epam.crud.repository;

import ua.epam.crud.model.Developer;

import java.util.ArrayList;


public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    @Override
    default Developer getById(Long id) {
        return null;
    }

    @Override
    default ArrayList<Developer> getAll() {
        return null;
    }

    @Override
    default ArrayList<Developer> create(Developer developer) {
        return null;
    }

    @Override
    default void delete(Long id) {

    }

    @Override
    default ArrayList<Developer> update(Developer developer) {
        return null;
    }
}
