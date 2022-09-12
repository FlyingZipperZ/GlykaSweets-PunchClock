package excel;

import org.apache.poi.xssf.usermodel.*;

/**
 * This file represents the names of all of the users of the application
 * and places them in the header of each excel to display who's time sheet this is.
 */

public class RowHeader {
    // Sets the header row with Names and column names
    public void setFirstRow(String[][] fullNames, int index, int count, XSSFSheet sheet, XSSFWorkbook workbook){

        // Create excel style
        XSSFCellStyle style = workbook.createCellStyle();

        // Creates a font for the excel
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        // Creates cells to be filled
        XSSFCell[] cells = new XSSFCell[14];
        XSSFRow rows = sheet.createRow(count);

        // Create cell A
        cells[0] = rows.createCell(0);
        cells[0].setCellValue(String.format("%s, %s",fullNames[index][1], fullNames[index][0]));
        cells[0].setCellStyle(style);

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
        cells[7].setCellValue("Lunch");
        cells[7].setCellStyle(style);

        // Create cell I
        cells[8] = rows.createCell(8);
        cells[8].setCellValue("Time Clock");
        cells[8].setCellStyle(style);

        // Create cell J
        cells[9] = rows.createCell(9);
        cells[9].setCellValue("# Hrs");
        cells[9].setCellStyle(style);

        // Create cell K
        cells[10] = rows.createCell(10);
        cells[10].setCellValue("# Min");
        cells[10].setCellStyle(style);

        // Create cell L
        cells[11] = rows.createCell(11);
        cells[11].setCellValue("Rate");
        cells[11].setCellStyle(style);

        // Create cell M
        cells[12] = rows.createCell(12);
        cells[12].setCellValue("Paycheck");
        cells[12].setCellStyle(style);

        // Create cell N
        cells[13] = rows.createCell(13);
        cells[13].setCellStyle(style);
    }
}
