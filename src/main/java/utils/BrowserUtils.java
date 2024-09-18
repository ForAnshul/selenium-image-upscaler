package utils;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class BrowserUtils {
	
	private static final Logger logger = Logger.getLogger(BrowserUtils.class.getName());
	
	/*
	 * WebDriver driver;
	 * 
	 * public BrowserUtils(WebDriver driver) { this.driver = driver;
	 * 
	 * }
	 */
	 

	public static void waitForElement(WebDriver driver, By findBy) {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		}
	
	public static String getScreenshot(WebDriver driver, String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);                    
		File destFile = new File(System.getProperty("user.dir") + "\\Screenshots\\" + testCaseName + ".png");
		logger.info("destFile == " + destFile);
		FileUtils.copyFile(Source, destFile);
		return testCaseName;
		
	}
	
	public static ExtentReports getExtentReports() {
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		logger.info(" path == " +  path);
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anshul");
		return extent;
		
	}
	
	
	
	
}