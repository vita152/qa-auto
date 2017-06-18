package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


    /**
     * Constructor MainPage have
     *  super(webDriver)
     *  init Elements
     *  settingIcon is Displayed
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
     * @return Settings is displayed (true or folse)
     */
    public boolean isPageLoaded() {
        return settingIcon.isDisplayed();
    }
}
