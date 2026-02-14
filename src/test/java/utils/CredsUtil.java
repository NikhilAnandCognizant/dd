package utils;



import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CredsUtil {
    public static Object[][] getxl() throws IOException {
        String[][] data = new String[1][2];
        FileInputStream fis = new FileInputStream("./src/test/java/utils/creds.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh =  wb.getSheetAt(0);
        XSSFRow rw = sh.getRow(0);
        DataFormatter df = new DataFormatter();
        String username  = df.formatCellValue(rw.getCell(0));
        String pass  = df.formatCellValue(rw.getCell(1));
        data[0][0] = username;
        data[0][1] =pass;
        System.out.println(username+" "+pass);
        return data;

    }
}
