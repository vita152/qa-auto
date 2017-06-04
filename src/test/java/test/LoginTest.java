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

    public WebDriver webDriver; // объявление переменной

    @BeforeMethod    //BeforeTest  не отрабатывает, по этому BeforeMethod
    public void beforeClass(){
       webDriver = new FirefoxDriver(); // создаем новый объект static - только один, плохо - влияет на скорость, не откроется 2 браузера или 2 вкладки
       }

    @AfterMethod
    public  void afterMethod()
{
    webDriver.quit();
}

    @Test
    public void testLoginPositive() {
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login",
                            "Main page title is wrong");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/",
                            "Wrong URL on Login page");

        loginPage.LoginAs("denvert1@shotspotter.net","Test123!");

        MainPage mainPage = new MainPage(webDriver);
        mainPage.isPageLoaded();

        Assert.assertEquals(mainPage.getPageTitle(), "Shotspotter",
                            "Main page title is wrong");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),
                            "Wrong URL on Main page");
        Assert.assertTrue(mainPage.isPageLoaded(),
                            "Setting Icon is not displayed");
    }

    @Test
    public void testLoginNegativ(){
        String TitleTextLogin ="Shotspotter - Login";
        String TitleTextLoginError ="Login page title is wrong";
        String ExpectetErrorMsg = "The provided credentials are not correct.";
        String ExpectetErrorMsgError = "Invalid Text not correct";
        String DisplaydErrorMsg = "Error messege was not displayd on LoginPage";
        String URLLoginPage ="https://alerts.shotspotter.biz/";
        String URLLoginError ="Login URL on Login page";
        String Email = "denvert1@shotspotter.net";
        String Password = "Test";
        String LoginPAgeError = "LoginPage is not loaded";


        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.getPageTitle(), TitleTextLogin, TitleTextLoginError);
        Assert.assertEquals(loginPage.getPageURL(), URLLoginPage, URLLoginError);
        loginPage = loginPage.LoginAsReturtToLogin(Email,Password);

        loginPage.isPageLoaded();
        Assert.assertTrue(loginPage.isPageLoaded(), LoginPAgeError);
        Assert.assertTrue(loginPage.invalidCredentialsMsgDisplayed(),DisplaydErrorMsg);
        Assert.assertEquals(loginPage.getErrormsgText(), ExpectetErrorMsg, ExpectetErrorMsgError);
    }
}
