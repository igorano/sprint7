package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.CSS, using = "button.single_add_to_cart_button")
    private WebElement btn_AddToCart;

    public void perform_Search(String search) {
        driver.navigate().to("https://shop.demoqa.com/?s=" + search + "&post_type=product");
    }

    public void navigateTo_HomePage() {
        driver.get("https://www.shop.demoqa.com");
    }

}