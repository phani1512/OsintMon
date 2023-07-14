package webSites;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import constaints.Constaints;

public class AddValidURL extends Constaints {
	@Test(priority = 7, enabled = false)
	public void ValidURL() {
		test = extent.createTest("InvalidURL");
		try {
			Thread.sleep(3000);
			WebElement websites = driver.findElement(By.xpath("(//div[@class='thumbnail'])[3]"));
			websites.click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			String Actual = driver.findElement(By.id("lblWebsitesHeading")).getText().trim();
			String Expected = "Website(s)";
			Expected.trim();
			Assert.assertEquals(Actual, Expected);
			WebElement enterURL = driver.findElement(By.id("txtSiteURL"));
			enterURL.sendKeys("User");
			driver.findElement(By.id("btnViewSite")).click();
			Thread.sleep(3000);
			String MainWindow = driver.getWindowHandle();
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();
			while (i1.hasNext()) { 
				String ChildWindow = i1.next();

				if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

					// Switching to Child window
					driver.switchTo().window(ChildWindow);
					WebElement Actual_ErrorMessage = driver
							.findElement(By.xpath("//span[@jsselect='heading']"));
					Actual_ErrorMessage.getText();
					String Expected_ErrorMessage = "This site can’t be reached";
					Assert.assertEquals(Actual_ErrorMessage, Expected_ErrorMessage);
					System.out.println("Error Message is verified");
					driver.close();
				}
			}
			driver.switchTo().window(MainWindow);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
