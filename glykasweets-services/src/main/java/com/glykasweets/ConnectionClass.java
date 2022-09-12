package com.glykasweets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String dbName = "glykasweets";
        String userName = "odysseasfourakis";
        String password = "rootpass";

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, userName, password);

        if (connection != null) {
            System.out.println("\nConnection OK\n");
        } else {
            System.out.println("\nConnection Fail\n");
        }
        return connection;
    }
}
