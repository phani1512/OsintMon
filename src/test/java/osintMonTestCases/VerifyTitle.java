package osintMonTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.OsintMon.Constaints;


public class VerifyTitle extends Constaints {

		@Test(priority = 0)
		public void verifyPageTitle() throws InterruptedException {
			test = extent.createTest("verifyPageTitle");
			// Actual title
			String Title = driver.getTitle().trim();
			System.out.println("The page title is :" + Title);
		//	String Expected_title = "Welcome to OSINTMon";
			String Expected_title = "Welcome to OSINTMon";
			Assert.assertEquals(Title, Expected_title);
			System.out.println("Title verified");
			
		}
//		@Test
//		public void verifyHomePage() {
//			test = extent.createTest("verifyHomePage");
//			String HomeTitle = driver.getTitle();
//			System.out.println("The Home page title is :" + HomeTitle);
//			Assert.assertEquals(HomeTitle, "");
//		}
	}


