package com.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This file sets the dates and formulas for each individual Cell in the Excel program
 */
public class DataAndFormulas {
    /**
     * setIOFormulas takes variables and creates each row starting with
     * Date, In, Out, Total, OT, and pay
     * Gets in_time, and out_time
     * places them in their respective places
     * @param past represents the 14 days of a 2-week pay cycle
     * @param dayName helps find the name of the day being pushed
     * @param sheet the sheet itself within a workbook that creates each sheet
     * @param workbook workbook is the file that is used
     * @param count count represents the current row we are on
     * @param format not totally sure
     */
    public static void setIOFormulas(int past, int dayName, XSSFSheet sheet, XSSFWorkbook workbook, int count,
                                     DataFormat format, Time rsIn, Time rsOut) throws SQLException {

        // Create 2 different excel styles for the cells
        XSSFCellStyle style1 = workbook.createCellStyle();
        XSSFCellStyle style2 = workbook.createCellStyle();

        // Set the styles to how they are formatted
        style1.setDataFormat(format.getFormat("h:mm AM/PM"));
        style2.setDataFormat(format.getFormat("h:mm"));

        style1.setAlignment(HorizontalAlignment.CENTER);
        style2.setAlignment(HorizontalAlignment.CENTER);

        // Strings of days of the week names
        String[] dayNameString = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        // Create workbook style
        CellStyle cs = workbook.createCellStyle();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        cal.add(Calendar.DATE, -past);

        // Create excel rows
        XSSFRow rows;
        rows = sheet.createRow(count);

        // Create excel cells
        Cell[] cells = new Cell[14];

        // Create cell A
        cells[0] = rows.createCell(0);
        cells[0].setCellValue(sdf.format(cal.getTime()) + " " + dayNameString[dayName]);
        cells[0].setCellStyle(cs);

        // Create cell B
        cells[1] = rows.createCell(1);
        if (rsIn != null) {
            Format f = new SimpleDateFormat("hh:mm aa");
            cells[1].setCellValue(f.format(rsIn));
        } else {
            cells[1].setCellValue("None");
        }
        cells[1].setCellStyle(style1);

        // Create cell C
        cells[2] = rows.createCell(2);
        if (rsOut != null) {
            Format f = new SimpleDateFormat("hh:mm aa");
            cells[2].setCellValue(f.format(rsOut));
        } else {
            cells[2].setCellValue((Date) null);
        }
        cells[2].setCellStyle(style1);

        // Create cell D
        cells[3] = rows.createCell(3);
        cells[3].setCellFormula(String.format("IF(%s=\"None\",0,%s-%s)",
                cells[1].getAddress(),
                cells[2].getAddress(),
                cells[1].getAddress()));
        cells[3].setCellStyle(style2);

        // Create cell E HOUR(%s)
        cells[4] = rows.createCell(4);
        cells[4].setCellFormula(String.format("IF(HOUR(%s)<8,0,%s-\"8:00\")",
                cells[3].getAddress(),
                cells[3].getAddress()));
        cells[4].setCellStyle(style2);

        // Create cell F Minute(%s)
        cells[5] = rows.createCell(5);

        // Create cell G OT =IF(HOUR(%s)<8,0,%s-"8:00")
        cells[6] = rows.createCell(6);


        // Create cell H Pay somehow
        cells[7] = rows.createCell(7);

        // Create cell I
        cells[8] = rows.createCell(8);

        // Create cell J
        cells[9] = rows.createCell(9);

        // Create cell K
        cells[10] = rows.createCell(10);

        // Create cell L
        cells[11] = rows.createCell(11);

        // Create cell M
        cells[12] = rows.createCell(12);

        // Create cell N
        cells[13] = rows.createCell(13);
    }


    /**
     * sets the last row for time and rate calculations
     * @param uRate Strings with first, last names and the rate of pay
     * @param sheet     XSSFSheet to work with
     * @param workbook  XSSFWorkbook for Excel
     * @param count     The count of names in the database
     * @param format    A format for the spreadsheet
     */
    public static void setTimeCalculationRow(int uRate, XSSFSheet sheet, XSSFWorkbook workbook, int count,
                                             DataFormat format) {
        // Last row that sets the calculation from other rows
        XSSFRow rows;
        XSSFCell[] cells = new XSSFCell[14];

        XSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(format.getFormat("[h]:mm"));
        style.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle rate = workbook.createCellStyle();

        rows = sheet.createRow(count);

        // Create cell A
        cells[0] = rows.createCell(0);

        // Create cell B
        cells[1] = rows.createCell(1);

        // Create cell C
        cells[2] = rows.createCell(2);

        // Create cell D
        cells[3] = rows.createCell(3);
        cells[3].setCellFormula(String.format("SUM(D%d:D%d)", count - 15, count - 1));
        cells[3].setCellStyle(style);

        // Create cell E OT
        cells[4] = rows.createCell(4);
        cells[4].setCellFormula(String.format("SUM(E%d:E%d)", count - 15, count - 1));
        cells[4].setCellStyle(style);

        // Create Cell F Rate
        rate.setAlignment(HorizontalAlignment.CENTER);
        cells[5] = rows.createCell(5);
        cells[5].setCellValue(uRate);
        XSSFDataFormat dataFormat = workbook.createDataFormat();
        rate.setDataFormat(dataFormat.getFormat("$#,##0"));
        cells[5].setCellStyle(rate);


        // Create cell G OT  $#,##0.00
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        XSSFCellStyle OT = workbook.createCellStyle();
        OT.setAlignment(HorizontalAlignment.CENTER);
        OT.setFont(font);
        OT.setDataFormat(dataFormat.getFormat("$#,##0.00"));
        cells[6] = rows.createCell(6);
        cells[6].setCellFormula(String.format("((%s*24)-(%s*24))*%s+(%s*24)*(%s*1.5)",
                cells[3].getAddress(),
                cells[4].getAddress(),
                cells[5].getAddress(),
                cells[4].getAddress(),
                cells[5].getAddress()));
        cells[6].setCellStyle(OT);

        // Create cell H Rate From DB
        cells[7] = rows.createCell(7);

        // Create cell I
        cells[8] = rows.createCell(8);

        // Create cell J
        cells[9] = rows.createCell(9);

        // Create cell K
        cells[10] = rows.createCell(10);

        // Create cell L
        cells[11] = rows.createCell(11);

        // Create cell M
        cells[12] = rows.createCell(12);

        // Create cell N
        cells[13] = rows.createCell(13);


    }
}
