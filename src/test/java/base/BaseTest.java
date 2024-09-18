package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.logging.Logger;

public class BaseTest {

    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    protected WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) {
        // Initialize the WebDriver based on the browser parameter
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.get("https://www.spyne.ai/image-upscaler");
        logger.info("setUp execution finished.");
    }

	
	  @AfterTest public void tearDown() {
		  if (driver != null) { 
			  driver.quit(); 
			  } 
		  }
	 

}
