package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        3 таблица бар - Шаарлар, Олколор, Шаар башчылары
//
//        Ар бир таблицага жок дегенде 5тен маалымат киргизуу керек
//
//        Statement жана PreparedStatement-ти колдонуу
//
//        Шаарларды жана олколорду ArrayList-ке сактоо
//        Базадан шаарды id менен алып консолго чыгарышыбыз керек
//        createTABLE();
//        addInfo("Bishkek", "Kyrgyzstan", "Aibek Junushaliev");
//        addInfo("Moscow", "Russia", "Sergey Sobyanin");
//        addInfo("New-York", "USA", "Eric Adams");
//        addInfo("Astana", "Kazakhstan", "Jenis Kasymbek");
//        addInfo("Paris", "French", "Ann Idalgo");
        System.out.println(getAll());
        System.out.println(getCityById(1));

    }

    public static void createTABLE() {
        String SQL = "Create table IF NOT EXISTS cities(" +
                "id SERIAL PRIMARY KEY," +
                "city_name VARCHAR(128) NOT NULL," +
                "country VARCHAR(128) NOT NULL," +
                "major_city VARCHAR(128) NOT NULL);";
        try (Connection conn = JD.connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCount() {
        String SQL = "SELECT count(*) FROM cities;";
        int count = 0;
        try (Connection conn = JD.connection();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void addInfo(String city_name, String country, String major_city) {
        String SQL = "INSERT INTO cities(city_name, country,major_city)VALUES(?,?,?);";
        try (Connection conn = JD.connection()) {
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setString(1, city_name);
            statement.setString(2, country);
            statement.setString(3, major_city);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Country> getAll() {
        String SQL = "SELECT * FROM cities;";
        List<Country> country = new ArrayList<>();
        try (Connection conn = JD.connection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Country country1 = new Country();
                country1.setName(resultSet.getString("country"));
                country1.setCityId(resultSet.getInt("id"));
                country1.setMajorId(resultSet.getInt("id"));
                country.add(country1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    public static City getCityById(int id) {
        String SQL = "SELECT * FROM cities WHERE id=?;";
        City city = new City();
        try (Connection conn = JD.connection()) {
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("city_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }
}
