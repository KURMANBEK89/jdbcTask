package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JD {private static String URL="jdbc:postgresql://localhost:5432/postgres";
    private static String USERNAME="postgres";
    private static String PASSWORD="kurmanbek";

    public static Connection connection(){
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Successfully connected");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
