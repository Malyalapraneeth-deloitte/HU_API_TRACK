import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelData {

    public String getString(int row, int column) throws IOException {

        String excelPath = "C:\\Users\\malpraneeth\\Desktop\\Tasks.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        XSSFRow rw = null;
        XSSFCell cell = null;
        String str= null;

        rw = sheet.getRow(row);
        cell = rw.getCell(column);
        if(column == 2){
            int a =(int) cell.getNumericCellValue();
            str=Integer.toString(a);
            return str;

        }
        str = cell.getStringCellValue();
        return str;
    }
    public int getAge( int row, int column) throws IOException {

        String excelPath = "C:\\Users\\malpraneeth\\Desktop\\Tasks.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        XSSFRow rw = null;
        XSSFCell cell = null;

        rw = sheet.getRow(row);

        cell = rw.getCell(column);
        int ag = (int) cell.getNumericCellValue();
        return ag;
    }
    public void writeToken(Object ObjToken) throws IOException {

        String excelPath = "C:\\Users\\malpraneeth\\Desktop\\Tasks.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;

        row= sheet.getRow(1);
        cell = row.createCell(4);
        cell.setCellType(CellType.STRING);
        String token =(String)ObjToken;
        cell.setCellValue(token);

        FileOutputStream fos = new FileOutputStream(excelPath);
        wb.write(fos);
        fos.close();
    }

    public String getToken(int sht,int row,int col) throws IOException {

        String excelPath = "C:\\Users\\malpraneeth\\Desktop\\Tasks.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sht);
        XSSFRow rw = null;
        XSSFCell cell = null;
        String str= null;
        rw = sheet.getRow(row);
        cell = rw.getCell(col);
        str = cell.getStringCellValue();
        return str;

    }

}