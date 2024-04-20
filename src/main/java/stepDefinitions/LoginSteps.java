package stepDefinitions;

import functionLibrary.CommonFunctions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class LoginSteps extends CommonFunctions {

    @Then("I should land on products page")
    public void landOnProductsPage() {
        Assert.assertTrue(findElement(driver, By.className("title")).isDisplayed());
    }

    @When("I do Login with invalid username {string} and password {string}")
    public void invalidLogin(String username, String password) {
        type(driver, By.id("user-name"), username);
        type(driver, By.id("password"), password);
        clickElement(driver, By.id("login-button"));

    }

    @Then("I should get error message {string}")
    public void invalidLoginErrorMessage(String errorMessage) {

        assertEquals(driver, By.xpath("//h3[@data-test='error']"), errorMessage);
    }

    @When("I do Login with no username and password entered")
    public void doNoDataLogin() {
        driver.findElement(By.id("login-button")).click();
        //you can also use common functions-clickElement
    }


}

