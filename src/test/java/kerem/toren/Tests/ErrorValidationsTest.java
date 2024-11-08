package kerem.toren.Tests;

import kerem.toren.TestComponents.BaseTest;
import kerem.pageobjects.CartPage;
import kerem.pageobjects.ProductCatalogue;
import kerem.toren.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {

        landingPage.loginApplication("keremtest@test.com", "Keremtest1");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("keremtest2@test2.com", "Keremtest1234");

        //alt satırdaki object kodunu kaldırdık çünkü bu objecti yukarıdaki loginApplication methodunun içinde oluşturduk
        //ProductCatalogue productCatalogue = new ProductCatalogue(driver);

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");

        //Error validation yaptığımız için assert.false kullandık
        Assert.assertFalse(match);

    }
}
