package kerem.pageobjects;

import kerem.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        //initialization
        super(driver);
        this.driver = driver;
        //alt satırdaki kod @FindBy ile belirttiğimiz variable ları çalıştırır
        //Go and initialize all the elements
        //bu kodu constructor a yazmamızın sebebi bu class a girildiğinde ilk çalışan şey constructordur
        PageFactory.initElements(driver, this);
    }

    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

    //PageFactory
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    //By kullanmadık çünkü locator ü driver ile verilmiş bu sayede pagefactory kullanabildik ama toast message için pagefactory kullanamadık çünkü toastmessageın locator u By ile verilmiş
    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        if (prod == null) {
            System.out.println("Ürün bulunamadı: " + productName);
        }
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}
