package calso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public static ResultSet getTimeIn(Calendar cal, String fullNames, Connection con) {
        ResultSet rs = null;

        try {
            Statement stmt = con.createStatement();
             rs = stmt.executeQuery(
                    "SELECT \"in_time\" FROM clock_in WHERE id = " + fullNames + " and \"in_date\" = " +
                            "'2022-" + (cal.get(Calendar.MONTH)+1) + "-" + (cal.get(Calendar.DAY_OF_MONTH)) + "' ORDER BY \"in_date\" ASC;");

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
