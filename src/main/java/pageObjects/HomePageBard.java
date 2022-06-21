package pageObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageBard {
    WebDriver driver;
    WebDriverWait wait;

    public HomePageBard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @FindBy(how = How.XPATH, using = "//a[text()='Каталог']")
    private WebElement catalogue;

    @FindBy(how = How.XPATH, using = "//a[text()='Роман']")
    private WebElement novel;

    @FindBy(how = How.XPATH, using = "(//h2)[1]")
    private WebElement firstBook;

    @FindBy(how = How.XPATH, using = "//input[@name='q']")
    private WebElement search;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement submit;

    @FindBy(how = How.XPATH, using = "//div[@class='purchase-price']/following-sibling::a")
    private WebElement purchaseBook;

    @FindBy(how = How.XPATH, using = "//a[@class='shopcart-info-title']")
    private WebElement shoppingCart;

    @FindBy(how = How.XPATH, using = "//td[@class='remove']/a")
    private WebElement removeBook;
    public void navigateTo_HomePage() {
        driver.get("https://bard.bg");
    }
    public void selectFistNovelBook() {
        catalogue.click();
        wait.until(ExpectedConditions.visibilityOf(novel));
        novel.click();
        firstBook.click();
    }

    public void searchBook(String book){
        search.click();
        search.sendKeys(book);
        submit.click();
    }

    public void purchaseBook(){
        purchaseBook.click();
    }

    public void clickShoppingCart(){
        shoppingCart.click();
    }
    public void removeBook(){
        removeBook.click();
    }
}