package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.net.URISyntaxException;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    public void testUploadFunctionality() throws URISyntaxException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        // Perform search action
        homePage.uploadFile();
       // Assert.assertTrue(driver.getTitle().contains("Selenium Automation"));
    }

    @Test(priority = 2)
    public void testValidateUploadImage() throws  InterruptedException {
        HomePage homePage = new HomePage(driver);
        // Perform search action
        boolean isFileUploaded = homePage.validateUploadedFile();
        Assert.assertTrue(isFileUploaded);
    }

    @Test(priority = 3)
    public void testProcessImage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.processImage();
        // TODO : After ckicking the process button, captcha gets visible which can not be automated, also not adviced to automate.
        //  So Human intervention is needed there. Once captcha is entered correctly, Tests will run further.
    }

    @Test(priority = 4)
    public void testGoogleLogin() throws InterruptedException {
        //Capturing the window handles is done but the flow will not be completed as Google blocks/do not support any automated interactions.
        HomePage homePage = new HomePage(driver);
        homePage.googleLogin("XYZ@gmail.com", " ");
    }

}