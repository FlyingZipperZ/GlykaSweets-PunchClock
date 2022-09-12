package com.excel;

import com.glykasweets.GlykaSweetsAppConstants;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class BuildExcel {

    static final Logger logger = Logger.getLogger(BuildExcel.class.getName());

    // Create Excel file and populate it
    public static void create(Connection con) throws SQLException {
        // Get names from DB
        String[][] fullNames = ExtractNames.firstAndLastNames(con);

        // Create file paths
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a time stamp instant
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();

        // Create Worksheets for Excel file
        XSSFSheet sheet = workbook.createSheet(dateFormat.format(date));

        // Create styles
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFCellStyle style1 = workbook.createCellStyle();

        // Create fonts
        XSSFFont font = workbook.createFont();
        XSSFFont font1 = workbook.createFont();

        // Create format
        DataFormat format = workbook.createDataFormat();

        // Set up fonts for style
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        // Set up fonts for style1
        font1.setBold(false);
        font1.setFontHeight(12);
        style1.setFont(font1);

        // initialize all known and needed attributes for the calendar
        int dayName = calendar.get(Calendar.DAY_OF_WEEK);

        int index = 0;
        int count = 0;

        // Sets up the Excel sheet modular
        while (index < fullNames.length) {

            logger.info(fullNames[index][0] + " " + fullNames[index][1]);

            // Create header rows for name and labels
            RowHeader.setFirstRow(fullNames[index][0], fullNames[index][1], sheet, workbook, count++);
            RowHeader.setSecondRow(count++, sheet, workbook);

            // Nested loop that runs SQL query and writes the data to the excel
            int days = 13;
            while(days >= 0) {

                if (dayName >= 7) {
                    dayName = 0;
                }

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -days);

                Statement stmtIn = con.createStatement();
                ResultSet rsIn = stmtIn.executeQuery(
                        "SELECT \"in_time\" FROM clock_in WHERE id = " + fullNames[index][3] + " and \"in_date\" = '" +
                                cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
                                cal.get(Calendar.DAY_OF_MONTH) + "' ORDER BY \"in_date\" ASC;");

                Statement stmtOut = con.createStatement();
                ResultSet rsOut = stmtOut.executeQuery(
                        "SELECT \"out_time\" FROM clock_out WHERE id = " + fullNames[index][3] + " and \"out_date\" = '" +
                                cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" +
                                cal.get(Calendar.DAY_OF_MONTH) + "' ORDER BY \"out_date\" ASC;");

                if (!rsIn.next()) {
                    DataAndFormulas.setIOFormulas(days, dayName++, sheet, workbook, count++, format, null, null);
                } else {
                    do {
                        rsOut.next();
                        DataAndFormulas.setIOFormulas(days, dayName, sheet, workbook, count++, format, rsIn.getTime("in_time"), rsOut.getTime("out_time"));
                    } while(rsIn.next());
                    dayName++;
                }
                days--;
            }

            // Create a blank row
            BlankRow.createRow(sheet, count++);

            // Create the final row that sums the time and calculates the final pay
            DataAndFormulas.setTimeCalculationRow(Integer.parseInt(fullNames[index][2]), sheet, workbook, count++, format);

            // create two blank rows back to back
            BlankRow.createRow(sheet, count++);

            index++;
        }

        String fileLocation = null;

        // Output file
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT \"file\" FROM filedestination;");
            rs.next();
            fileLocation = rs.getString("file") + "/" + GlykaSweetsAppConstants.EMPLOYEE_EXCEL_FILE;
            rs.close();
            stmt.close();
            FileOutputStream out =  new FileOutputStream(fileLocation);
            workbook.write(out);
            out.close();
            System.out.println("Excel with formula cells written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        DisplayExcel.readSheetWithFormula(fileLocation);
    }
}
