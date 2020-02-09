package ua.epam.crud.repository.jdbc

import org.junit.Test
import org.testng.Assert
import ua.epam.crud.model.Account
import ua.epam.crud.model.AccountStatus
import ua.epam.crud.model.Skill
import ua.epam.crud.repository.AccountRepository
import ua.epam.crud.repository.SkillRepository

class JdbcSkillRepositoryTest {

    @Test
    void testGetById() {
        SkillRepository skillRepository = new JdbcSkillRepository()
        Skill expected = new Skill(3,"C#");
        Skill actual = skillRepository.getById(3L);
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    void testCreate() {
    }

    void testDelete() {
    }
}
