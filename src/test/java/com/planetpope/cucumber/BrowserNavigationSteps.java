package com.planetpope.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class BrowserNavigationSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I open Chrome browser")
    public void iOpenChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @When("I navigate to {string}")
    public void navigateToURL(String url) {
        driver.get(url);
    }

    @Then("the current URL should be {string}")
    public void verifyBrowserURL(String expectedURL) {
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals("URL does not match", expectedURL, actualURL);
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
