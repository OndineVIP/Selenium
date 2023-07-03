
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BankCardTest {
    private WebDriver driver;
    static ChromeOptions options = new ChromeOptions();

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "win/chromedriver.exe");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
    }

    @BeforeEach
    void setup() {
               driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }

    @Test
    void testDriver() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input]")).sendKeys("Саня Белый");
        driver.findElement(By.cssSelector("[data-test-id=phone] input]")).sendKeys("+79777888999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[data-test-id=submit]")).click();
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.cssSelector("alert-sucsses")).getText();
        String expected = "Ваша заявка успешно отправлена!Наш менеджер свяжется с вами в ближайшее время";
        String actual = driver.findElement(By.cssSelector("order-sucsses")).getText().trim();
        assertEquals(expected, actual);

    }

}




