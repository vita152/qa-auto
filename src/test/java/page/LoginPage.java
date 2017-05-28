package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.LoginTest;

/**
 * Created by UI дизайн on 27.05.2017.
 */
public class LoginPage {

   // public static WebDriver webDriver;

    static WebDriver webDriver = LoginTest.webDriver;
    public  static WebElement emailField = webDriver.findElement(By.xpath("//input[@type='email']"));
    public  static WebElement passwordField = webDriver.findElement(By.xpath("//input[@type='password']"));
    public  static WebElement goButton = webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']"));


    public  static void LoginAs (String a, String b){

       System.out.println(a);
       System.out.println(b);

        LoginPage.emailField.sendKeys(a);
        LoginPage.passwordField.sendKeys(b);

        goButton.click();
    }

}
