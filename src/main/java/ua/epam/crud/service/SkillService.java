package ua.epam.crud.service;

import ua.epam.crud.repository.jdbc.SkillRepository;
import ua.epam.crud.repository.jdbc.JdbcSkillRepository;

public class SkillService {
    private SkillRepository skillRepository;

    public SkillService() {
        this.skillRepository = new JdbcSkillRepository();
    }

}
