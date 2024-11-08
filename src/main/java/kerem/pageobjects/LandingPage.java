package kerem.pageobjects;

import kerem.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        //alt satırdaki kod @FindBy ile belirttiğimiz variable ları çalıştırır
        //Go and initialize all the elements
        //bu kodu constructor a yazmamızın sebebi bu class a girildiğinde ilk çalışan şey constructordur
        PageFactory.initElements(driver, this);
    }

    //WebElement userMail = driver.findElement(By.id("userEmail"));

    //PageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class *= 'flyInOut']")
    WebElement errorMessage;

    By waitErrorMessage = By.cssSelector("[class *= 'flyInOut']");

    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage() {
        waitForElementToAppear(waitErrorMessage);
        return errorMessage.getText();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
