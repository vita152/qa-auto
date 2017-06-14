package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Created by QA on 30.05.2017.
 */
public class MainPage extends BasePage<MainPage> {

    @FindBy(className = "settings")
    private WebElement settingIcon;

    @FindBy(xpath = "//div[@class='settings isOpen']")
    private WebElement settingMenu;

    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
    private WebElement logOutMenuItem;


    public MainPage(WebDriver webDriver) {
        super(webDriver, MainPage.class);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplaued(settingIcon);

    }

    public LoginPage logOut() {
        settingIcon.click();
        waitUntilElementDisplaued(settingMenu);
        waitUntilElementClickable(logOutMenuItem, 5).click();
        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    public boolean isPageLoaded() {
        return settingIcon.isDisplayed();
    }
}
