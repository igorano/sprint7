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
    @FindBy(how = How.ID, using = "my_account")
    private WebElement myAccount;
    @FindBy(how = How.XPATH, using = "//a[@href ='/user/myaccount']")
    private WebElement account;


    public void clickMyAccount() {
       myAccount.click();
    }

    public void clickAccount() {
        account.click();
    }

    public void navigateTo_HomePage() {
        driver.get("https://www.emag.bg/homepage");
    }

}