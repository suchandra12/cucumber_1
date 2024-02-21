package com.crypto88;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Indexfooter {
    private WebDriver driver;
    private String baseUrl = "http://iqhqsolutionsdev.ddns.net:8082/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Run in headless mode with new value
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("I opened the URL and scrolled to the end")
    public void i_opened_the_url_and_scrolled_to_the_end() {
        driver.get(baseUrl);
        // Scroll down to the end of the page
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        // Add a small delay to ensure content loads completely
        try {
            Thread.sleep(2000); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I print all logos present in the bottom footer")
    public void i_print_all_logos_present_in_the_bottom_footer() {
        // Find the container element that holds the logos
        WebElement footerElement = driver.findElement(By.className("bottom-footer"));

        // Find all logo elements within the container
        List<WebElement> logoElements = footerElement.findElements(By.cssSelector(".social-icon i"));

        // Print the text content of each logo
        for (WebElement logoElement : logoElements) {
            System.out.println("Logo: " + logoElement.getAttribute("class"));
        }
    }


    @Then("I verify all logos are present in the bottom footer")
    public void i_verify_all_logos_are_present_in_the_bottom_footer() {
        // Find the container element that holds the logos
        WebElement footerElement = driver.findElement(By.className("bottom-footer"));
        // Find all logo elements within the container
        List<WebElement> logoElements = footerElement.findElements(By.cssSelector(".social-icon i"));
        // Verify that at least one logo is present
        assertTrue("No logos found in the bottom footer", logoElements.size() > 0);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
