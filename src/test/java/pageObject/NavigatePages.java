package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NavigatePages extends PageObjetc {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_BID = "http://localhost:8080/leiloes/2";
    private static final String URL_AUCTION = "http://localhost:8080/leiloes";
    private static final String URL_NEW_AUCTION = "http://localhost:8080/leiloes/new";
    private static final String URL_EDIT = "http://localhost:8080/leiloes/1/form";

    public NavigatePages() throws InterruptedException {
        super(null);
        browser.get(URL_LOGIN);
    }

    public void fillFormLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public Boolean isPageAuction() {
        return browser.getCurrentUrl().equals(URL_AUCTION);
    }

    public Boolean isPageNewAuction() {
        return browser.getCurrentUrl().equals(URL_NEW_AUCTION);
    }

    public Boolean isPageLogin() {
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public boolean isPageEdit() {
        return browser.getCurrentUrl().equals(URL_EDIT);
    }

    public Boolean isPageLoginError() {
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }

    public Boolean isPageBid() {
        return browser.getCurrentUrl().equals(URL_BID);
    }

    public void buttonNewAuctionLink() {
        browser.findElement(By.id("novo_leilao_link")).click();
    }

    public void buttonReturn() {
        browser.findElement(By.id("voltar")).click();
    }

    public void buttonEdit() {
        browser.findElement(By.id("editar")).click();
    }

    public void buttonBid() {
        browser.findElement(By.id("lance")).click();
    }

    public void buttonHome() {
        browser.findElement(By.id("home")).click();
    }

    public void buttonGoOut() {
        browser.findElement(By.id("sair")).click();
    }

    public void buttonToEnter() {
        browser.findElement(By.id("entrar")).click();
    }

    public void buttonNewBid() {
        browser.findElement(By.id("valorLance")).sendKeys("100");
        browser.findElement(By.id("btnDarLance")).submit();
    }

    public AuctionPage submitLogin() throws InterruptedException { // Após efetuar login, por padrão é redirecionado a pagina de leilões, desta forma ja redirecionamos após login.
        browser.findElement(By.id("login-form")).submit();
        return new AuctionPage(browser);
    }

    public boolean isBidRegister(String value) {
        String userLogged = browser.findElement(By.id("usuario-logado")).getText();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        WebElement columnDate = browser.findElement(By.xpath(String.format("(//td[contains(text(),'%s')])[last()]", date)));
        WebElement columnUser = browser.findElement(By.xpath(String.format("(//td[contains(text(),'%s')])[last()]", userLogged)));
        WebElement columnValue = browser.findElement(By.xpath(String.format("(//td[contains(text(),'%s')])[last()]", value)));

        return columnDate.getText().contains(date)
                && columnUser.getText().contains(userLogged)
                && columnValue.getText().contains(value);
    }

    public String isUserLogged() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("");
        }
    }

    public boolean isUserLogout() {
        return browser.getPageSource().contains("Entrar");
    }
}
