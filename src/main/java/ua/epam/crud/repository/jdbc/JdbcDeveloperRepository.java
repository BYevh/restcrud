package ua.epam.crud.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.crud.model.Account;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.DeveloperRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class JdbcDeveloperRepository implements DeveloperRepository {

    private JdbcUtils jdbcUtils = new JdbcUtils();
    private JdbcAccountRepository jdbcAccountRepository = new JdbcAccountRepository();
    private static final Logger logger = LoggerFactory.getLogger(JdbcDeveloperRepository.class);

    private final String SELECT_BY_ID_QUERY = "SELECT * FROM developers WHERE id=?";
    private final String SELECT_ALL_QUERY = "SELECT * FROM developers";
    private final String SELECT_ALL_QUERY_DEVELOPER_SKILLS = "SELECT * FROM developer_skills WHERE developer_id=?";
    private final String INSERT_QUERY_DEVELOPER = "INSERT INTO developers VALUE ( ? , ?)";
    private final String INSERT_QUERY_DEVELOPER_SKILLS = "INSERT INTO developer_skills VALUE ( ? , ?)";
    private final String DELETE_QUERY_DEVELOPER = "DELETE FROM developers WHERE id=?";
    private final String DELETE_QUERY_DEVELOPER_SKILLS = "DELETE FROM developer_skills WHERE developer_id=?";
    private final String UPDATE_QUERY = "UPDATE developers SET name=? WHERE id=?";

    @Override
    public Developer getById(Long id) {
        logger.info("get Account by id");
        Developer developerById = null;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            developerById = readFromDB(preparedStatement).get(0);
        } catch (SQLException e) {
            logger.error("wrong sql query");
        } catch (IndexOutOfBoundsException e) {
            logger.info("Id doesn't exist: " + e.getMessage());
        }
        return developerById;
    }

    @Override
    public ArrayList<Developer> getAll() {
        logger.info("get all Accounts");
        ArrayList<Developer> listOfDevelopers = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_QUERY)) {
            listOfDevelopers = readFromDB(preparedStatement);
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return listOfDevelopers;
    }

    @Override
    public ArrayList<Developer> create(Developer developer) {
        logger.info("create Developer");
        ArrayList<Developer> developers = getAll();
        Long idDeveloper = developer.getId();
        String nameDeveloper = developer.getName();

        Account account = developer.getAccount();
        for (Developer developer1 : developers) {
            if (idDeveloper.equals(developer1.getId())) {
                System.out.println("developer is exist");
                return developers;
            }
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY_DEVELOPER)) {
            preparedStatement.setLong(1, idDeveloper);
            preparedStatement.setString(2, nameDeveloper);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }

        logger.info("create Account");
        jdbcAccountRepository.create(account);

        logger.info("create Skills");
        insertSkillsToDbDeveloperSkills(developer);
        return getAll();
    }

    @Override
    public void delete(Long id) {
        logger.info("delete Developer skills by id");
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_QUERY_DEVELOPER_SKILLS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }

        logger.info("delete Account by id");
        jdbcAccountRepository.delete(id);

        logger.info("delete Developer by id");
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_QUERY_DEVELOPER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
    }

    @Override
    public ArrayList<Developer> update(Developer developer) {
        logger.info("update Developer");
        Long idDeveloper = developer.getId();
        String nameDeveloper = developer.getName();
        Account account = developer.getAccount();

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setLong(1, idDeveloper);
            preparedStatement.setString(2, nameDeveloper);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }

        logger.info("update Account");
        jdbcAccountRepository.update(account);


        logger.info("delete Developer skills by id");
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_QUERY_DEVELOPER_SKILLS)) {
            preparedStatement.setLong(1, idDeveloper);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        logger.info("update Skills");
        insertSkillsToDbDeveloperSkills(developer);
        return getAll();
    }


    private ArrayList<Developer> readFromDB(PreparedStatement preparedStatement) {
        logger.info("read Developer from DB");
        ArrayList<Developer> developers = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Account account = jdbcAccountRepository.createAccountFromTableData(resultSet.getLong("id"));
                HashSet<Skill> setOfSkill = createSetOfSkillsFromDbDeveloperSkills(id);
                developers.add(new Developer(
                        id,
                        resultSet.getString("name"),
                        setOfSkill,
                        account));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    private void insertSkillsToDbDeveloperSkills(Developer developer) {
        Long idDeveloper = developer.getId();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY_DEVELOPER_SKILLS)) {
            for (Skill skill : developer.getSkills()) {
                preparedStatement.setLong(1, idDeveloper);
                preparedStatement.setLong(2, skill.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
    }


    private HashSet<Skill> createSetOfSkillsFromDbDeveloperSkills(long idDeveloper) {
        JdbcSkillRepository jdbcSkillRepository = new JdbcSkillRepository();
        ArrayList<Skill> listOfAllSkills = jdbcSkillRepository.getAll();
        HashSet<Skill> listOfSkillsDeveloper = new HashSet<>();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_QUERY_DEVELOPER_SKILLS)) {
            preparedStatement.setLong(1, idDeveloper);
            ResultSet resultSet = preparedStatement.executeQuery();
            long skillId;
            while (resultSet.next()) {
                skillId = resultSet.getLong("skill_id");
                for (Skill skill : listOfAllSkills) {
                    if (skill.getId().equals(skillId)) {
                        listOfSkillsDeveloper.add(skill);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("wrong sql query");
        }
        return listOfSkillsDeveloper;
    }


}
