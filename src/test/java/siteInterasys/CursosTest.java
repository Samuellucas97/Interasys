package siteInterasys;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class CursosTest {
    private String baseUrl;
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        baseUrl = "https://iterasys.com.br";

        System.setProperty("webdriver.chrome.driver",
                "/home/samuel/Downloads/siteInterasys/drivers/chrome/version93/chromedriver"
        );

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void searchCourse() {
        // Home Page
        driver.get(baseUrl);
        final WebElement searchtext = driver.findElement(By.id("searchtext"));
        searchtext.clear();
        searchtext.sendKeys(Keys.chord("Mantis"));

        final WebElement btn_form_search = driver.findElement(By.id("btn_form_search"));
        btn_form_search.click();

        // Home Page - Course selected
        final WebElement spanComprar = driver.findElement(By.cssSelector("span.comprar"));
        spanComprar.click();

        // Shopping Cart Page  (Checking results)
        final WebElement itemTitleElement = driver.findElement(By.cssSelector("span.item-title"));
        final String itemTitle = itemTitleElement.getText();
        final WebElement itemPriceElement = driver.findElement(By.cssSelector("span.new-price"));
        final String itemPrice = itemPriceElement.getText();

        String expectedItemTitle = "Mantis";
        String expectedItemPrice = "R$ 49,99";

        assertThat(itemTitle).isEqualTo(expectedItemTitle);
        assertThat(itemPrice).isEqualTo(expectedItemPrice);
    }
}
