package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;


/**
 * Created by Вита on 23.06.2017.
 */
public class MainPageTests {
    public WebDriver webDriver;
    MainPage mainPage;
    public String Email = "denvert1@shotspotter.net";
    public String Password = "Test123!";

    /**
     * Open Firefox,
     * go to "https://alerts.shotspotter.biz/"
     */
    @BeforeClass
    public void beforeClass() {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage = loginPage.login(Email, Password);
    }

    /**
     * Close window Firefox
     */
    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test
    public void testSwitchIncidentsPeriod() throws InterruptedException {
        int[] timeFrameOptions = {24, 3, 7};// список элементов

        for (int timeFrameOption : timeFrameOptions) {// инициализация еще одной переменной, которая является по очереди каждой переменной из списка

            mainPage.switchTimeFramePeriod(timeFrameOption);
            int resultsCount = mainPage.getResultsCount();
            int incidentCardsCount = mainPage.getIncidentCardsCount();

            System.out.println("Period:" + timeFrameOption);
            System.out.println("resultsCount: " + resultsCount);
            System.out.println("incidentCardsCount: " + incidentCardsCount);

            Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");
        }
    }

    @DataProvider
    public static Object[][] timeFrameOptions() {
        return new Object[][]{{24}, {3}, {7}};
    }

    @Test(dataProvider = "timeFrameOptions")
    public void testSwitchIncidentsPeriodByDataProvider(int timeFrameOption) {
        mainPage.switchTimeFramePeriod(timeFrameOption);
        int resultsCount = mainPage.getResultsCount();
        int incidentCardsCount = mainPage.getIncidentCardsCount();

        System.out.println("Period:" + timeFrameOption);
        System.out.println("resultsCount: " + resultsCount);
        System.out.println("incidentCardsCount: " + incidentCardsCount);

        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");
    }
}
