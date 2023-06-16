package testSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Teste {

    @Test
    void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://google.com");
        webDriver.findElement(By.name("q")).sendKeys("StackOverflow");
        webDriver.findElement(By.name("q")).submit();

//        webDriver.quit();
    }

}