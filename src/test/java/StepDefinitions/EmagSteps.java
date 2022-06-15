package StepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import managers.PageObjectManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

public class EmagSteps {
    WebDriver driver;
    HomePage homePage;
    PageObjectManager pageObjectManager;
    @Given("Login into emag")
    public void loginIntoEmag() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        homePage.navigateTo_HomePage();
    }
    @And("Login existing account successfully")
    public void loginExistingAccountSuccessfully() {
        homePage.clickMyAccount();
        homePage.clickAccount();
    }

    @And("Search for a product in the search field")
    public void searchForAProductInTheSearchField() {
    }

    @And("Search in the category")
    public void searchInTheCategory() {
    }

    @When("Add a random product to the favorites")
    public void addARandomProductToTheFavorites() {
    }

    @Then("Checkout all Lego Harry Potter")
    public void checkoutAllLegoHarryPotter() {
    }


}
