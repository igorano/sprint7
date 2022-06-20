package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import managers.PageObjectManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import pageObjects.LoginPage;

public class EmagSteps {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    PageObjectManager pageObjectManager;

    @Before
    public  void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Given("Login into emag")
    public void loginIntoEmag() {
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        homePage.navigateTo_HomePage();
    }
    @And("Login existing account successfully")
    public void loginExistingAccountSuccessfully() {
        homePage.clickMyAccount();
    }

    @And("Search for a product in the search field")
    public void searchForAProductInTheSearchField() throws InterruptedException {
        homePage.searchInSearchBar("пералня");
    }

    @And("Search in the category")
    public void searchInTheCategory() {
        homePage.searchIsCategory();
    }

    @When("Add a random product to the favorites")
    public void addARandomProductToTheFavorites() {
        homePage.searchInSearchBar("миялна");
        homePage.addToFavorites();
    }

    @Then("Checkout all Lego Harry Potter")
    public void checkoutAllLegoHarryPotter(){
        homePage.searchInConstructors();
    }
    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
