package com.planetpope.cucumber;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SwitchTabsSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Run in headless mode with new value
        driver = new ChromeDriver(options);

        // Maximize the browser window
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open the Application_URL in Chrome")
    public void openApplicationURL() {
        driver.get("http://iqhqsolutionsdev.ddns.net:8082/");
    }

    @And("I click on login_tab")
    public void clickOnLoginTab() {
        WebElement loginTab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.login-button")));
        loginTab.click();
    }

    @And("I enter a valid email_address {string}")
    public void enterValidEmail(String email) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);
    }

    @And("I enter a valid_password {string}")
    public void enterValidPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
    }

    @When("I click on Login_button")
    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        loginButton.click();
    }

    @Then("I wait {int}_seconds")
    public void i_wait_for_seconds(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("User switches between_different_tabs")
    public void user_switches_between_different_tabs(DataTable tabNames) {
        List<Map<String, String>> tabs = tabNames.asMaps();
        for (Map<String, String> tab : tabs) {
            String tabName = tab.get("Tab Name");
            String expectedUrl = tab.get("Expected URL");
            WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(tabName)));
            tabElement.click();
            String currentUrl = driver.getCurrentUrl();
            assertEquals(expectedUrl, currentUrl);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Then("User closes the application")
    public void user_closes_the_application() {
        if (driver != null) {
            driver.quit();
        }
    }
}
