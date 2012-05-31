package com.weiss.excel;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadOldExcel {
	public static String fileToBeRead = "D:/Users/acer/Documents/stus.xlsx";
	public static void main(String argv[]) {
		try {
			Workbook workbook=null;
			try {
				workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
	        } catch (Exception ex) {
	        	workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
	        }
	        Sheet sheet = workbook.getSheet("Sheet1");
			String sql = "insert into student ( id,  name, password, sex, classid, email,  address, phone ) values ";
			String sql1="";
			int rows = sheet.getPhysicalNumberOfRows();
			
			for (int r = 1; r < rows; r++) {
				StringBuffer bStr=new StringBuffer("");
				Row row = sheet.getRow(r);
				if (row != null) {
					bStr.append("(");
					Iterator<Cell> cells=row.cellIterator();
					while(cells.hasNext()){
						Cell cell =  cells.next();
						if (cell != null) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_FORMULA:
								break;
							case Cell.CELL_TYPE_NUMERIC:
								long temp=(long) cell.getNumericCellValue();
								bStr.append("'"+temp+"',");
								break;
							case Cell.CELL_TYPE_STRING:
								bStr.append("'"+cell.getStringCellValue()+"',");
								break;
							case Cell.CELL_TYPE_BLANK:// blank
								break;
							default:
								bStr.append("'"+"',");
							}
						}else{
							bStr.append("'',");
						}
						
					}
					sql1+=bStr.substring(0, bStr.length()-1)+"),";
				}
			}
			sql+=sql1.substring(0,sql1.length()-1);
			System.out.println(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}