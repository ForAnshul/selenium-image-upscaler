package base;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class Listeners implements ITestListener {
	
	private static final Logger logger = Logger.getLogger(Listeners.class.getName());
	
	ExtentTest test;
	ExtentReports extent = utils.BrowserUtils.getExtentReports();
	
	public void onTestStart(ITestResult result) {
		logger.info("onTestStart called of listener started");
		test = extent.createTest(result.getMethod().getMethodName());
		logger.info("onTestStart called of listener finished");
	}
	
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getThrowable());
		test.log(Status.PASS, "Test Passed");
	  }
	
	public void onTestFailure(ITestResult result) {
		logger.info("onTestFailure called of listener started");
		test.fail(result.getThrowable());
		//Screenshot
		String filepath = null;
		try {
			WebDriver driver = (WebDriver)
					result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver").get(
							result.getInstance());
			filepath = utils.BrowserUtils.getScreenshot(driver, result.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		logger.info("onTestFailure called of listener finished");
	  }


	public void onFinish(ITestContext context) {
	    extent.flush();
	  }


}
