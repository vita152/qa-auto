package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/**
 * BasePage
 *
 * @param
 */
public class BasePage{
    protected WebDriver webDriver;


    /**
     *Constructor BasePage
     *
     * @param webDriver  webDriver instans
     */
    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Common method to get current Page URL
     *
     * @return String with current Page URL
     */
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

    /**
     *Waits until element is displayed using specific max timeout and
     * catch Exception when element is not displayed
     *
     * @param element Webelement to wait for
     * @return Is Webelement displayed or not (True or false)
     */
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
     * @return WebElement after expected condition
     */
    public WebElement waitUntilElementClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until element is displayed using specific max timeout.
     *
     * @param element Webelement to wait for
     * @param timeout Max timeout in seconds
     * @return WebElement after expected condition.
     */
    public WebElement waitUntilElementDisplaued(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     *Waits until element is displayed.
     *
     * @param element Webelement to wait for
     * @return WebElement after expected condition using specific max timeout
     */
    public WebElement waitUntilElementDisplaued (WebElement element){
        return waitUntilElementDisplaued (element, 10);
    }
}
