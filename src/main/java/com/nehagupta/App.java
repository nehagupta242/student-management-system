package com.nehagupta;

import java.sql.Connection;
import java.sql.SQLException;

import com.nehagupta.database.DBConnection;

public class App {

    public static void main(String[] args) {

        try {
            Connection connection = DBConnection.getConnection();

            System.out.println("Connected to MySQL successfully!");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}