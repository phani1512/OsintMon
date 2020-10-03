package validTestcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import constaints.Constaints;

@Parameters({"emailId","password"})
@Test
public class ValidLogin extends Constaints {
	
	public void InvalidCredentials(String userName, String password) throws IOException {
		test = extent.createTest("ValidLogin");
		
		try {
			Thread.sleep(2000);
			WebElement username = driver.findElement(By.id("txtUserName"));
			username.clear();
			username.sendKeys(userName);
			WebElement Password = driver.findElement(By.id("txtPassword"));
			Password.clear();
			Password.sendKeys(password);
			driver.findElement(By.id("btnLogin1")).click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			
		//need to work
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}



