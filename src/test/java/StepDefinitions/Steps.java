package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import managers.PageObjectManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePageBard;
import pageObjects.HomePageEmag;

import java.time.Duration;

public class Steps {
    private static final String BASE_URL = "https://dummy.restapiexample.com/api/v1/";
    WebDriver driver;
    HomePageBard homePageBard;
    HomePageEmag homePageEmag;
    WebDriverWait wait;
    PageObjectManager pageObjectManager;

    private static Response response;
    private static ResponseBody body;

    @Before
    public  void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageObjectManager = new PageObjectManager(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }
    @Given("Choose a product from category")
    public void chooseAProductFromCategory() {
        homePageBard = pageObjectManager.getBardHomePage();
        homePageBard.navigateTo_HomePage();
        homePageBard.selectFistNovelBook();

    }

    @When("Search for a product")
    public void searchForAProduct() {
        homePageBard.searchBook("къща");
    }

    @And("Add a book to the basket")
    public void addABookToTheBasket() {
        homePageBard.purchaseBook();
    }

    @Then("Edit the basket")
    public void editTheBasket() throws InterruptedException {
        homePageBard.clickShoppingCart();
        homePageBard.removeBook();
        Thread.sleep(10000);
    }
    @Given("Login into emag")
    public void loginIntoEmag() {
        homePageEmag = pageObjectManager.getEmagHomePage();
        homePageEmag.navigateTo_HomePage();
    }
    @And("Login existing account successfully")
    public void loginExistingAccountSuccessfully() {
        homePageEmag.clickMyAccount();
    }

    @And("Search for a product in the search field")
    public void searchForAProductInTheSearchField() throws InterruptedException {
        homePageEmag.searchInSearchBar("пералня");
    }

    @And("Search in the category")
    public void searchInTheCategory() {
        homePageEmag.searchIsCategory();
    }

    @When("Add a random product to the favorites")
    public void addARandomProductToTheFavorites() {
        homePageEmag.searchInSearchBar("миялна");
        homePageEmag.addToFavorites();
    }

    @Then("Checkout all Lego Harry Potter")
    public void checkoutAllLegoHarryPotter(){
        homePageEmag.searchInConstructors();
    }
    @After()
    public void quit(){
        driver.quit();
    }

    @Given("go to base url")
    public void goToBaseUrl() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("create post request")
    public void createPostRequest() {
        RestAssured.baseURI = BASE_URL + "create";
        RequestSpecification request = RestAssured.given();

        response = request.post();
    }

    @Then("view the entry in DB")
    public void viewTheEntryInDB() {
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains("Successfully! Record has been added."));
    }

    @When("create get request")
    public void createGetRequest() {
        RestAssured.baseURI = BASE_URL + "employee";
        RequestSpecification request = RestAssured.given();
        response = request.get();
    }

    @Then("retrieve information from the DB")
    public void retrieveInformationFromTheDB() {
        String body = response.getBody().asString();
        System.out.println(body);
    }

    @Then("edit an entry in the DB")
    public void editAnEntryInTheDB() {
    }

    @When("create delete request")
    public void createDeleteRequest() {
        RestAssured.baseURI = BASE_URL + "delete/2";
        RequestSpecification request = RestAssured.given();
        response = request.delete();

    }

    @Then("delete an entry")
    public void deleteAnEntry() {
        String body = response.getBody().asString();
        System.out.println(body);
        Assert.assertTrue(body.contains("Record has been deleted"));
    }
}
