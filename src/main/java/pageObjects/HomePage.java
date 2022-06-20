package pageObjects;
import io.netty.util.Timeout;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.Visibility;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.ID, using = "my_account")
    private WebElement myAccount;
    @FindBy(how = How.ID, using = "searchboxTrigger")
    private WebElement searchboxTrigger;

    @FindBy(how = How.XPATH, using = "//a[@href ='/user/myaccount']")
    private WebElement account;

    @FindBy(how = How.XPATH, using = "//span[text() ='Големи електроуреди']")
    private WebElement categoryBE;

    @FindBy(how = How.XPATH, using = "//div[@class ='navbar-aux-content__departments']")
    private WebElement categories;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Фризери')]")
    private WebElement categoriesBERefrigerators;

    @FindBy(how = How.XPATH, using = "(//button[@class='add-to-favorites btn'])[1]")
    private WebElement addFirstProductToFavorites;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'add-to-favorites-active')]")
    private WebElement addedToFavorites;

    @FindBy(how = How.XPATH, using = "//span[text() ='Играчки & Детски артикули']")
    private WebElement toysKids;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Конструктори')]")
    private WebElement constructors;

    public void clickMyAccount() {
       myAccount.click();
    }

    public void clickAccount() {
        account.click();
    }

    public void navigateTo_HomePage() {
        driver.get("https://www.emag.bg/homepage");
    }

    public void searchInSearchBar(String product){
        searchboxTrigger.click();
        searchboxTrigger.sendKeys(product);
        searchboxTrigger.sendKeys(Keys.RETURN);
    }

    public void searchIsCategory(){
        categories.click();
        categoryBE.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(categoriesBERefrigerators));
        categoriesBERefrigerators.click();
    }

    public void addToFavorites(){
        addFirstProductToFavorites.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(addedToFavorites));
    }

    public void searchInConstructors(){
        driver.navigate().to("https://www.emag.bg/search/konstruktori/harry+potter");
    }
}