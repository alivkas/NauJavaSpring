package ru.matveyelovskikh.naujavaspring.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

/**
 * Тесты авторизации на страницах
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthUITest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Тестировать UI авторизацию и выход
     */
    @Test
    public void testLoginLogout() {
        driver.get("http://localhost:" + port + "/login");

        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assertions.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Welcome"));

        driver.get("http://localhost:" + port + "/logout");

        Assertions.assertEquals("http://localhost:" + port + "/login?logout", driver.getCurrentUrl());
    }
}
