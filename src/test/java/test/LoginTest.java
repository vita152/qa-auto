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

    @BeforeMethod
    public void beforeClass(){
       webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
       }

    @AfterMethod
    public  void afterMethod()
{
    webDriver.quit();
}

    @Test
    public void testLoginPositive() {
        String TitleTextLogin ="Shotspotter - Login";
        String TitleTextLoginError ="Main page title is wrong";
        String TitleTextLoginMain ="Shotspotter";
        String TitleTextLoginMainError ="Main page title is wrong";
        String URLLoginPage ="https://alerts.shotspotter.biz/";
        String URLLoginError ="Wrong URL on Login page";
        String URLLoginPageMain ="https://alerts.shotspotter.biz/main";
        String URLLoginMainError ="Wrong URL on Main page";
        String SettingErrorMsg = "Setting Icon is not displayed";
        String Email = "denvert1@shotspotter.net";
        String Password = "Test123!";

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageTitle(), TitleTextLogin, TitleTextLoginError);
        Assert.assertEquals(loginPage.getPageURL(), URLLoginPage, URLLoginError);

        loginPage.login(Email, Password);

        MainPage mainPage = new MainPage(webDriver);
        mainPage.isPageLoaded();

        Assert.assertEquals(mainPage.getPageTitle(), TitleTextLoginMain, TitleTextLoginMainError);
        Assert.assertTrue(mainPage.getPageURL().contains(URLLoginPageMain), URLLoginMainError);
        Assert.assertTrue(mainPage.isPageLoaded(), SettingErrorMsg);
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
        String Email = "denvert1@shotspot.net";
        String Password = "Test";
        String LoginPageError = "LoginPage is not loaded";


        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageTitle(), TitleTextLogin, TitleTextLoginError);
        Assert.assertEquals(loginPage.getPageURL(), URLLoginPage, URLLoginError);

        loginPage.login(Email,Password);
        loginPage.isPageLoaded();

        Assert.assertEquals(loginPage.getPageURL(), URLLoginPage, URLLoginError);
        Assert.assertTrue(loginPage.isPageLoaded(), LoginPageError);
        Assert.assertTrue(loginPage.invalidCredentialsMsgDisplayed(),DisplaydErrorMsg);
        Assert.assertEquals(loginPage.getErrormsgText(), ExpectetErrorMsg, ExpectetErrorMsgError);
    }
}
