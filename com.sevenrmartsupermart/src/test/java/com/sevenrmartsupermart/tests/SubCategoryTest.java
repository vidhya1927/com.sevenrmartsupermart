package com.sevenrmartsupermart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermart.base.Base;
import com.sevenrmartsupermart.constants.DataProviderInput;
import com.sevenrmartsupermart.pages.HomePage;
import com.sevenrmartsupermart.pages.LoginPage;
import com.sevenrmartsupermart.pages.SubCategoryPage;
import com.sevenrmartsupermart.utilities.GeneralUtility;

public class SubCategoryTest extends Base {
	SubCategoryPage subcategorypage;
	LoginPage loginpage;
	HomePage homepage;
	DataProviderInput dataproviderinput;

	@Test(groups = "Sanity")
	public void verifySubCategorySearch() {
		loginpage = new LoginPage(driver);		
		homepage=loginpage.login();
		subcategorypage =homepage.clickOnSubCategory();
		subcategorypage.clickOnSearchIcon();
		subcategorypage.searchSubCategory("Electronics", "Otis");
		String actualResult = subcategorypage.getSearchResult();
		Assert.assertEquals(actualResult, "Otis");
	}

	@Test(dataProvider = "sevenrmart", dataProviderClass = DataProviderInput.class)
	public void VerifySearchDataProvider(String category, String subCategory) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.clickOnSubCategory();
		subcategorypage.clickOnSearchIcon();
		subcategorypage.searchSubCategory(category, subCategory);
		String actualResult = subcategorypage.getSearchResult();
		System.out.println(actualResult);
		Assert.assertEquals(actualResult, subCategory);
	}

	@Test(groups = { "smoke", "regression" })
	public void verifyInvalidSubCategorySearch() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.clickOnSubCategory();
		subcategorypage.clickOnSearchIcon();
		subcategorypage.searchSubCategory("Electronics", "Food Items");
		String actualResult = subcategorypage.getInvalidSearchResult();
		System.out.println(actualResult);
		Assert.assertEquals(actualResult, ".........RESULT NOT FOUND.......");
	}

	@Test(retryAnalyzer = com.sevenrmartsupermart.listeners.RetryAnalyzer.class)
	public void verifyAddNewSubCategory() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.clickOnSubCategory();
		String product = GeneralUtility.getRandomName();
		String actualAlertText = subcategorypage.addNewCategory("Electronics", product);
		Assert.assertEquals(actualAlertText, "Alert!  ");
	}

}
