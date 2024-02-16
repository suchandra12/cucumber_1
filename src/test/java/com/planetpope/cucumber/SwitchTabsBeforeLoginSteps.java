package com.planetpope.cucumber;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
 
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;
 

public class SwitchTabsBeforeLoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private String expectedLoginUrl = "http://iqhqsolutionsdev.ddns.net:8082/#/login";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on the application's main page")
    public void user_is_on_the_applications_main_page() {
        driver.get("http://iqhqsolutionsdev.ddns.net:8082/"); // Navigate to the main page
    }

    @When("User switches between different tabs")
    public void user_switches_between_different_tabs(DataTable tabNames) {
        for (String tabName : tabNames.asList()) {
            WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(tabName)));
            tabElement.click();

            // Wait for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check if the current URL matches the expected login URL
            String currentUrl = driver.getCurrentUrl();
            assertEquals(expectedLoginUrl, currentUrl);

            // Come back to the main page
            driver.navigate().back();
        }
    }
}
