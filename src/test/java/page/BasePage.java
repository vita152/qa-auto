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

    /**
     * Common method to get current Page title
     *
     * @return String with current Page title
     */
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

    /**
     * Waits until element is clickable using specific max timeout.
     *
     * @param element Webelement to wait for
     * @param timeout Max timeout in seconds
     * @return WebElement after expected contition
     */
    public WebElement waitUntilElementClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilElementDisplaued(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilElementDisplaued (WebElement element){
        return waitUntilElementDisplaued (element, 10);
    }
}
