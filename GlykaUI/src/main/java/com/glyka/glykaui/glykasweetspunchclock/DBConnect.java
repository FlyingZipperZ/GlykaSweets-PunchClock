package com.glyka.glykaui.glykasweetspunchclock;

import com.glyka.glykaui.database.ConnectionClass;

import java.sql.*;

public class DBConnect {
    public static void DBAdding(String email, String fname, String lname, int rate) {
        Connection c = null;
        Statement stmt = null;
        String uName = "glykasweetspostgres";
        String dbName = "glykasweets";
        String dbpass = "rootpass";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, uName, dbpass);

            stmt = c.createStatement();
            String sql = "insert into glykasweets_user " +
                    "(email, \"firstName\", \"lastName\", \"rate\", \"createdAt\", \"updatedAt\") ";
            String addSequence = String.format("values ('%s', '%s', '%s', %s, now(), now())",
                    email, fname, lname, rate);
            stmt.executeUpdate(sql + addSequence);

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database Successfully");
    }


    /**
     * Check status of employee
     */
    public static boolean check_status(String fname) throws SQLException, ClassNotFoundException {
        Connection c = new ConnectionClass().getConnection();
        boolean status = false;

        // Clock in time with id and first name
        // Then change the glykasweets_user boolean to true

        System.out.println(fname);
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select \"io\" from glykasweets_user where \"firstName\" = '%s';", fname));
            System.out.println("statement run");
            rs.next();
            System.out.println(rs.getBoolean("io"));
            status = rs.getBoolean("io");
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return status;
    }


    /**
     * Take first name and find the correct id number
     * associated with it
     * Then
     * Take the id number and use it to clock in
     * @param fname First name of the person being clocked in
     */
    public static void DB_Clock_in(String fname) throws SQLException, ClassNotFoundException {
        Connection c = new ConnectionClass().getConnection();
        Statement stmt = null;
        // Clock in time with id and first name
        // Then change the glykasweets_user boolean to true
        if(check_status(fname)) {
            System.out.println("Already in");
        } else {
            try {
                // Create statement
                stmt = c.createStatement();
                // Create result set for extracting data
                ResultSet rs = stmt.executeQuery(String.format("select id from glykasweets_user where \"firstName\" = '%s';", fname));
                rs.next();
                System.out.println(rs.getString(1));
                // Insert a new clock in time stamp
                String sql = "insert into clock_in (id, \"in_date\", \"in_time\") ";
                String addSequence = String.format("values (%s,now(),now())", rs.getString(1));
                stmt.executeUpdate(sql + addSequence);
                // Update current users io status to true for later use
                String new_sql = String.format("update glykasweets_user set \"io\" = true where \"firstName\" = '%s';", fname);
                stmt.executeUpdate(new_sql);
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Clocked in Successfully");
        }
    }

    /**
     * Take first name and find the correct id number
     * associated with it
     * Then
     * Take the id number and use it to clock out
     * @param fname First name of the person being clocked out
     */
    public static void DB_Clock_out(String fname) throws SQLException, ClassNotFoundException {
        Connection c = new ConnectionClass().getConnection();
        Statement stmt = null;
        if (!check_status(fname)) {
            System.out.println("Already out");
        } else {
            try {
                // Create statement
                stmt = c.createStatement();
                // Create result set for extracting data
                ResultSet rs = stmt.executeQuery(String.format("select id from glykasweets_user where \"firstName\" = '%s';", fname));
                rs.next();
                System.out.println(rs.getString(1));
                // Insert a new clock out time stamp
                String sql = "insert into clock_out (id, \"out_date\", \"out_time\") ";
                String addSequence = String.format("values (%s,now(),now())", rs.getString(1));
                stmt.executeUpdate(sql + addSequence);
                // Update current users io status to true for later use
                String new_sql = String.format("update glykasweets_user set \"io\" = false where \"firstName\" = '%s';", fname);
                stmt.executeUpdate(new_sql);
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Clocked in Successfully");
        }
    }
}
