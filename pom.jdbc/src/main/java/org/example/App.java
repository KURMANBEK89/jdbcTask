package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
//        createTABLE();
//        System.out.println(getCount());
//        addUsers("Aidai",18);
//        System.out.println(getAllUsers());
        System.out.println(getUserById(1));

    }

    public static void createTABLE() {
        String SQL = "Create table IF NOT EXISTS users(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(128)," +
                "age INT NOT NULL);";
        try (Connection conn = jd.connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCount() {
        String SQL = "SELECT count(*) FROM users";
        int count = 0;
        try (Connection conn = jd.connection();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void addUsers(String name, int age) {
        String SQL = "INSERT INTO users(name, age)VALUES(?,?);";
        try (Connection conn = jd.connection()) {
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        String SQL = "SELECT * FROM users;";
        List<User> users = new ArrayList<>();
        try (Connection conn = jd.connection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    public static User getUserById(int id) {
        String SQL = "SELECT * FROM users WHERE id=?;";
        User user = new User();
        try (Connection conn = jd.connection()) {
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
