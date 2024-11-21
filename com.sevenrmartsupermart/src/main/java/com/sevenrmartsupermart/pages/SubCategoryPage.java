package com.sevenrmartsupermart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sevenrmartsupermart.utilities.PageUtility;
import com.sevenrmartsupermart.utilities.GeneralUtility;

public class SubCategoryPage {
	WebDriver driver;

	@FindBy(xpath = "//a[@onclick='click_button(2)']")
	WebElement searchIcon;
	@FindBy(xpath = "//button[@class='btn btn-danger btn-fix']")
	WebElement searchButton;
	@FindBy(xpath = "//select[@class='form-control selectpicker']")
	WebElement selectDropDown;
	@FindBy(xpath = "//input[@placeholder='Sub Category']")
	WebElement subCategory;
	@FindBy(xpath = "//span[@id='res']//center")
	WebElement invalidSearchResult;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	WebElement newButton;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']//h5")
	WebElement saveAlert;

	public SubCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnSearchIcon() {
		searchIcon.click();
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void clickOnDropDown() {
		selectDropDown.click();
	}

	public void selectCategory(String Category) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(selectDropDown, Category);
		selectDropDown.sendKeys(Category);
	}

	public void enterSubCategory(String subCategorydata) {
		subCategory.click();
		subCategory.sendKeys(subCategorydata);
	}

	public String getSearchResult() {
		GeneralUtility generalutility = new GeneralUtility();
		return generalutility.getAttribute(subCategory, "value");
	}

	public String getInvalidSearchResult() {

		return invalidSearchResult.getText();
	}

	public void searchSubCategory(String category, String subCategorydata) {

		clickOnSearchButton();
		selectCategory(category);
		enterSubCategory(subCategorydata);
		searchButton.click();
	}

	public String addNewCategory(String category, String subCategorydata) {

		newButton.click();
		selectCategory(category);
		enterSubCategory(subCategorydata);
		saveButton.click();
		return saveAlert.getText();

	}

}
