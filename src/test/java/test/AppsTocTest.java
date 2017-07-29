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
        MainPage mainPage = new MainPage(webDriver);
        mainPage.abOutMenuItem();
        mainPage.WebWindoww();
    }

}


