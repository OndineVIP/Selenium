import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;


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
    void setupTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
               driver = new ChromeDriver(chromeOptions);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testDriver() {
        driver.get("http://localhost:9999");
        driver.findElement((By.cssSelector("[data-test-id=name] input"))).sendKeys("Ирина Хакамада");
        driver.findElement((By.cssSelector("[data-test-id=phone] input"))).sendKeys("+79522345678");
        driver.findElement((By.cssSelector("[data-test-id=agreement]"))).click();
        driver.findElement(By.cssSelector("button.button.button_view_extra.button_size_m.button_theme_alfa-on-white")).click();
        String text = driver.findElement((By.cssSelector("[data-test-id=order-success]"))).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }


    }

