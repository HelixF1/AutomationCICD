package kerem.toren.Tests;

import kerem.toren.TestComponents.BaseTest;
import kerem.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        //alt satırdaki object kodunu kaldırdık çünkü bu objecti yukarıdaki loginApplication methodunun içinde oluşturduk
        //ProductCatalogue productCatalogue = new ProductCatalogue(driver);

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectTurkey();
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("thankyou for the order."));

    }

    //submitOrder testi çalıştıktan sonra bu test çalışacak
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(){

        ProductCatalogue productCatalogue = landingPage.loginApplication("keremtest@test.com", "Keremtest123");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }


    //Extent reports

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//kerem//toren//data//PurchaseOrder.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}


//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("email", "keremtest@test.com");
//        map.put("password", "Keremtest123");
//        map.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("email", "keremtest2@test2.com");
//        map1.put("password", "Keremtest1234");
//        map1.put("product", "ADIDAS ORIGINAL");