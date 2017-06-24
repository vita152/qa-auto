package test;

import jdk.management.resource.internal.inst.StaticInstrumentation;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

/**
 * Created by Java Script on 20.05.2017.
 */
public class LoginTest {

    public WebDriver webDriver;
    public String Email = "sst.tau@gmail.com";
    public String Password = "P@ssword123";

    /**
     * Open Firefox, (Webdriver init)
     * go to "https://alerts.shotspotter.biz/"
     */
    @BeforeMethod
    public void beforeMethod(){
       webDriver = new FirefoxDriver();
       webDriver.navigate().to("https://alerts.shotspotter.biz/");
    }

    /**
     * Close window Firefox
     */
    @AfterMethod
    public  void afterMethod()
{
    webDriver.quit();
}

    /**
     * Type right login and password, click "goButton", and get MainPage
     */
    @Test
    public void testLoginPositive() {
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong URL on Login page");

        loginPage.login(Email, Password);

        MainPage mainPage = new MainPage(webDriver);
        mainPage.isPageLoaded();

        Assert.assertEquals(mainPage.getPageTitle(), "Shotspotter", "Main page title is wrong");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"), "Wrong URL on Main page");
        Assert.assertTrue(mainPage.isPageLoaded(), "Setting Icon is not displayed");
    }

    /**
     * Type wrong login and password, click "goButton", and get Error Message
     */
    @Test
    public void testLoginNegativ(){

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Login page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Login URL on Login page");

        loginPage.login(Email, "Test");
        loginPage.isPageLoaded();

        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Login URL on Login page");
        Assert.assertTrue(loginPage.isPageLoaded(), "LoginPage is not loaded");
        Assert.assertTrue(loginPage.invalidCredentialsMsgDisplayed(),"Error messege was not displayd on LoginPage");
        Assert.assertEquals(loginPage.getErrormsgText(), "The provided credentials are not correct.", "Invalid Text not correct");
    }

    /**
     * Type right login and password, click "goButton", get MainPage,
     * in drop-down-menu "settings" click "logOut",
     * and get LoginPage
     */
    @Test
    public void LogOutTest() {

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong URL on Login page");

        MainPage mainPage = loginPage.login(Email, Password);

        Assert.assertEquals(mainPage.getPageTitle(), "Shotspotter", "Main page title is wrong");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"), "Wrong URL on Main page");
        Assert.assertTrue(mainPage.isPageLoaded(), "Setting Icon is not displayed");

        loginPage = mainPage.logOut();
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong URL on Login page");
    }
}
