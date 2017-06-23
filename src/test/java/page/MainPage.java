package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Thread.activeCount;
import static java.lang.Thread.sleep;


/**
 * MainPage have:
 * settingIcon,
 * logOut,
 * map.
 */
public class MainPage extends BasePage<MainPage> {

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

    @FindBy(xpath = "//filter-menu//div[@class='selected-option']")
    private WebElement incidentsTimeSwitch;
    @FindBy(xpath = "//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='24']")
    private WebElement timeFrameSwitch24h;
    @FindBy(xpath = "//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='3']")
    private WebElement timeFrameSwitch3d;
    @FindBy(xpath = "//filter-menu//div[@class='available-options']//*[@class='time-increment' and text()='7']")
    private WebElement timeFrameSwitch7d;
    @FindBy(xpath = "//filter-menu//div[@class='available-options']//*[@class='time-increment'] and text()='']")
    private WebElement timeFrameSwitch;
    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;
    @FindBy(xpath = "//div//*[text()='List']")
    private WebElement listButton;
    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsList;


    public int getResultsCount() {
        return Integer.parseInt(resultsCount.getText().replace(" Results", ""));
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
     * @param i days
     */
    public void switchTimeFramePeriod(int i) {
        incidentsTimeSwitch.click();
        switch (i) {
            case 7:
                timeFrameSwitch7d.click();
                break;
            case 24:
                timeFrameSwitch24h.click();
                break;
            case 3:
                timeFrameSwitch3d.click();
                break;
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
}
