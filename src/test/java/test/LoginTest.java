package test;

import jdk.management.resource.internal.inst.StaticInstrumentation;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;

import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

/**
 * Created by Java Script on 20.05.2017.
 */
public class LoginTest {

    public static WebDriver webDriver;

    @BeforeMethod    //BeforeTest  не отрабатывает, по этому BeforeMethod
public void beforeClass()
{
 webDriver = new FirefoxDriver();
    webDriver.navigate().to("https://alerts.shotspotter.biz/");
    try {
        sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    @AfterMethod
public  void afterMethod()
{
    webDriver.quit();
}
    @Test
    public void testLoginPositive() {

        Assert.assertEquals(webDriver.getTitle(), "Shotspotter - Login",
                            "Main page title is wrong");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://alerts.shotspotter.biz/",
                            "Wrong URL on Login page");

        LoginPage.LoginAs("denvert1@shotspotter.net","Test123!");

               try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(webDriver.getTitle(), "Shotspotter",
                            "Main page title is wrong");
        Assert.assertTrue(webDriver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main"),
                            "Wrong URL on Main page");
        WebElement settingIcon = webDriver.findElement(By.className("settings"));
        Assert.assertTrue(settingIcon.isDisplayed(),
                            "Setting Icon is not displayed");
    }
}
