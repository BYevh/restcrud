package ua.epam.crud.service;

import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.DeveloperRepository;
import ua.epam.crud.repository.jdbc.JdbcDeveloperRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperService {
    private DeveloperRepository developerRepository;

    public DeveloperService() {
        this.developerRepository = new JdbcDeveloperRepository();
    }

    public Developer getById(Long id) throws IOException {
        return developerRepository.getById(id);
    }

    public ArrayList<Developer> getAll() {
        return developerRepository.getAll();
    }

    public List<Developer> create(Developer developer) {
        return developerRepository.create(developer);
    }

    public void delete(Long id) {
        developerRepository.delete(id);
    }

    public List<Developer> update(Developer developer) {
        return developerRepository.update(developer);
    }
}
