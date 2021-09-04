package ru.bulkashmak;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {
    @Test
    void test() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.regard.ru/");
    }
}