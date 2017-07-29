package page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;


/**
 * MainPage have:
 * settingIcon,
 * logOut,
 * map.
 */
public class MainPage extends BasePage {

    @FindBy(className = "settings")
    private WebElement settingIcon;
    /**
     * drop down menu "settings"
     */
    @FindBy(xpath = "//div[@class='settings isOpen']")
    private WebElement settingMenu;
    /**
     * WebElement "logOut" in drop down menu "settings"
     */
    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
    private WebElement logOutMenuItem;
    @FindBy(xpath = "//settings-drop-down//li[text()='About']")
    private WebElement abOutMenuItem;
    @FindBy(xpath = "//filter-menu//div[@class='selected-option']")
    private WebElement incidentsTimeSwitch;
    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;
    @FindBy(xpath = "//div//*[text()='List']")
    private WebElement listButton;
    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsList;
    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement modalcontent;
    @FindBy(xpath = "//div[@class='cell day']/div [@class='content']")
    private List<WebElement> incidentTimeStamp;
    @FindBy(xpath = "//div[@class='about-dialog-content']//*[text()='terms of service']")
    private WebElement termsofservice;
    @FindBy(xpath = "//div [@class='modal-footer']//*[@class='btn btn-primary']")
    private WebElement close;


    /**
     * @param period
     * @return
     */
    private WebElement getTimeFramePeriodOption(int period) {
        return webDriver.findElement(By.xpath(String.format("//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='%d']", period)));
    }


    /**
     * Numeric from RESULTS
     *
     * @return quantity of RESULTS
     */
    public int getResultsCount() {
        return Integer.parseInt(resultsCount.getText().replace(" RESULTS", ""));
    }

    /**
     * Constructor MainPage have
     * super(webDriver)
     * init Elements
     * settingIcon is Displayed
     *
     * @param webDriver super(webDriver)
     */
    public MainPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplaued(settingIcon);
    }

    /**
     * Click logOut in MainPage to return in LoginPage
     *
     * @return LoginPage after click logOut
     */
    public LoginPage logOut() {
        settingIcon.click();
        waitUntilElementDisplaued(settingMenu);
        waitUntilElementClickable(logOutMenuItem, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    /**
     * MainPage is Loaded when Settings is displayed
     *
     * @return Settings is displayed (true or false)
     */
    public boolean isPageLoaded() {
        return settingIcon.isDisplayed();
    }

    /**
     * Choose days in timeFrameSwitch
     *
     * @param period days
     */
    public void switchTimeFramePeriod(int period) {
        incidentsTimeSwitch.click();
        getTimeFramePeriodOption(period).click();
        waitResultCountUpdated(10);
    }

    public void waitResultCountUpdated(int maxTimeoutSec) {
        int initialResultCount = getResultsCount();

        for (int i = 0; i < maxTimeoutSec; i++) {
            try {
                sleep(100);
                int currentResult = getResultsCount();
                if (initialResultCount != currentResult) {
                    break;
                }
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Count Element in AdresList
     *
     * @return quantity of Elements
     */
    public int getIncidentCardsCount() {
        listButton.click();
        return incidentsList.size();
    }

    /**
     * Click listButton and wait incidentsList
     */
    public void openIncidentsList() {
        listButton.click();
        waitUntilElementClickable(incidentsList.get(1), 10);
    }


    /**
     * Get name Elements in incidentsList and return their text
     *
     * @param detal - name Elements in incidentsList
     * @return Text of the Elements from the List incidentsList
     */
    public List<String> getIncidentCards(String detal) {
        List<String> listTimeStamps = new ArrayList<String>();
        String XpathElement = getIncident(detal);
        for (WebElement incidentTimeStamps : incidentsList) {
            String TimeStampsText = incidentTimeStamps.findElement(By.xpath(XpathElement)).getText();
            listTimeStamps.add(TimeStampsText);
        }
        return listTimeStamps;
    }

    /**
     * @param detal Get name, return xpath
     * @return xpath for Elements:time/street/city
     */
    public String getIncident(String detal) {
        switch (detal.toLowerCase()) {
            case "time":
                return "//div[@class='cell day']//div [@class='content']";
            case "street":
                return "//div[@class='address']";
            case "city":
                return "//div[@class='city S']";
            default:
                return "";
        }
    }

    /**
     * @return unique Element incidentTimeStamp true/false
     */
    public boolean TimeStampsUnique() {
        Set incidentTimeStampCount = new HashSet(incidentTimeStamp);
        if (incidentTimeStampCount.size() == incidentTimeStamp.size()) {
            return true;
        }
        return false;
    }

    /**
     * Open abOutMenuItem(About)
     */
    public void abOutMenuItem() {
        settingIcon.click();
        waitUntilElementDisplaued(settingMenu);
        waitUntilElementClickable(abOutMenuItem, 5).click();
    }

    /**
     * switch Window handles
     */
    public void WebWindoww() {
        termsofservice.click();
        String parentWindow = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                webDriver.switchTo().window(windowHandle);

                AppsTocPage appsTocPage = new AppsTocPage(webDriver);
                appsTocPage.isLoaded();
                Assert.assertEquals(getPageURL(), "http://www.shotspotter.com/apps/tos", "Wrong URL on Apps-Toc page");
                Assert.assertEquals(appsTocPage.getPageTitle(), "Apps-TOS", "Apps-Toc page page title is wrong");

                webDriver.close();
                webDriver.switchTo().window(parentWindow);
            }
            close.click();
        }
    }
}