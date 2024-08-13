package com.ninza.hrm.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public String getDataFromExcel(String sheetname, int rowno, int cellno) throws Throwable {
		FileInputStream fis = new FileInputStream("");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet(sheetname);
		Row row = sheet.getRow(rowno);
		Cell cell = row.getCell(cellno);
		String data = cell.getStringCellValue();
		return data;
	}

	public void writeDataToExcel(String sheetname, int rowno, int cellno, String data) throws Throwable {
		FileInputStream fis = new FileInputStream("");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet(sheetname);
		Row row = sheet.getRow(rowno);
		Cell cell = row.createCell(cellno);
		cell.setCellValue(data);
	}

	public int getRowno(String sheetname, int rowno, int cellno, String data) throws Throwable {
		FileInputStream fis = new FileInputStream("");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet(sheetname);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}
	
	public static  Object[][] getMultipleDataFromExcelSheet(String path, String sheetName)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(path);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet(sheetName);
		int row = sheet.getPhysicalNumberOfRows();
		System.out.println(row);
		int cell = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] test = new Object[row-1][cell];
		for (int i = 1; i < row; i++)
		{
			for(int j=0;j<cell;j++)
			{
				test[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
			
		}
		
		return test;
	}
}