package ua.epam.crud.repository.jdbc

import org.junit.Assert
import org.junit.Test
import ua.epam.crud.model.Account
import ua.epam.crud.model.AccountStatus
import ua.epam.crud.model.Developer
import ua.epam.crud.model.Skill
import ua.epam.crud.repository.DeveloperRepository

class JdbcDeveloperRepositoryTest {

    DeveloperRepository developerRepository = new JdbcDeveloperRepository();

    @Test
    void testGetById_CREATED_DEVELOPERS_EQUELS() {
        HashSet<Skill> skills = new HashSet<>();
        skills.add(new Skill (1, "Java"));
        Developer expected = new Developer(4l, "test",skills,new Account(4L, AccountStatus.DELETED))
        developerRepository.create(expected)
        Developer actual = developerRepository.getById(4L)
        Assert.assertEquals(expected.getId(), actual.getId())
        Assert.assertEquals(expected.getName(), actual.getName())
        Assert.assertEquals(expected.getAccount().getAccountStatus(), actual.getAccount().getAccountStatus())
        developerRepository.delete(4L)
    }

    @Test
    void testDelete() {
        HashSet<Skill> skills = new HashSet<>();
        skills.add(new Skill (1, "Java"));
        developerRepository.create(new Developer(4l, "test",skills,new Account(4L, AccountStatus.DELETED)))
        developerRepository.delete(4L)
        Assert.assertNull(developerRepository.getById(4L))
    }
}
