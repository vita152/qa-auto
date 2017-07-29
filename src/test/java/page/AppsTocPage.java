package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * Created by Вита on 18.07.2017.
 */
public class AppsTocPage extends BasePage {


    @FindBy(xpath = "//div[@class ='entry']//h3[@class='black' and text ()='ShotSpotter - Terms of Service']")
    private WebElement termsOfServiceApp;
    @FindBy(xpath = "//div[@class='about-dialog-content']//*[text()='terms of service']")
    private WebElement termsofservice;


    /**
     * Constructor AppsTocPage have
     * super(webDriver)
     * init Elements
     * termsOfServiceApp is Displayed
     *
     * @param webDriver super(webDriver)
     */
    public AppsTocPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplaued(termsOfServiceApp, 10);
    }

    /**
     * AppsTocPage is Loaded when WebElement termsOfServiceApp is displayed
     *
     * @return termsOfServiceApp is displayed or not (tru or false)
     */
    public boolean isLoaded() {
        return waitUntilElementDisplaued(termsOfServiceApp, 15).isDisplayed();
    }


}


