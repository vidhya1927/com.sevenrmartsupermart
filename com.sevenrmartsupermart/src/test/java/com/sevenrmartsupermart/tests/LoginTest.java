package com.sevenrmartsupermart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermart.base.Base;
import com.sevenrmartsupermart.pages.HomePage;
import com.sevenrmartsupermart.pages.LoginPage;
import com.sevenrmartsupermart.utilities.ExcelReader;

public class LoginTest extends Base {
	ExcelReader excelreader=new ExcelReader();
	LoginPage loginpage;
	HomePage homepage;	
	
	@Test
	public void verifyAdminUserLogin()
	{
		excelreader.setExcelFile("LoginPageData", "SignInData");
		String userNameData=excelreader.getCellData(0,1);
		String passwordData=excelreader.getCellData(1,1);

		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		//loginpage.login("admin", "admin");
		loginpage.login(userNameData, passwordData);
		
		String actualProfileName= homepage.getProfileName();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName);
	}
	
	@Test	
	public void verifyInvalidLogin()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login("user", "admin");	
		loginpage.invalidLoginError();				
	}
	@Test
	public void verifySignInButtonEnabled()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		boolean flag = loginpage.signInIsEnabled();
		Assert.assertEquals(flag, true);
	}

}
