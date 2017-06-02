package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by QA on 30.05.2017.
 */
public class MainPage {
    private WebDriver webDriver;

    @FindBy(className="settings")
    private WebElement settingIcon;


    public MainPage (WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public boolean isPageLoaded() {
        return settingIcon.isDisplayed();
    }

    public void setSettingIcon(WebElement settingIcon) {
        this.settingIcon = settingIcon;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
