package com.example.database;

import java.sql.*;

public class User {

    static Connection con;

    static {
        try {
            con = new ConnectionClass().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    int userCount = getIdCount(con);

    static String[][] names = new String[9][3];
    static String[] fname = new String[9];
    static String name;
    static String delName;

    public User() throws SQLException, ClassNotFoundException {
    }

    public static String[][] getNames() {
        return names;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static void setDelName(String delName) {
        User.delName = delName;
    }

    public static String getDelName() { return delName; }

    public static String getName() {
        return name;
    }

    public static String[] getFNames() {
        return fname;
    }

    public static int getIdCount(Connection con) throws SQLException, ClassNotFoundException {

        String sql = "SELECT count(*) FROM glykasweets_user";
        int count;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        count = rs.getInt(1);

        System.out.println("The number of people in this database are " + count);
        return count;
    }

    public static void getNamesFromDB() throws SQLException, ClassNotFoundException {
        Connection c = new ConnectionClass().getConnection();
        Statement stmt = null;

        // Clock in time with id and first name
        // Then change the glykasweets_user boolean to true

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select \"firstName\", \"lastName\", \"rate\" from glykasweets_user order by id ASC;");

            int j = 0;
            while (rs.next()) {
                names[j][0] = rs.getString("firstName");
                names[j][1] = rs.getString("lastName");
                names[j][2] = rs.getString("rate");
                fname[j] = names[j][0];
                System.out.print(names[j][0] + " ");
                System.out.print(names[j][1] + " : ");
                System.out.println(names[j][2]);
                j++;
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
