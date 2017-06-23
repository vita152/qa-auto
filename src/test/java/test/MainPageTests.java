package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;

/**
 * Created by Вита on 23.06.2017.
 */
public class MainPageTests {
    public WebDriver webDriver;
    public String Email = "denvert1@shotspotter.net";
    public String Password = "Test123!";

    /**
     * Open Firefox,
     * go to "https://alerts.shotspotter.biz/"
     */
    @BeforeMethod
    public void beforeClass(){
        webDriver = new FirefoxDriver();//ChromeDriver();
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

    @Test
    public void testSwitchIncidentsPeriod() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        MainPage mainPage = loginPage.login(Email,Password);

        mainPage.switchTimeFramePeriod(3); //home work
        //int resultsCount = mainPage.getResultsCount();
        int incidentCardsCount = mainPage.getIncidentCardsCount();   //home work

        //Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");
    }
}
