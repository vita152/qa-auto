package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;


/**
 * First page have: login, password, go, ErrorMsg
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@class='button' and text()='GO']")
    private WebElement goButton;
    /**
     * WebElement ErrorMessage when to type wrong email or password
     */
    @FindBy(xpath = "//*[@class='invalid-credentials']")
    private WebElement invalidCredentialsErrorMsg;

    /**
     * Constructor LoginPage have:
     *  super(webDriver),
     *  init Elements,
     *  goButton is Displayed.
     *
     * @param webDriver super(webDriver)
     */
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementDisplaued(goButton, 5);
    }

    /**
     *Determine which one Page Object is it (LoginPage or MainPage)
     *
     * @param userEmail type login
     * @param userPassword type password
     * @param <T> unknown Page Object type (LoginPage/MainPage)
     * @return PageObject (LoginPage or MainPage)
     */
    public <T> T login(String userEmail, String userPassword) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        goButton.click();
        if (isElementDisplayed(goButton)) {
            return (T) this;
        } else {
            return (T) PageFactory.initElements(webDriver, MainPage.class);
        }
    }

    /**
     * LoginPage is Loaded when WebElement emailField is displayed
     *
     * @return emailField is displayed or not (tru or false)
     */
    public boolean isPageLoaded() {
        return emailField.isDisplayed();
    }

    /**
     * Error Message is displayed when to write wrong email or password
     *
     * @return invalidCredentialsErrorMsg is displayed or not (tru or false)
     */
    public boolean invalidCredentialsMsgDisplayed() {
        return invalidCredentialsErrorMsg.isDisplayed();
    }

    /**
     *Common method to get Error Text when write wrong email or password
     *
     * @return Error Text when write wrong email or password
     */
    public String getErrormsgText() {
        return invalidCredentialsErrorMsg.getText();
    }
}
