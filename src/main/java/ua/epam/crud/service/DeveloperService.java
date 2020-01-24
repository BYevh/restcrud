package ua.epam.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.crud.model.Developer;
import ua.epam.crud.repository.DeveloperRepository;
import ua.epam.crud.repository.jdbc.JdbcDeveloperRepository;

import java.util.ArrayList;



public class DeveloperService {
    private DeveloperRepository developerRepository;
    public static final Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    public DeveloperService() {
            this.developerRepository = new JdbcDeveloperRepository();
    }

    public Developer getById(Long id) {
        Developer developer = null;
        logger.info("Developer repository: getById");
        try {
            developer = developerRepository.getById(id);
        } catch (Exception e) {
            logger.error("Developer repository: getById" + e.getMessage());
        }
        return developer;
    }

    public ArrayList<Developer> getAll() {
        ArrayList<Developer> developers = null;
        try {
            developers = developerRepository.getAll();
        } catch (Exception e) {
            logger.error("Developer repository: getAll" + e.getMessage());
        }
        return developers;
    }

    public ArrayList<Developer> create(Developer developer) {
        ArrayList<Developer> developers = null;
        try {
            developers = developerRepository.create(developer);
        } catch (Exception e) {
            logger.error("Developer repository: create" + e.getMessage());
        }
        return developers;
    }

    public void delete(Long id) {
        try {
            developerRepository.delete(id);
        } catch (Exception e) {
            logger.error("Developer repository: delete" + e.getMessage());
        }
    }

    public ArrayList<Developer> update(Developer developer) {
        ArrayList<Developer> developers = null;
        try {
            developers = developerRepository.update(developer);
        } catch (Exception e) {
            logger.error("Developer repository: update" + e.getMessage());
        }

        return developers;
    }
}
