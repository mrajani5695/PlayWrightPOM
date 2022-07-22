package com.playwright.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.playwright.pages.HomePage;
import com.playwright.pages.LoginPage;

import com.microsoft.playwright.*;

import org.testng.Assert;

public class LoginTest {

	LoginPage login;
	HomePage home;
	Playwright playwright = Playwright.create();
	BrowserType firefox = playwright.chromium();
	Browser browser = firefox.launch(new BrowserType.LaunchOptions().setHeadless(false));
	Page page = browser.newPage();

	@BeforeTest
	public void setUp() {
	page.navigate("https://www.saucedemo.com/");
	home = new HomePage(page);
	login = new LoginPage(page);
	}

	// Verify title before login
	@Test(priority = 1)
	public void verifyPageTitle() {
	String title = login.verifyTitle();
	Assert.assertEquals(title, "Swag Labs");

	}

	// Login into the application
	@Test(priority = 2)
	public void loginIntoTheApplication() {
	login.loginIntoApplication("standard_user", "secret_sauce");
	}

	// Verify product name after login
	@Test(priority = 3)
	public void verifyProductsName() {
	String productName = home.productName();
	Assert.assertEquals(productName, "Sauce Labs Backpack");

	}

	// Logout from application
	@Test(priority = 4)
	public void logoutFromApplication() {
		login.logoutApplication();

	}

	// Close the browser
	@AfterTest
	public void closeBrowser() {
		browser.close();
	}

}
