package com.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

public class RowHeader {

    public static void setFirstRow(String firstName, String lastName, XSSFSheet sheet, XSSFWorkbook workbook, int count) {

        // Create Excel style
        XSSFCellStyle style = workbook.createCellStyle();

        // Creates a font for the Excel
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        // Creates cells to be filled
        XSSFCell[] cells = new XSSFCell[14];
        XSSFRow rows = sheet.createRow(count);

        // Create cell A
        cells[0] = rows.createCell(0);
        cells[0].setCellValue(String.format("%s, %s", lastName, firstName));
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

    public static void setSecondRow(int count, XSSFSheet sheet, XSSFWorkbook workbook) {
        // Create excel style
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        // Creates a font for the Excel
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        // Creates cells to be filled
        XSSFCell[] cells = new XSSFCell[14];
        XSSFRow rows = sheet.createRow(count);

        // Create cell A
        cells[0] = rows.createCell(0);
        cells[0].setCellValue("Date");
        cells[0].setCellStyle(style);

        // Create cell B
        cells[1] = rows.createCell(1);
        cells[1].setCellValue("In");
        cells[1].setCellStyle(style);

        // Create cell C
        cells[2] = rows.createCell(2);
        cells[2].setCellValue("Out");
        cells[2].setCellStyle(style);

        // Create cell D
        cells[3] = rows.createCell(3);
        cells[3].setCellValue("Total");
        cells[3].setCellStyle(style);

        // Create cell E
        cells[4] = rows.createCell(4);
        cells[4].setCellValue("OT");
        cells[4].setCellStyle(style);

        // Create cell F
        cells[5] = rows.createCell(5);
        cells[5].setCellValue("Pay");
        cells[5].setCellStyle(style);

        // Create cell G
        cells[6] = rows.createCell(6);

        // Create cell H
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

        sheet.autoSizeColumn(0);
    }

}
