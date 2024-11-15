package kerem.toren.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import kerem.pageobjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        //new comments are added | 10 November 21:50
        String productName = "ZARA COAT 3";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client");

        LandingPage landingPage = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("keremtest@test.com");
        driver.findElement(By.id("userPassword")).sendKeys("Keremtest123");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink *= 'cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartProducts.stream().anyMatch(cartProduct->
                cartProduct.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        driver.findElement(By.cssSelector("[placeholder = 'Select Country']")).sendKeys("tu");
        List<WebElement> options = driver.findElements(By.cssSelector(".ta-item"));
        options.stream().filter(option->
                option.getText().equalsIgnoreCase("Turkey")).findFirst().ifPresent(WebElement::click);

        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("thankyou for the order."));

    }
}
