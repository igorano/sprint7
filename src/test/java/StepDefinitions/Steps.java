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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePageBard;
import pageObjects.HomePageEmag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class Steps {
    private static final String BASE_URL = "https://dummy.restapiexample.com/api/v1/";
    private static final String SOAP_URL = "http://www.dneonline.com/calculator.asmx?WSDL";
    WebDriver driver;
    HomePageBard homePageBard;
    HomePageEmag homePageEmag;
    WebDriverWait wait;
    PageObjectManager pageObjectManager;

    private static Response response;
    private static ResponseBody body;

    File soapRequest;
    CloseableHttpClient client;
    HttpPost request;

    @Before
    public void before_all() {
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
    public void tearDown(){
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

    @When("Get a particular employee")
    public void getAParticularEmployee() {

        RestAssured.baseURI = BASE_URL + "employee/1";
        RequestSpecification request = RestAssured.given();
        response = request.get();
    }

    @When("get non existing employee")
    public void getNonExistingEmployee() {
        RestAssured.baseURI = BASE_URL + "employee/-1";
        RequestSpecification request = RestAssured.given();
        response = request.get();
    }

    @When("creating already existing employee")
    public void creatingAlreadyExistingEmployee() {
        RestAssured.baseURI = BASE_URL + "create/1";
        RequestSpecification request = RestAssured.given();
        response = request.get();
    }

    @Given("go to url")
    public void goToUrl() throws IOException {
        soapRequest = new File("soap.xml");
        client = HttpClients.createDefault();
    }

    @When("create request")
    public void createRequest() throws FileNotFoundException {
        request = new HttpPost(SOAP_URL);
        request.addHeader("Content-Type", "text/xml");
        request.setEntity(new InputStreamEntity(new FileInputStream(soapRequest)));
    }

    @Then("check response")
    public void checkResponse() throws IOException {
        CloseableHttpResponse resp = client.execute(request);
        int statusCode = resp.getStatusLine().getStatusCode();
        var response = EntityUtils.toString(resp.getEntity());
        Assert.assertTrue(response.contains("2"));
        Assert.assertEquals(200, statusCode);
    }
}
