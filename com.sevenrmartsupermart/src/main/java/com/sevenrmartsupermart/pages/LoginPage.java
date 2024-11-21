package com.sevenrmartsupermart.pages;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sevenrmartsupermart.constants.Constants;

public class LoginPage {

	WebDriver driver;
	Properties properties = new Properties();

	@FindBy(xpath = "//input[@type='text']")
	private WebElement userNameField;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordField;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signinButton;
	String invalidError = "//div[@class='alert alert-danger alert-dismissible']";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		try {
			FileInputStream ip = new FileInputStream(Constants.CONFIG_FILE_PATH); // to read file
			properties.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterUserName(String userName) {
		userNameField.sendKeys(userName);
	}

	public void enterPassWord(String password) {
		passwordField.sendKeys(password);
	}

	public void clickSignInButton() {
		signinButton.click();
	}

	public void login(String userName, String password) {
		enterUserName(userName);
		enterPassWord(password);
		clickSignInButton();
	}

	public void login() {
		String userName = properties.getProperty("username");
		String password = properties.getProperty("password");
		enterUserName(userName);
		enterPassWord(password);
		clickSignInButton();
	}

	public void invalidLoginError() {
		WebElement invalidmsg = driver.findElement(By.xpath(invalidError));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(invalidError)));
		System.out.println(invalidmsg.getText());
	}

	public boolean signInIsEnabled() {
		boolean status = signinButton.isEnabled();
		return status;
	}
}
