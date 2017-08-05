package test;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.AppsTocPage;
import page.LoginPage;
import page.MainPage;

/**
 * Created by Вита on 18.07.2017.
 */
public class AppsTocTest {
    public WebDriver webDriver;
    public String Email = "denvert1@shotspotter.net";
    public String Password = "Test123!";
    public AppsTocPage appsTocPage;


    @Parameters({"browser"})

    /**
     * Open Firefox,chrome
     * go to "https://alerts.shotspotter.biz/"
     */
    @BeforeClass
    public void beforeClass(@Optional("Firefox") String browser) throws InterruptedException {
        if (browser.equalsIgnoreCase("Firefox")) {
            FirefoxDriverManager.getInstance().setup();
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeDriverManager.getInstance().setup();
            webDriver = new ChromeDriver();
        }
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(Email, Password);
    }

    /**
     * Close window Firefox
     */
    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test
    public void AbOutTest() {
        String mainPageWindow;
        String termsOfServiceWindow;

        MainPage mainPage = new MainPage(webDriver);
        mainPageWindow=mainPage.getCurrentWindowHandle();
        mainPage.abOutMenuItem();

        appsTocPage=mainPage.openAppsTocPage();
        termsOfServiceWindow=appsTocPage.getCurrentWindowHandle();

        Assert.assertEquals(appsTocPage.getPageURL(), "http://www.shotspotter.com/apps/tos", "Wrong URL on Apps-Toc page");
        Assert.assertEquals(appsTocPage.getPageTitle(), "Apps-TOS", "Apps-Toc page page title is wrong");
        appsTocPage.closeWindow(termsOfServiceWindow);

        mainPage.switchWindowTo(mainPageWindow);
        mainPage.closeButton();
    }
}


