package api.utilities;

import java.io.*;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class XLUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;
    
    public XLUtility(String path) {
    	
    	this.path=path;  	
    	 }
	
    public int getRowCount (String sheetName) throws IOException  {
    	
    	fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	sheet = workbook.getSheet(sheetName);
    	int rowcount = sheet.getLastRowNum();
    	workbook.close();
    	return rowcount;
    	
    }

public int getCellCount (String sheetName , int rownum) throws IOException  {
    	
    	fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	sheet = workbook.getSheet(sheetName);
    	row.getSheet().getRow(rownum);
    	int cellcount = row.getLastCellNum();
    	workbook.close();
    	fi.close();
    	return cellcount;
    	
    }

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		}
		
		catch(Exception e) {
			data = "in catch block";
		}
		
		workbook.close();
		fi.close();
		return data;
		
	}










}