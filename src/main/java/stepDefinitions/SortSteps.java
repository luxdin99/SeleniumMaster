package stepDefinitions;

import functionLibrary.CommonFunctions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Product;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SortSteps extends CommonFunctions {
    @When("I click sort dropdown")
    public void clickSortDropdown() {
        clickElement(driver, By.className("product_sort_container"));
    }

    @Then("I should see options {string},{string},{string},{string}")
    public void verifySortDropDownOptionsExists(String expOpt1, String expOpt2, String expOpt3, String expOpt4) {
        String expDropDownOptions[] = {expOpt1, expOpt2, expOpt3, expOpt4};
        List<WebElement> options = new Select(findElement(driver, By.className("product_sort_container"))).getOptions();
        List<String> optionTexts = new ArrayList<>();

        for (WebElement option : options) {
            optionTexts.add(option.getText());
        }
        //To confirm the number of items in drop down - array-uses length, list-uses size

        if (expDropDownOptions.length == optionTexts.size()) {
            for (String expOpt : expDropDownOptions) {
                Assert.assertTrue(optionTexts.contains(expOpt));
                //other way to confirm this is  Assert.assertEquals(expDropDownOptions.length,optionTexts.size());
            }

        }

    }

    @When("I choose option {string}")
    public void chooseSortOption(String option) {
        new Select(driver.findElement(By.className("product_sort_container"))).selectByVisibleText(option);
        //to do the above step using commonfunctions-do as below
        //selectDrpDownOptions(driver,By.className("product_sort_container"), option);

    }

    @Then("I expect products to be sorted in ascending order")
    public void verifyProductNameAtoZ() {
        List<WebElement> actualProducts = findElements(driver, By.xpath("//div[@data-test ='inventory-item-name']"));
        List<String> productNames = new ArrayList<>();
        for (WebElement actualProduct : actualProducts) {
            productNames.add(actualProduct.getText());
        }

        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames);

        Assert.assertTrue(productNames.equals(sortedProductNames));


    }

    @Then("I expect products to be sorted in descending order")
    public void verifyProductNameZtoA() {
        List<WebElement> actualProducts = findElements(driver, By.xpath("//div[@data-test ='inventory-item-name']"));
        List<String> productNames = new ArrayList<>();
        for (WebElement actualProduct : actualProducts) {
            productNames.add(actualProduct.getText());
        }

        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames, Collections.reverseOrder());

        Assert.assertTrue(productNames.equals(sortedProductNames));


    }

    @Then("I expect products to be sorted in price ascending order")
    public void verifyProductPriceLowToHigh() {
        List<WebElement> actualPriceElements = findElements(driver, By.xpath("//div[@data-test ='inventory-item-price']"));
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement actualPricesElement : actualPriceElements) {
            String priceAsText = actualPricesElement.getText();

            try {
                Double priceAsDigits = Double.parseDouble(priceAsText);
                actualPrices.add(priceAsDigits);
            } catch (NumberFormatException e) {


            }
        }
        List<Double> sortedActualPrices = new ArrayList<>(actualPrices);
        Collections.sort(sortedActualPrices);

        Assert.assertTrue(actualPrices.equals(sortedActualPrices));

    }

    @Then("I expect products to be sorted in price descending order")
    public void verifyProductPriceHighToLow() {
        List<WebElement> actualPriceElements = findElements(driver, By.xpath("//div[@data-test ='inventory-item-price']"));
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement actualPricesElement : actualPriceElements) {
            String priceAsText = actualPricesElement.getText();

            try {
                Double priceAsDigits = Double.parseDouble(priceAsText);
                actualPrices.add(priceAsDigits);
            } catch (NumberFormatException e) {


            }
        }
        List<Double> sortedActualPrices = new ArrayList<>(actualPrices);
        Collections.sort(sortedActualPrices, Collections.reverseOrder());

        Assert.assertTrue(actualPrices.equals(sortedActualPrices));
    }


}


