package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

/**
 * Created by UI дизайн on 27.05.2017.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@class='button' and text()='GO']")
    private WebElement goButton;
    @FindBy(xpath = "//*[@class='invalid-credentials']")
    private WebElement invalidCredentialsErrorMsg;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
        waitUntilElementDisplaued(goButton, 5);
    }

    public <T> T login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        goButton.click();
        if (isElementExist(goButton)) {
            return (T) PageFactory.initElements(webDriver, LoginPage.class);
        } else {
            return (T) PageFactory.initElements(webDriver, MainPage.class);
        }
    }


    public boolean isPageLoaded() {
        return emailField.isDisplayed();
    }

    public boolean invalidCredentialsMsgDisplayed() {
        return invalidCredentialsErrorMsg.isDisplayed();
    }

    public String getErrormsgText() {
        return invalidCredentialsErrorMsg.getText();
    }
}
