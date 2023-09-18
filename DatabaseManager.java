package com.mycompany.tnlotr1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/alpha", "root", "");
    }

    public static boolean isUsernameTaken(String username) throws SQLException {
        try (Connection con = getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM accounts WHERE account_name = ?";
            PreparedStatement checkStatement = con.prepareStatement(checkQuery);
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        }
    }

    public static boolean isEmailTaken(String email) throws SQLException {
        try (Connection con = getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM accounts WHERE mail = ?";
            PreparedStatement checkStatement = con.prepareStatement(checkQuery);
            checkStatement.setString(1, email);
            ResultSet resultSet = checkStatement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        }
    }

}

