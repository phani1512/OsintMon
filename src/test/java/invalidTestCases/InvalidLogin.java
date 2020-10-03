package invalidTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constaints.Constaints;

public class InvalidLogin extends Constaints {

//	@BeforeTest
//	public void Login() {
//		driver.findElement(By.id("lnkLogin")).click();
//	}

	@Test(priority = 1)
	public void NoCredentials() {
		test = extent.createTest("NoCredentials");
		driver.findElement(By.id("lnkLogin")).click();
		try {
			Thread.sleep(2000);
			driver.findElement(By.id("txtUserName")).clear();
			driver.findElement(By.id("txtPassword")).clear();
			driver.findElement(By.id("btnLogin1")).click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert Message is : " + alert.getText());
			alert.accept();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Test(priority = 2)
	public void InvalidCredentials() throws IOException {
		test = extent.createTest("InvalidCredentials");
		try {
			Thread.sleep(2000);
			WebElement username = driver.findElement(By.id("txtUserName"));
			username.clear();
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\phane\\git\\OsintMon\\configs\\Configurations.Properties");
			prop.load(fis);
			username.sendKeys(prop.getProperty("username"));
			WebElement password = driver.findElement(By.id("txtPassword"));
			password.clear();
			password.sendKeys(prop.getProperty("password"));
			driver.findElement(By.id("btnLogin1")).click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			String Actual = driver.findElement(By.id("lblError")).getText();
			String Expected = "Invalid Username or bad password supplied.";
			Assert.assertEquals(Actual, Expected);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
