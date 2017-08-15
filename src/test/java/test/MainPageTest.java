package test;

import com.sun.jna.platform.win32.Sspi;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;


/**
 * Created by Вита on 23.06.2017.
 */
public class MainPageTest {
    public WebDriver webDriver;
    MainPage mainPage;
    public String Email = "denvert1@shotspotter.net";
    public String Password = "Test123!";

    @Parameters({"browser"})

    /**
     * Open Firefox,
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

    @Test
    public void testValidateIncidentCardFi() {
        String expectedCity = "Denver";
        mainPage.switchTimeFramePeriod(3);
        mainPage.openIncidentsList();
        List<String> listCities = mainPage.getIncidentCardsText("city");
        List<String> listStreets = mainPage.getIncidentCardsText("street");
        List<String> listTimeStamps = mainPage.getIncidentCardsText("time");

        for (String elementCity : listCities) {
            Assert.assertEquals(elementCity, expectedCity, "City is not Denver");
        }
        for (String elementStreet : listStreets) {
            Assert.assertNotEquals(elementStreet, "", "Street address is empty");
        }
        for (String listTimeStamp : listTimeStamps) {
            Assert.assertNotEquals(listTimeStamp, "", "listTimeStamp address is empty");
        }
        Assert.assertTrue(mainPage.TimeStampsUnique(), "There is non-unique TimeStamp");
    }

    public String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));

    }

    @Test
    public void testNewNotification ()  throws IOException{
        String jsonBody = generateStringFromResource("/Users/bmishra/Code_Center/stash/experiments/src/main/resources/Search.json")

        given().
                contentType("application/json").
                body(jsonBody).
                when().
                post("http://dev/search").
                then().
                statusCode(200).
                body(containsString("true"));
    }


}
