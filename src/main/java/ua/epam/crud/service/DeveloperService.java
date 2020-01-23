package ua.epam.crud.service;

import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.DeveloperRepository;
import ua.epam.crud.repository.jdbc.JdbcDeveloperRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeveloperService {
    private DeveloperRepository developerRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(DeveloperService.class);

    public DeveloperService() {
        try {
            this.developerRepository = new JdbcDeveloperRepository();
        } catch (Exception e) {
            LOGGER.info("Developer repository: " + e.getMessage());
        }

    }

    public Developer getById(Long id) {
        Developer developer = null;
        try {
            developer = developerRepository.getById(id);
        } catch (Exception e) {
            LOGGER.info("Developer repository: " + e.getMessage());
        }
        return developer;
    }

    public ArrayList<Developer> getAll() {
        ArrayList<Developer> developers = null;
        try {
            developers = developerRepository.getAll();
        } catch (Exception e) {
            LOGGER.info("Developer repository: " + e.getMessage());
        }
        return developers;
    }

    public ArrayList<Developer> create(Developer developer) {
        ArrayList<Developer> developers = null;
        try {
            developers = developerRepository.create(developer);
        } catch (Exception e) {
            LOGGER.info("Developer repository: " + e.getMessage());
        }
        return developers;
    }

    public void delete(Long id) {
        try {
            developerRepository.delete(id);
        } catch (Exception e) {
            LOGGER.info("Developer repository: " + e.getMessage());
        }
    }

    public ArrayList<Developer> update(Developer developer) {
        ArrayList<Developer> developers = null;
        try {
            developers = developerRepository.update(developer);
        } catch (Exception e) {
            LOGGER.info("Developer repository: " + e.getMessage());
        }

        return developers;
    }
}
