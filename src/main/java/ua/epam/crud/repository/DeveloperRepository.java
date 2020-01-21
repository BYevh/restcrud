package ua.epam.crud.repository;

import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.GenericRepository;

import java.io.IOException;
import java.util.List;


public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    @Override
    default Developer getById(Long id) throws IOException {
        return null;
    }

    @Override
    default List<Developer> getAll() throws IOException {
        return null;
    }

    @Override
    default List<Developer> create(Developer developer) throws IOException {
        return null;
    }

    @Override
    default void delete(Long id) throws IOException {

    }

    @Override
    default List<Developer> update(Developer developer) {
        return null;
    }
}
