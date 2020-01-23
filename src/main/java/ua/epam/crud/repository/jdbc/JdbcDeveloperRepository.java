package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.DeveloperRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static ua.epam.crud.repository.jdbc.JdbcUtils.*;

public class JdbcDeveloperRepository implements DeveloperRepository {

    private JdbcUtils jdbcUtils = new JdbcUtils();

    @Override
    public Developer getById(Long id) {
        String sql = "SELECT * FROM developers WHERE id = " + id;
        return readFromDB(sql).get(0);
    }

    @Override
    public ArrayList<Developer> getAll() {
        String sql = "SELECT * FROM developers";
        return readFromDB(sql);
    }

    @Override
    public ArrayList<Developer> create(Developer developer) {
        Long idDeveloper = developer.getId();
        String nameDeveloper = developer.getName();
        Long idStatus = developer.getAccount().getAccountStatus().getId();
        String sql = "INSERT INTO developers VALUE (" + idDeveloper + ", '" + nameDeveloper + "')";
        jdbcUtils.writeToDB(sql);
        sql = "INSERT INTO accounts VALUE (" + idDeveloper + ", " + idStatus + ")";
        jdbcUtils.writeToDB(sql);
        for (Skill skill : developer.getSkills()) {
            sql = "INSERT INTO developer_skills VALUE (" + idDeveloper + ", " + skill.getId() + ")";
            jdbcUtils.writeToDB(sql);
        }
        return getAll();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM developer_skills WHERE developer_id=" + id;
        jdbcUtils.writeToDB(sql);
        sql = "DELETE FROM accounts WHERE developer_id=" + id;
        jdbcUtils.writeToDB(sql);
        sql = "DELETE FROM developers WHERE id=" + id;
        jdbcUtils.writeToDB(sql);

    }

    @Override
    public List<Developer> update(Developer developer) {
        Long idDeveloper = developer.getId();
        String nameDeveloper = developer.getName();
        Long idStatus = developer.getAccount().getAccountStatus().getId();
        String sql = "UPDATE developers SET name='" + nameDeveloper + "' WHERE id=" + idDeveloper;
        jdbcUtils.writeToDB(sql);
        sql = "UPDATE accounts SET id_status=" + idStatus + " WHERE developer_id=" + idDeveloper;
        jdbcUtils.writeToDB(sql);
        sql = "DELETE FROM developer_skills WHERE developer_id=" + idDeveloper;
        jdbcUtils.writeToDB(sql);
        for (Skill skill : developer.getSkills()) {
            sql = "INSERT INTO developer_skills VALUE (" + idDeveloper + ", " + skill.getId() + ")";
            jdbcUtils.writeToDB(sql);
        }
        return getAll();
    }


    private ArrayList<Developer> readFromDB(String sql) {
        ArrayList<Developer> developers = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Account account = JdbcUtils.createAccountFromTableData(resultSet.getLong("id"));
                HashSet<Skill> setOfSkill = createSetOfSkillsFromTableData(id);
                developers.add(new Developer(
                        id,
                        resultSet.getString("name"),
                        setOfSkill,
                        account));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return developers;
    }


    private HashSet<Skill> createSetOfSkillsFromTableData(long idDeveloper) {
        String sql = "SELECT * FROM developer_skills WHERE developer_id=" + idDeveloper;
        JdbcSkillRepository jdbcSkillRepository = new JdbcSkillRepository();
        ArrayList<Skill> listOfAllSkills = jdbcSkillRepository.getAll();
        HashSet<Skill> listOfSkillsDeveloper = new HashSet<>();
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            long skillId;
            while (resultSet.next()) {
                skillId = resultSet.getLong("skill_id");
                for (Skill skill : listOfAllSkills) {
                    if (skill.getId().equals(skillId)) {
                        listOfSkillsDeveloper.add(skill);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listOfSkillsDeveloper;
    }


}
