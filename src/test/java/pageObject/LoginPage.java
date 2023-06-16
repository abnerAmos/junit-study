package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObjetc {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_BID = "http://localhost:8080/login/2";

    public LoginPage() throws InterruptedException {
        super(null);
        browser.get(URL_LOGIN);
    }

    public String currentUrl(String url) {
        if (url == "login")
            return URL_LOGIN;
        else
            return URL_BID;
    }

    public void fillFormLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public AuctionPage submitLogin() throws InterruptedException { // Após efetuar login, por padrão é redirecionado a pagina de leilões, desta forma ja redirecionamos após login.
        browser.findElement(By.id("login-form")).submit();
        return new AuctionPage(browser);
    }

    public Boolean isPageLogin() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public Boolean isPageLoginError() {
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }

    public String getUsernameLogged() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Boolean isContainsPageSource(String phrase) {
        return browser.getPageSource().contains(phrase);
    }

}
