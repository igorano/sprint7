package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePageBard;
import pageObjects.HomePageEmag;

public class PageObjectManager {
    private final WebDriver driver;
    private HomePageEmag homePageEmag;
    private HomePageBard homePageBard;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }
    public HomePageEmag getEmagHomePage(){
        return (homePageEmag == null) ? homePageEmag = new HomePageEmag(driver) : homePageEmag;
    }
    public HomePageBard getBardHomePage(){
        return (homePageBard == null) ? homePageBard = new HomePageBard(driver) : homePageBard;
    }
}