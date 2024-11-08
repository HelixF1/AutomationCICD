package kerem.pageobjects;

import kerem.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder = 'Select Country']")
    private WebElement country;
    @FindBy(css = ".action__submit")
    private WebElement submitButton;
    @FindBy(css = ".ta-item")
    private List<WebElement> options;

    public void selectTurkey() {
        country.click();
        country.sendKeys("tu");
        options.stream().filter(option->
                option.getText().equalsIgnoreCase("Turkey")).findFirst().ifPresent(WebElement::click);
    }

    public ConfirmationPage submitOrder() {
        submitButton.click();
        return new ConfirmationPage(driver);
    }


}
