package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by UI дизайн on 03.06.2017.
 */
public class BasePage {
    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public String getPageURL() {
          return webDriver.getCurrentUrl();
    }

    public String getPageTitle() {
        return  webDriver.getTitle();
    }

 public WebElement waitUntilElementDisplaued (WebElement element, int timeout){
     WebDriverWait wait = new WebDriverWait(webDriver, timeout);
     return wait.until(ExpectedConditions.visibilityOf(element));
 }

}
