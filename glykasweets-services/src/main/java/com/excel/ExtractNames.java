package com.excel;

import com.glykasweets.ConnectionClass;
import com.glykasweets.DB;

import java.sql.*;

public class ExtractNames {
    public static String[][] firstAndLastNames(Connection con) {
        Statement stmt = null;
        String[][] names = new String[DB.getCount(con)][4];

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select \"id\", \"firstName\", \"lastName\", \"rate\" from glykasweets_user order by id asc;");
            int i = 0;
            while (rs.next()) {
                names[i][0] = rs.getString("firstName");
                names[i][1] = rs.getString("lastName");
                names[i][2] = rs.getString("rate");
                names[i][3] = rs.getString("id");
//                System.out.println(rs.getString("firstName") + " " + rs.getString("lastName") + " " + rs.getString("rate"));
                i++;
            }


            stmt.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database Successfully: Names and Rate found");
        return names;
    }

}