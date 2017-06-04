package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Created by UI дизайн on 27.05.2017.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath="//input[@type='email']")
    private WebElement emailField;
    @FindBy (xpath="//input[@type='password']")
    private WebElement passwordField;
    @FindBy (xpath="//*[@class='button' and text()='GO']")
    private WebElement goButton;
    @FindBy (xpath="//*[@class='invalid-credentials']")
    private WebElement invalidCredentialsErrorMsg;

    public LoginPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
        waitUntilElementDisplaued(goButton, 5);
    }

    public MainPage  LoginAs (String userEmail, String userPassword){
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        goButton.click();
        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new MainPage(webDriver);
    }
    public LoginPage  LoginAsReturtToLogin (String userEmail, String userPassword){
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        goButton.click();
        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isPageLoaded(){
        return emailField.isDisplayed();
    }

    public boolean  invalidCredentialsMsgDisplayed (){
        return invalidCredentialsErrorMsg.isDisplayed();
    }

    public  String getErrormsgText (){
        return invalidCredentialsErrorMsg.getText();
    }
 }
