package functionLibrary;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class CommonFunctions {
    public static WebDriver driver;

    public void openBrowser()
    {
        driver = new EdgeDriver();
    }
    public void closeBrowser()
    {
     driver.quit();
    }

    public void assertNoSuchElement(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            Assert.fail();
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    //when network is slow ,and you want the test script to wait and synchronise test execution
    public void waitForTime (WebDriver driver, Duration duration)
    {
        driver.manage().timeouts().implicitlyWait(duration);

    }
    //By locator(by)
    public WebElement findElement(WebDriver driver,By by)
    {
        return driver.findElement(by);

      }
      public void clickElement(WebDriver driver,By by)
      {
        findElement(driver,by).click();
      }
      public void type(WebDriver driver,By by,String text)
      {
          findElement(driver,by).sendKeys(text);
      }
      public void goToUrl(WebDriver driver,String url)
      {
          driver.get(url);
      }
      public void assertTrue(WebDriver driver,By by)
      {
          Assert.assertTrue(findElement(driver,by).isDisplayed());


      }
      public void assertEquals(WebDriver driver,By by,String expectedText)
      {
          Assert.assertEquals(expectedText, findElement(driver,by).getText());
      }

    public List<WebElement> findElements(WebDriver driver, By by)
    {
        return driver.findElements(by);

    }
    //To select an option from dropdown menu
    public void selectDrpDownOptions(WebDriver driver,By by,String option)
    {
        new Select(findElement(driver, by)).selectByVisibleText(option);
    }
}
