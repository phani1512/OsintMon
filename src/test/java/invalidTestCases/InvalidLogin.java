package invalidTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constaints.Constaints;

public class InvalidLogin extends Constaints {
	
	@BeforeTest
	public void Login() {
		driver.findElement(By.id("lnkLogin")).click();
	}

	@Test(priority= 1)
	public void NoCredentials() {
		test = extent.createTest("verifyPageTitle");
		try {
			Thread.sleep(2000);
			driver.findElement(By.id("txtUserName")).clear();
			driver.findElement(By.id("txtPassword")).clear();
			driver.findElement(By.id("btnLogin1")).click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert Message is : "   + alert.getText());
			alert.accept();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
