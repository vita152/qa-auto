import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static java.lang.Thread.sleep;

/**
 * Created by Вита on 23.05.2017.
 */
public class LoginTestFalse {
    WebDriver webDriver;

    public static void main (String [] args) {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("test@shotspotter.net");
        webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("Test");
        webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']")).click();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       //WebElement source = webDriver.findElement(By.xpath("//*[@class='invalid-credentials']"));
      //  Assert.assertEquals("The provided credentials are not corect.", source.getText());

       //try {
      //     Assert.assertFalse(webDriver.findElement(By.xpath("//*[@class='invalid-credentials']")).isDisplayed(), "The provided credentials are nt corect.");
      // }catch (Throwable t) {
      //      System.out.println("Error");
      //  }

          try {
            Assert.assertEquals(webDriver.findElement(By.xpath("//*[@class='invalid-credentials']")).getText(), "The provided credentials are not correct.");}
          catch (Throwable t)
          {
         System.out.println("Error");
          }

        Assert.assertEquals("Shotspotter - Login", webDriver.getTitle());

        webDriver.quit();
    }
}
