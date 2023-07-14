package constaints;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class Constaints {

	public static WebDriver driver;
	public static ExtentReports extent;
	public ExtentTest test;

	@BeforeSuite
	public static void SetUrl() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.silentOutput","true");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		 System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,
		 "true");

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\phane\\git\\OsintMon\\configs\\Configurations.Properties");
		prop.load(fis);
		driver.get(prop.getProperty("urlmof"));
		Thread.sleep(2000);
		driver.manage().window().maximize();
		try {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(
					System.getProperty("user.dir") + "/test-output/xtentReport.html");
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("SFS");
			extent.attachReporter(spark);
		} catch (Exception ex) {

		}
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			// To capture screenshot path and store the path of the screenshot in the string
			// "screenshotPath"
			// We do pass the path captured by this method in to the extent reports using
			// "logger.addScreenCapture" method.
			String screenshotPath = getScreenShot(driver, result.getName());
			// To add it in the extent report

			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
	}

	@AfterTest
	public void endReport() {
		extent.flush();

	}

	@AfterSuite
	public void tearDown() {
		System.out.println("Testing done");
		driver.quit();
//		this.EmailReport();
	}

	// This method is to capture the screenshot and return the path of the
	// screenshot.
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
//	public void EmailReport() {
//		System.out.println("first line");
//		Properties props = new Properties();
//		 
//		// this will set host of server- you can change based on your requirement 
//		props.put("mail.smtp.host", "smtp.gmail.com");
// 
//		// set the port of socket factory 
//		props.put("mail.smtp.socketFactory.port", "587");
//		props.put("mail.transport.protocol", "smtp");
// 
//		// set socket factory
//		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
// 
//		// set the authentication to true
//		props.put("mail.smtp.auth", "true");
// 
//		// set the port of SMTP server
//		props.put("mail.smtp.port", "587");
//		
//		props.put("mail.smtp.starttls.enable", "true");
//		
// 
//		// This will handle the complete authentication
//		Session session = Session.getDefaultInstance(props,
// 
//				new javax.mail.Authenticator() {
// 
//					protected PasswordAuthentication getPasswordAuthentication() {
//						System.out.println("second line");
//					
// 
//					return new PasswordAuthentication("phaneendraphani20@gmail.com", "Phaneendra@15");
// 
//					}
// 
//				});
//		System.out.println("Session created");
// 
//		try {
// 
//			// Create object of MimeMessage class
//			Message message = new MimeMessage(session);
// 
//			// Set the from address
//			message.setFrom(new InternetAddress("phaneendraphani20@gmail.com"));
// 
//			// Set the recipient address
//			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("phaneendraphani20@gmail.com"));
//            
//                        // Add the subject link
//			message.setSubject("Testing Subject");
// 
//			// Create object to add multimedia type content
//			BodyPart messageBodyPart1 = new MimeBodyPart();
// 
//			// Set the body of email
//			messageBodyPart1.setText("This is message body");
// 
//			// Create another object to add another content
//			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//			
// 
//			// Mention the file which you want to send
//			String filename = "C:\\Users\\phane\\git\\OsintMon\\test-output\\xtentReport.html";
// 
//			// Create data source and pass the filename
//			DataSource source = new FileDataSource(filename);
// 
//			// set the handler
//			messageBodyPart2.setDataHandler(new DataHandler(source));
// 
//			// set the file
//			messageBodyPart2.setFileName(filename);
// 
//			// Create object of MimeMultipart class
//			Multipart multipart = new MimeMultipart();
// 
//			// add body part 1
//			multipart.addBodyPart(messageBodyPart2);
// 
//			// add body part 2
//			multipart.addBodyPart(messageBodyPart1);
// 
//			// set the content
//			message.setContent(multipart);
// 
//			// finally send the email
//			Transport.send(message);
// 
//			System.out.println("=====Email Sent=====");
// 
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
// 
//		}
// 
//	}
// 
}



