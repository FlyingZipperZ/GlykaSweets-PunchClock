package com.glykasweets;

import com.excel.ExtractNames;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DB {
    public static int getCount(Connection con) {
        int count = 0;

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM glykasweets_user;");

            rs.next();
            count = rs.getInt(1);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database Successfully: DB Count found = " + count);
        return count;
    }

    public static ResultSet getTimeIn(Connection con) {
        ResultSet rs = null;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        cal.add(Calendar.DATE, 0);

        String[][] fullNames = ExtractNames.firstAndLastNames(con);

        try {
            Statement stmt = con.createStatement();
//             rs = stmt.executeQuery(
//                    "SELECT \"in_time\" FROM clock_in WHERE id = " + fullNames[0][3] + " and \"in_date\" = '" +
//                            cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
//                            (cal.get(Calendar.DAY_OF_MONTH)) + "' ORDER BY \"in_date\" ASC;");

            rs = stmt.executeQuery(
                    "SELECT \"in_time\" FROM clock_in WHERE id = 11 and \"in_date\" = '2022-09-09'" +
                            " ORDER BY \"in_date\" ASC;");

            if (!rs.next()) {
                System.out.println("rsIn in empty in Java");
            } else {
                System.out.println("rsIn is Not empty in Java");
            }

//            rs.next();
////                    while (rsIn.next()) {
////                        inRS.add(rsIn.getTime("in_time"));
            Format f = new SimpleDateFormat("hh:mm aa");
            System.out.println("The in_time is: " + f.format(rs.getTime("in_time")));
//                    }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    public static ResultSet getTimeOut(Calendar cal, String fullNames, Connection con) {
        String time_out = null;
        ResultSet rs = null;

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT \"out_time\" FROM clock_out WHERE id = " + fullNames + " and \"out_date\" = " +
                            "'2022-" + (cal.get(Calendar.MONTH)+1) + "-" + (cal.get(Calendar.DAY_OF_MONTH)) + "' ORDER BY \"out_date\" ASC;");



            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
    }
}
