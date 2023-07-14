package loginTest;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import constaints.Constaints;

public class ValidLogin extends Constaints {

	@Parameters({ "userName", "password", "expectedUserName" })
	@Test(priority = 3, enabled = false)
	public void validCredentials(String userName, String password, String expectedUserName) throws IOException {
		test = extent.createTest("ValidLogin");
	//	driver.findElement(By.id("lnkLogin")).click();
		try {
			Thread.sleep(2000);
			WebElement username = driver.findElement(By.id("txtUserName"));
			username.clear();
			username.sendKeys(userName);
			WebElement Password = driver.findElement(By.id("txtPassword"));
			Password.clear();
			Password.sendKeys(password);
			driver.findElement(By.id("btnLogin1")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("(//button[@id='btnproceed'])[2]")).size() != 0) {
				boolean dodPresent = driver.findElement(By.xpath("(//button[@id='btnproceed'])[2]")).isDisplayed();
				boolean dodEnabled = driver.findElement(By.xpath("(//button[@id='btnproceed'])[2]")).isEnabled();
				if (dodPresent && dodEnabled) {
					driver.findElement(By.xpath("(//button[@id='btnproceed'])[2]")).click();
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblWelcome")));
					String veriyUserName = driver.findElement(By.id("lblWelcome")).getText().trim();
					expectedUserName.trim();
					Assert.assertEquals(veriyUserName, expectedUserName);

				} else {
					String veriyUserName = driver.findElement(By.id("lblWelcome")).getText();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Assert.assertEquals(veriyUserName, expectedUserName);
					System.out.println("UserName verified");
					Thread.sleep(2000);
					System.out.println("Login Successfull");
				}
			} else {
				String veriyUserName = driver.findElement(By.id("lblWelcome")).getText();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Assert.assertEquals(veriyUserName, expectedUserName);
				System.out.println("UserName verified");
				Thread.sleep(2000);
				System.out.println("Login Successfull");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 4, enabled = false)
	public void viewMonitor() throws IOException {
		test = extent.createTest("viewMonitor");
		WebElement viewMonitor = driver.findElement(By.id("btnviewmonitor"));
		viewMonitor.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String monitor = driver.findElement(By.id("lblWebsitesHeading")).getText().trim();
		monitor.trim();
		String expectedScreen = "Monitors";
		Assert.assertEquals(monitor, expectedScreen);
		System.out.println("Monitors Screen verified");
	}

	@Test(priority = 5, enabled = false)
	public void dropdownSelection() throws IOException, InterruptedException {
		test = extent.createTest("dropdownSelection");
		WebElement Language = driver.findElement(By.id("ddlLanguage"));
		Select select = new Select(Language);
		select.getOptions();
		select.selectByIndex(0);
		Thread.sleep(3000);
		String selectedLanguage = driver.findElement(By.id("lblWebsitesHeading")).getText().trim();
		selectedLanguage.trim();
		String expectedLanguage = "المراقبة";
		Assert.assertEquals(selectedLanguage, expectedLanguage);
	}

	@Test(priority = 6, enabled = false)
	public void logOut() throws IOException {
		test = extent.createTest("logOut");
		WebElement logOut = driver.findElement(By.id("span_logout"));
		logOut.click();
		String Title = driver.getTitle().trim();
		System.out.println("The page title is : " + Title);
		String Expected_title = "Welcome to OSINTMon";
		Assert.assertEquals(Title, Expected_title);
		System.out.println("Logout is Sucessfull & Home Page Title is verified");

	}
}


