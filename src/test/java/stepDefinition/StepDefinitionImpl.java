package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kerem.pageobjects.*;
import kerem.toren.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    //Background = BeforeTest
    @Given("I landed on E-Commerce page")
    public void I_landed_on_ECommerce_Page() throws IOException {

        landingPage = launchApp();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password){

        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName) throws InterruptedException {

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName){

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectTurkey();
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displaying_on_ConfirmationPage(String message){

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void message_is_displayed(String strArg1) throws Throwable{

        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        driver.close();
    }
}
