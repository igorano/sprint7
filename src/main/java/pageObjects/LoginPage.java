package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "user_login_email")
    private WebElement loginField;
    @FindBy(how = How.ID, using = "user_login_continue")
    private WebElement submitBtn;


    public void populateLoginField(String value) {
        loginField.sendKeys(value);
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }
}
