package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by UI дизайн on 03.06.2017.
 */
public class BasePage <T>{
    protected WebDriver webDriver;
    private Class<T> clazz;


    public BasePage(WebDriver webDriver, Class<T> clazz) {
        this.webDriver = webDriver;
        this.clazz = clazz;
    }

    public String getPageURL() {
        return webDriver.getCurrentUrl();
    }

    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitUntilElementDisplaued (element, 3).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
    public WebElement waitUntilElementDisplaued(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
      //  return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilElementDisplaued (WebElement element){
        return waitUntilElementDisplaued (element, 10);
    }
}
