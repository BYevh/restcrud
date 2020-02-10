package ua.epam.crud.repository.jdbc

import org.junit.Test
import org.junit.Assert
import ua.epam.crud.model.Skill
import ua.epam.crud.repository.SkillRepository

class JdbcSkillRepositoryTest {

    SkillRepository skillRepository = new JdbcSkillRepository()


    @Test
    void testGetById() {
        Skill expected = new Skill(10L,"Test")
        skillRepository.create(expected)
        Skill actual = skillRepository.getById(10L)
        Assert.assertEquals(expected.getId(), actual.getId())
        Assert.assertEquals(expected.getName(), actual.getName())
    }

    @Test
    void testCreate() {
        Skill expected = new Skill(10L,"Test")
        skillRepository.create(expected)
        Skill actual = skillRepository.getById(10L)
        Assert.assertEquals(expected.getId(), actual.getId())
        Assert.assertEquals(expected.getName(), actual.getName())
        skillRepository.delete(10L)
    }

    @Test
    void testDelete() {
        skillRepository.create(new Skill(10L,"Test"))
        skillRepository.delete(10L)
        Skill actual = skillRepository.getById(10L)
        Assert.assertNull(actual)
    }
}
