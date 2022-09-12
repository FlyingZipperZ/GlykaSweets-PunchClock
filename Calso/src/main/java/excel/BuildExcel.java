package excel;

import calso.CalsoAppConstants;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

public class BuildExcel {
    // Create Excel file and populate it
    public static void create(Connection con) throws SQLException {
        // Get names from DB
        String[][] fullNames = ExtractNames.firstAndLastNames(con);

        // Create file paths
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a time stamp instant
        Calendar calendar = Calendar.getInstance();

        // Create Worksheets for Excel file
        XSSFSheet sheet = workbook.createSheet("test");

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

        int count = 0;
        int index = 0;

        // Sets up the Excel sheet modular
        for (int i = 0; i < fullNames.length; i++) {

            // Create header
            int past = 14;
            RowHeader rh = new RowHeader();
            rh.setFirstRow(fullNames, index, count, sheet, workbook);
            count++;

            // Create 14 time clock rows
            while(past >= 0) {
                if (dayName == 7) {
                    dayName = 0;
                }
                DataAndFormulas df = new DataAndFormulas();
                df.setIOFormulas(fullNames, past, dayName, sheet, workbook, count, format, i, con);
                dayName++;
                past--;
                count++;
                if(past == -1) {
                    dayName = calendar.get(Calendar.DAY_OF_WEEK);
                }
            }

            // Create a blank row
            BlankRow.createRow(sheet, count);

            // Create final row
            DataAndFormulas.setTimeCalculationRow(fullNames, sheet, workbook, ++count, format, i);

            // create two blank rows back to back
            BlankRow.createRow(sheet, ++count);
            BlankRow.createRow(sheet, ++count);
            count++;

            // Reset for next employee
            index++;
        }

        // Output file
        try {
            FileOutputStream out =  new FileOutputStream(new File(CalsoAppConstants.EMPLOYEE_EXCEL_FILE));
            workbook.write(out);
            out.close();
            System.out.println("Excel with formula cells written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DisplayExcel.readSheetWithFormula("Employee.xlsx");
    }
}
