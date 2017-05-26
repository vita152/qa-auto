import jdk.management.resource.internal.inst.StaticInstrumentation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

/**
 * Created by Java Script on 20.05.2017.
 */
public class LoginTest {
    public static void main (String [] args) {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("denvert1@shotspotter.net");
        webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("Test123!");
        webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']")).click();
               try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Shotspotter", webDriver.getTitle());
        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@class='close-up footer-button']")).getText(), "CLOSE UP");
        webDriver.findElement(By.xpath("//*[@class='settings']"));

        webDriver.quit();
    }
}
