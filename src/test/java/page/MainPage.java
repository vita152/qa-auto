package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by QA on 30.05.2017.
 */
public class MainPage {
    private WebDriver webDriver;
    private WebElement settingIcon;

    private void InitMainPageWebElement(){
        settingIcon = webDriver.findElement(By.className("settings"));
    }

    public MainPage (WebDriver webDriver){
        this.webDriver = webDriver;
        InitMainPageWebElement();
    }

    public boolean isPageLoaded() {
        return settingIcon.isDisplayed();
    }
}
