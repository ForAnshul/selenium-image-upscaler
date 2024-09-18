package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class HomePage {

    private static final Logger logger = Logger.getLogger(HomePage.class.getName());
    private String fileName = "sample.jpeg";
    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page Factory-WebElements
    @FindBy(xpath = "//*[@id=\"__next\"]/div[2]/section[2]/div/div/div[1]/div[2]")
    WebElement uploadFileBtnLocator;

    @FindBy(xpath = "//img[@alt='input']")
    WebElement uploadedImageBtnLocator;

    @FindBy(xpath = "//button[contains(@class, 'px-10')]")
    WebElement processImageBtnLocator;

    @FindBy(xpath = "//div[contains(@class,'bottom-modal-sm')]/descendant::img[@alt='google icon']")
    WebElement googleLoginBtnLocator;

    @FindBy(xpath = "//input[@type='email']")
    WebElement googleEmailLocator;

    @FindBy(xpath = "//span[text()='Next']")
    WebElement googleNextBtnLocator;

    @FindBy(xpath = "//input[@type='password']")
    WebElement googlePwdBtnLocator;

    @FindBy(xpath = "//button[contains(@class, 'px-3')]")
    WebElement downloadBtnLocator;

    //Actions
    public void uploadFile() throws URISyntaxException, InterruptedException {
        logger.info("UploadFile execution started");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/section[2]/div/div/div[1]/div[2]")));
            System.out.println("is displayed ::" + until.isDisplayed());

            File fileFromResource = getFileFromResource(fileName);
            if (fileFromResource != null) {
                 until.findElement(By.xpath("//input[@type='file']")).sendKeys(fileFromResource.getAbsolutePath());
                uploadFileBtnLocator.sendKeys(fileFromResource.getAbsolutePath());
                logger.info("File uploaded successfully: " + fileFromResource.getAbsolutePath());
            } else {
                logger.info("File not found: " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("An error occurred during file upload: "+ e);
        }
        logger.info("uploadFile execution finished");
    }


    public boolean validateUploadedFile() throws InterruptedException {
        logger.info("validateUploadedFile execution started");
        //Thread.sleep(5000);
        BrowserUtils.waitForElement(driver, By.xpath("//button[contains(@class, 'px-10')]"));
        String imageURl = uploadedImageBtnLocator.getAttribute("src");
        logger.info("image url = " + imageURl);
        String imageName = imageURl.substring(imageURl.lastIndexOf("/") + 1);
        String imgName = imageName.replaceAll("%20", " "); // remove %20 from url
        logger.info("uploaded file name :: " + fileName);
        logger.info("retrieved file name :: " + fileName);
        logger.info("validateUploadedFile execution finished");
        return fileName.equals(imgName);
    }

    public void processImage() throws InterruptedException {
        logger.info("processImage execution started");
        Thread.sleep(2000);
        processImageBtnLocator.click();
        logger.info("processImage execution finished");
    }


    public void googleLogin(String email, String password) throws InterruptedException {
        logger.info("googleLogin execution started");
        //BrowserUtils.waitForElement(driver,By.xpath("//div[contains(@class,'bottom-modal-sm')]/descendant::img[@alt='google icon']"));
        Thread.sleep(10000);
        googleLoginBtnLocator.click();
        Thread.sleep(1000);
        Set<String> windowIds = driver.getWindowHandles();
        Iterator<String> itr = windowIds.iterator();
        String defaultWindow = itr.next();
        String childWindow = itr.next();
        driver.switchTo().window(childWindow);
        logger.info("I switched to " + driver.getTitle());

        googleEmailLocator.sendKeys(email);
        googleNextBtnLocator.click();
        googlePwdBtnLocator.sendKeys(password);
        googleNextBtnLocator.click();
        logger.info("googleLogin execution finished");
        driver.switchTo().window(defaultWindow);

        //Locate the Download button
        downloadBtnLocator.click();
        ;
        logger.info("googleLogin execution finished");
    }

   
    private File getFileFromResource(String fileName) throws URISyntaxException {
        logger.info("getFileFromResource execution started");
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            logger.info("getFileFromResource execution finished");
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            logger.info("getFileFromResource execution finished");
            return new File(resource.toURI());
        }
    }


}