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
public class LoginPage {

    private WebDriver webDriver;
    @FindBy(xpath="//input[@type='email']")
    private WebElement emailField;
    @FindBy (xpath="//input[@type='password']")
    private WebElement passwordField;
    @FindBy (xpath="//*[@class='button' and text()='GO']")
    private WebElement goButton;
    @FindBy (xpath="//*[@class='links']")
    private WebElement linksField;
    @FindBy (xpath="//*[@class='invalid-credentials']")
    private WebElement invalidCredential;

  //  private void InitLoginPageWebElement(){
      // emailField = webDriver.findElement(By.xpath("//input[@type='email']"));
       //passwordField = webDriver.findElement(By.xpath("//input[@type='password']"));
      // goButton = webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']"));
     //  linksField = webDriver.findElement(By.xpath("//*[@class='links']"));
   // }

    public LoginPage(final WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     //  InitLoginPageWebElement();
    }

    public void  LoginAs (String userEmail, String userPassword){
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        goButton.click();
        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // return new MainPage(webDriver);
    }

    public boolean isPageLoaded(){
        return linksField.isDisplayed();
    }

    public WebElement f (){
      //invalidCredential = webDriver.findElement(By.xpath("//*[@class='invalid-credentials']"));
      return invalidCredential;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setEmailField(WebElement emailField) {
        this.emailField = emailField;
    }

    public void setPasswordField(WebElement passwordField) {
        this.passwordField = passwordField;
    }

    public void setGoButton(WebElement goButton) {
        this.goButton = goButton;
    }

    public void setLinksField(WebElement linksField) {
        this.linksField = linksField;
    }

    public void setInvalidCredential(WebElement invalidCredential) {
        this.invalidCredential = invalidCredential;
    }
}
