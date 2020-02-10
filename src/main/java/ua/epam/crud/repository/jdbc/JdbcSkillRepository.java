package ua.epam.crud.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.SkillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcSkillRepository implements SkillRepository {

    JdbcUtils jdbcUtils = new JdbcUtils();
    public static final Logger logger = LoggerFactory.getLogger(JdbcSkillRepository.class);
    private final String SELECT_BY_ID_QUERY = "SELECT * FROM skills WHERE id=?";
    private final String SELECT_ALL_QUERY = "SELECT * FROM skills";
    private final String INSERT_QUERY = "INSERT INTO skills VALUE ( ? , ?)";
    private final String DELETE_QUERY = "DELETE FROM skills WHERE id=?";
    private final String UPDATE_QUERY = "UPDATE skills SET name_skill=? WHERE id=?";

    @Override
    public Skill getById(Long id) {
        logger.info("get Skill by id");
        Skill skillById = null;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            skillById = readFromDB(preparedStatement).get(0);
        } catch (SQLException e) {
            logger.error("wrong sql query");
        } catch (IndexOutOfBoundsException e) {
            logger.info("Id doesn't exist: " + e.getMessage());
        }
        return skillById;
    }

    @Override
    public ArrayList<Skill> getAll() {
        logger.info("get all Skills");
        ArrayList<Skill> listOfSkills = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_QUERY)) {
            listOfSkills = readFromDB(preparedStatement);
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return listOfSkills;
    }

    @Override
    public ArrayList<Skill> create(Skill skill) {
        logger.info("create Skill");
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setLong(1, skill.getId());
            preparedStatement.setString(2, skill.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return getAll();
    }

    @Override
    public void delete(Long id) {
        logger.info("delete Skill by id");
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
    }

    @Override
    public ArrayList<Skill> update(Skill skill) {
        logger.info("update Account");
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setLong(2, skill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return getAll();
    }


    private ArrayList<Skill> readFromDB(PreparedStatement preparedStatement) {
        logger.info("read Skills from DB");
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getLong("id"), resultSet.getString("name_skill")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

}
