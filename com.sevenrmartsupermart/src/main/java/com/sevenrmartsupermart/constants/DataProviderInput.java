package com.sevenrmartsupermart.constants;

import org.testng.annotations.DataProvider;
import com.sevenrmartsupermart.utilities.ExcelReader;

public class DataProviderInput {
	ExcelReader excelreader = new ExcelReader();

	@DataProvider(name = "sevenrmart")
	public Object[][] datasevenrmart() {
		excelreader.setExcelFile("SubCategory", "SearchData");
		return excelreader.getMultidimentionalData(2, 2);
	}

}
