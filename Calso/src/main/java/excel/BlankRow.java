package excel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class BlankRow {
    // Creates blank rows for the file
    public static void createRow(XSSFSheet sheet, int count) {
        XSSFRow row = sheet.createRow(count);
        for(int i = 0; i <= 14; i++) {
            row.createCell(i);
        }
    }
}
