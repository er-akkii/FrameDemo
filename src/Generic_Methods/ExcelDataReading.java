package Generic_Methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReading {

//		public static void main(String[] args) {
//			getAllData();
//		}
	public static ArrayList<Map<String, String>> list;

	public static Workbook getWorkbook(String filePath) {
		File file = new File(filePath);
		FileInputStream input = null;
		Workbook workbook = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	public static Map<String, String> getRowData(int rowNum, String excelFilePath, String sheetName) {
		Workbook workbook = getWorkbook(excelFilePath);
		Sheet sheet = workbook.getSheet(sheetName);
		Map<String, String> map = new HashMap<String, String>();

		Row rowObj = sheet.getRow(rowNum);
		Row firstRowObj = sheet.getRow(0);

		int cell = rowObj.getLastCellNum();

		for (int j = 0; j <= cell; j++) {
			String key = getCellData(firstRowObj, j);
			String value = getCellData(rowObj, j);
			map.put(key, value);
		}
		return map;
	}

	public static String getCellData(Row rowObj, int cellNum) {
		Cell cellObj = rowObj.getCell(cellNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		CellType cellType = cellObj.getCellType();
		String cellData = null;
		if (cellType == CellType.NUMERIC) {
			Double duble = cellObj.getNumericCellValue();
			cellData = duble.toString();
		}
		if (cellType == CellType.STRING) {
			cellData = cellObj.toString();
		}
		return cellData;
	}

	public static ArrayList<Map<String, String>> getAllData_multipleTimeLogin() {
		Workbook workbook = getWorkbook(
				"C:\\Users\\Admin\\3D Objects\\Practice File\\Vtiger_Automation\\src\\main\\resources\\ExcelData.xlsx");
		Sheet sheet = workbook.getSheet("Sheet1");
		int row = sheet.getLastRowNum();
		list = new ArrayList<Map<String, String>>();

		for (int i = 1; i < row; i++) {
			Map<String, String> map = new HashMap<String, String>();

			Row rowObj = sheet.getRow(i);
			Row firstRowObj = sheet.getRow(0);

			int cell = rowObj.getLastCellNum();

			for (int j = 0; j <= cell; j++) {
				String key = getCellData(firstRowObj, j);
				String value = getCellData(rowObj, j);
				map.put(key, value);
			}
			list.add(map);
		}
		return list;
	}

}
