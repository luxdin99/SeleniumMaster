package stepDefinitions;

import functionLibrary.CommonFunctions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.ArrayList;
import java.util.List;

public class BagSteps extends CommonFunctions {


    @Given("I am on homepage {string}")
    public void openHomePage(String url) {
        //For the below in common functions -do =goToUrl(driver,url)
        //for assert method in common functions -you can say -assertTrue(driver,By.className("login_logp"));
        driver.get(url);
        Assert.assertTrue(driver.findElement(By.className("login_logo")).isDisplayed());
    }

    @When("I do Login with username {string} and password {string}")
    public void doLogin(String username, String password) {
        //If typing username instead of below method -you can do
        //type(driver,By.id ("user-name"),username);
        //for click element-clickElement(driver,By.id ("login-button"));
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }

    @When("I choose a product {string}")
    public void chooseProduct(String productName) {
        driver.findElement(By.xpath("//div[@data-test = 'inventory-item-name' and text() = '" + productName + "']")).click();
        Assert.assertTrue(driver.findElement(By.name("back-to-products")).isDisplayed());
    }
//To use assert equals in common functions -say-assertEquals(driver,By.className("shopping-cart_badge"),badgevalue);
    //To use add to cart method in common functions - say clickElement(driver,by.id("add-to-cart"));
    @When("I click Add to cart button with badge {string}")
    public void addToCart(String badgeValue) {
        driver.findElement(By.id("add-to-cart")).click();
        Assert.assertEquals(badgeValue, driver.findElement(By.className("shopping_cart_badge")).getText());
    }

    @When("I click bag icon")
    public void clickBagIcon() {
        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }

    @Then("I should see product {string} in the bag")
    public void verifyProductInBag(String expectedProductInBag) {
        Assert.assertEquals(expectedProductInBag, driver.findElement(By.className("inventory_item_name")).getText());
    }

    //If you want to do below method using common functions -do this
    //clickElement(driver,By.xpath("//button[text()='Remove']"));

    @When("I click remove button in the bag")
    public void clickRemoveButton() {
        driver.findElement(By.xpath("//button[text()='Remove']")).click();
    }


    @Then("the product {string} should be removed")
    public void verifyProductRemovedFromBag(String expectedProductToBeRemoved) {
        assertNoSuchElement(driver, By.className("inventory_item_name"));
    }

    @When("I click back button")
    public void clickBackButton() {
        driver.findElement(By.id("back-to-products")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }

    @Then("I should see products {string} and {string} in the bag")
    public void verifyMultipleProductsInBag(String expectedProduct1InBag, String expectedProduct2InBag) {
        //Create an array of expected products
        String expectedProducts[] = {expectedProduct1InBag, expectedProduct2InBag};

        //Create list of actual product elements
        List<WebElement> actualProductsInBag = driver.findElements(By.xpath("//div[@data-test = 'inventory-item-name']"));

        //create an empty list to hols product names
        List<String> actualProductNames = new ArrayList<>();

        //iterate through actual product element and get name from each and add it to empty list
        for (WebElement actualProduct : actualProductsInBag) {
            actualProductNames.add(actualProduct.getText());
        }
        //iterate through each expected products array and check that exists with in list of actualProductNames
        for (String expectedProduct : expectedProducts) {
            Assert.assertTrue(actualProductNames.contains(expectedProduct));

        }
    }
}


