package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Account;
import ua.epam.crud.model.AccountStatus;
import ua.epam.crud.model.Developer;
import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.DeveloperRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class JdbcDeveloperRepository implements DeveloperRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

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
        String sql = "INSERT INTO developers VALUE (" + developer.getId() + ", '" + developer.getName() + ", '" + developer.getAccount().getAccountStatus().getId() + "')";
        writeToDB(sql);
        return getAll();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM skills WHERE id=" + id;
        writeToDB(sql);
        sql = "DELETE FROM accounts WHERE id=" + id;
        writeToDB(sql);
        sql = "DELETE FROM developers WHERE id=" + id;
        writeToDB(sql);

    }

    @Override
    public List<Developer> update(Developer developer) {
//        String sql = "UPDATE skills SET name=" + developer.getName() + ", " + +", WHERE id=" + developer.getId();
//        writeToDB(sql);
        return getAll();
    }


    private ArrayList<Developer> readFromDB(String sql) {
        ArrayList<Developer> developers = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Account account = createAccountFromTableData(resultSet.getLong("id"));
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


    private void writeToDB(String sql) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Account createAccountFromTableData(long idDeveloper) {
        String sql = "SELECT * FROM accounts WHERE developer_id=" + idDeveloper;
        int idStatus = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                idStatus = resultSet.getInt("id_status");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Account account = null;
        for (AccountStatus accountStatus : AccountStatus.values()) {
            if (accountStatus.getId() == idStatus) {
                account = new Account(idDeveloper, accountStatus);
            }
        }
        return account;
    }

    private HashSet<Skill> createSetOfSkillsFromTableData(long idDeveloper) {
        String sql = "SELECT * FROM developer_skills WHERE developer_id=" + idDeveloper;
        JdbcSkillRepository jdbcSkillRepository = new JdbcSkillRepository();
        ArrayList<Skill> listOfAllSkills = jdbcSkillRepository.getAll();
        HashSet<Skill> listOfSkillsDeveloper = new HashSet<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
