package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageObjetc {

    protected WebDriver browser;

    public PageObjetc(WebDriver browser) throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // Driver para conexão com o navegador

        if (browser == null)
            this.browser = new ChromeDriver(); // Instancia do Driver do Chrome
        else
            this.browser = browser;

//        this.browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
    }

    public void closePage() {
        browser.quit();
    }

}
