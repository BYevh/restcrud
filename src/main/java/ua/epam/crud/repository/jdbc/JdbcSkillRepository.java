package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.SkillRepository;

import java.sql.*;
import java.util.ArrayList;

public class JdbcSkillRepository implements SkillRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    @Override
    public Skill getById(Long id) {
        String sql = "SELECT * FROM skills WHERE id = " + id;
        return readFromDB(sql).get(0);
    }

    @Override
    public ArrayList<Skill> getAll() {
        String sql = "SELECT * FROM skills";
        return readFromDB(sql);
    }

    @Override
    public ArrayList<Skill> create(Skill skill) {
        String sql = "INSERT INTO skills VALUE (" + skill.getId() + ", '" + skill.getName() + "')";
        writeToDB(sql);
        return getAll();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM skills WHERE id=" + id;
        writeToDB(sql);
    }

    @Override
    public ArrayList<Skill> update(Skill skill) {
        String sql = "UPDATE skills SET name_skill=" + skill.getName() + ", WHERE id=" + skill.getId();
        writeToDB(sql);
        return getAll();
    }


    private ArrayList<Skill> readFromDB(String sql) {
        ArrayList<Skill> skills = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getLong("id"), resultSet.getString("name_skill")));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return skills;
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
}
