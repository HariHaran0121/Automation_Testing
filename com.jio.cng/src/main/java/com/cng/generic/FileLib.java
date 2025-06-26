package com.cng.generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileLib {

	public String getProperty(String key) throws Exception
	{
		FileInputStream fis = new FileInputStream("./testdata/commondata.property");
		Properties prop = new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);
		return data;
	}
	
	public String getExcelData(String sheetname,int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream userdata=new FileInputStream("./testdata/userdata.xlsx");
		Workbook wb = WorkbookFactory.create(userdata);
		String data = wb.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
		return data;
	}
	
	public void putExcelData(String sheetname, int row, int column) throws EncryptedDocumentException, IOException
	{
		FileInputStream userdata = new FileInputStream("./testdata/userdata.xlsx");
		
	}
}
