package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This file sets the dates and formulas for each individual Cell in the Excel program
 */
public class DataAndFormulas {
    // Sets the cells for each row in which the program is needed
    public void setIOFormulas(String[][] fullNames, int past, int dayName, XSSFSheet sheet, XSSFWorkbook workbook, int count,
                              DataFormat format, int i, Connection con) throws SQLException {

        // Create 2 different excel styles for the cells
        XSSFCellStyle style1 = workbook.createCellStyle();
        XSSFCellStyle style2 = workbook.createCellStyle();

        // Set the styles to how they are formatted
        style1.setDataFormat(format.getFormat("h:mm AM/PM"));
        style2.setDataFormat(format.getFormat("h:mm"));

        // Create excel rows
        XSSFRow rows;

        // Create excel cells
        Cell[] cells = new Cell[14];
        rows = sheet.createRow(count);

        // Strings of days of the week names
        String[] dayNameString = {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};

        // Create workbook style
        CellStyle cs = workbook.createCellStyle();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -past);

        rows = sheet.createRow(count);

        // Create cell A
        cells[0] = rows.createCell(0);
        cells[0].setCellValue(sdf.format(cal.getTime()) + " " +  dayNameString[dayName]);
        cells[0].setCellStyle(cs);

        ////////////////////////////////////////////////////////////////////////////////

        ResultSet rsIn = null;
        ResultSet rsOut;

        try {
            Statement stmtIn = con.createStatement();
            rsIn = stmtIn.executeQuery(
                    "SELECT \"in_time\" FROM clock_in " +
                            "WHERE id = " + fullNames[i][3] + " and \"in_date\" = " +
                            "'2022-" + (cal.get(Calendar.MONTH)+1) + "-" + (cal.get(Calendar.DAY_OF_MONTH)) + "' " +
                            "ORDER BY \"in_date\" ASC;");

//            if(rsIn.next())
//                System.out.println("in time: " + rsIn.getTime("in_time") + " Id: "+ fullNames[i][3]);

            Statement stmtOut = con.createStatement();
            rsOut = stmtOut.executeQuery(
                    "SELECT \"out_time\" FROM clock_out " +
                            "WHERE id = " + fullNames[i][3] + " and \"out_date\" = " +
                            "'2022-" + (cal.get(Calendar.MONTH)+1) + "-" + (cal.get(Calendar.DAY_OF_MONTH)) + "' " +
                            "ORDER BY \"out_date\" ASC;");

//            if(rsOut.next())
//                System.out.println("Out time: " + rsOut.getTime("out_time"));



        // Create cell B
        cells[1] = rows.createCell(1);
        if(rsIn.next())
            cells[1].setCellValue(String.valueOf(rsIn.getTime("in_time")));
        else
            cells[1].setCellValue("None");
        cells[1].setCellStyle(style1);

        // Create cell C
        cells[2] = rows.createCell(2);
        if(rsOut.next())
            cells[2].setCellValue(String.valueOf(rsOut.getTime("out_time")));
        else
            cells[2].setCellValue((Date) null);
        cells[2].setCellStyle(style1);

        // Create cell D
        cells[3] = rows.createCell(3);
        if(rsIn.next())
            cells[3].setCellValue(String.valueOf(rsIn.getTime("in_time")));
        cells[3].setCellStyle(style1);

        // Create cell E
        cells[4] = rows.createCell(4);
        if(rsOut.next())
            cells[4].setCellValue(String.valueOf(rsOut.getTime("out_time")));
        cells[4].setCellStyle(style1);

        // Create cell F
        cells[5] = rows.createCell(5);
        if(rsIn.next())
            cells[5].setCellValue(String.valueOf(rsIn.getTime("in_time")));
        cells[5].setCellStyle(style1);

        // Create cell G
        cells[6] = rows.createCell(6);
        if(rsOut.next())
            cells[6].setCellValue(String.valueOf(rsOut.getTime("out_time")));
        cells[6].setCellStyle(style1);

            stmtIn.close();
            stmtOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        // Create cell H
        cells[7] = rows.createCell(7);
        cells[7].setCellFormula(String.format("IF(%s=\"None\",0,((%s-%s)+(%s-%s)-%s))",
                cells[1].getAddress(),
                cells[2].getAddress(),
                cells[1].getAddress(),
                cells[4].getAddress(),
                cells[3].getAddress(),
                cells[6].getAddress()));
        cells[7].setCellStyle(style2);

        // Create cell I
        cells[8] = rows.createCell(8);
        cells[8].setCellValue("0:00");
        cells[8].setCellStyle(style2);

        // Create cell J
        cells[9] = rows.createCell(9);
        cells[9].setCellFormula(String.format("HOUR(%s)", cells[7].getAddress()));

        // Create cell K
        cells[10] = rows.createCell(10);
        cells[10].setCellFormula(String.format("MINUTE(%s)", cells[7].getAddress()));

        // Create cell L
        cells[11] = rows.createCell(11);

        // Create cell M
        cells[12] = rows.createCell(12);

        // Create cell N
        cells[13] = rows.createCell(13);

//        rsIn.close();
//        rsOut.close();
    }


    /**
     * sets the last row for time and rate calculations
     * @param fullNames Strings with first, last names and the rate of pay
     * @param sheet     XSSFSheet to work with
     * @param workbook  XSSFWorkbook for Excel
     * @param count     The count of names in the database
     * @param format    A format for the spreadsheet
     * @param i         An int that represents who is being shown
     */
    public static void setTimeCalculationRow(String[][] fullNames, XSSFSheet sheet, XSSFWorkbook workbook, int count,
                                             DataFormat format, int i) {
        // Last row that sets the calculation from other rows
        XSSFRow rows;
        XSSFCell[] cells = new XSSFCell[14];

        XSSFCellStyle style = workbook.createCellStyle();
        XSSFCellStyle style3 = workbook.createCellStyle();
        XSSFCellStyle style4 = workbook.createCellStyle();
        XSSFCellStyle style5 = workbook.createCellStyle();

        style3.setDataFormat(format.getFormat("[h]:mm:ss"));

        rows = sheet.createRow(count);

        // Create cell A
        cells[0] = rows.createCell(0);

        // Create cell B
        cells[1] = rows.createCell(1);

        // Create cell C
        cells[2] = rows.createCell(2);

        // Create cell D
        cells[3] = rows.createCell(3);

        // Create cell E
        cells[4] = rows.createCell(4);

        // Create cell F
        cells[5] = rows.createCell(5);

        // Create cell G
        cells[6] = rows.createCell(6);

        // Create cell H
        cells[7] = rows.createCell(7);
        cells[7].setCellFormula(String.format("SUM(H%d:H%d)", count - 15, count - 1));
        cells[7].setCellStyle(style3);

        // Create cell I
        cells[8] = rows.createCell(8);
        cells[8].setCellFormula(String.format("SUM(H%d:H%d)", count - 15, count - 1));
        cells[8].setCellStyle(style3);

        // Create cell J
        cells[9] = rows.createCell(9);
        cells[9].setCellFormula(String.format("HOUR(%s)", cells[7].getAddress()));
//                        cells[9].setCellStyle(style2);

        // Create cell K
        cells[10] = rows.createCell(10);
        cells[10].setCellFormula(String.format("MINUTE(%s)", cells[7].getAddress()));
//                        cells[10].setCellStyle(style2);

        // Create cell L
        cells[11] = rows.createCell(11);
        cells[11].setCellValue(Integer.parseInt(fullNames[i][2]));
        XSSFDataFormat dataFormat = workbook.createDataFormat();
        style5.setDataFormat(dataFormat.getFormat("$#,##0"));
        cells[11].setCellStyle(style5);

        // Create cell M
        cells[12] = rows.createCell(12);
        style4.setDataFormat(dataFormat.getFormat("$#,##0.00"));
        cells[12].setCellFormula(String.format("(%s*%s)+(%s*(%s/60))",
                cells[11].getAddress(),
                cells[9].getAddress(),
                cells[11].getAddress(),
                cells[10].getAddress()));
        cells[12].setCellStyle(style4);

        // Create cell N
        cells[13] = rows.createCell(13);
        cells[13].setCellStyle(style);


    }
}
