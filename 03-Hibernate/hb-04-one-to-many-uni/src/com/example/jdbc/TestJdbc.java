package com.example.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        String jdbcUrl="jdbc:mysql://localhost:3306/hb-04-one-to-many-uni?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String password = "hbstudent";
        try {
            System.out.println("Connecting to database: "+ jdbcUrl);
            Connection connection= DriverManager.getConnection(jdbcUrl,user,password);
            System.out.println("Connection Successful !!!");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
