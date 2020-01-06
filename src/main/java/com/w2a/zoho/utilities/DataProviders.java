package com.w2a.zoho.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "masterDP" )
	public static Object[][] getDataSuite1(Method m){
		ExcelReader excel=new ExcelReader(Constants.SUITE1_XL_PATH);
		
		String testCase=m.getName();
		return DataUtil.getData(testCase, excel);
	}
	

}
