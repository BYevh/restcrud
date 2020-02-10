package ua.epam.crud.repository.jdbc;

import ua.epam.crud.model.Skill;
import ua.epam.crud.repository.SkillRepository;

import java.sql.*;
import java.util.ArrayList;

public class JdbcSkillRepository implements SkillRepository {

    JdbcUtils jdbcUtils = new JdbcUtils();

    @Override
    public Skill getById(Long id) {
        Skill skillById = null;
        String sql = "SELECT * FROM skills WHERE id = " + id;
        try {
            skillById = readFromDB(sql).get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("id doesn't exist: " + e.getMessage());
        }
        return skillById;
    }

    @Override
    public ArrayList<Skill> getAll() {
        String sql = "SELECT * FROM skills";
        return readFromDB(sql);
    }

    @Override
    public ArrayList<Skill> create(Skill skill) {
        String sql = "INSERT INTO skills VALUE (" + skill.getId() + ", '" + skill.getName() + "')";
        jdbcUtils.writeToDB(sql);
        return getAll();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM skills WHERE id=" + id;
        jdbcUtils.writeToDB(sql);
    }

    @Override
    public ArrayList<Skill> update(Skill skill) {
        String sql = "UPDATE skills SET name_skill='" + skill.getName() + "' WHERE id=" + skill.getId();
        jdbcUtils.writeToDB(sql);
        return getAll();
    }


    private ArrayList<Skill> readFromDB(String sql) {
        ArrayList<Skill> skills = new ArrayList<>();

        try (Connection connection = jdbcUtils.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getLong("id"), resultSet.getString("name_skill")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            skills = null;
        }
        return skills;
    }

}
