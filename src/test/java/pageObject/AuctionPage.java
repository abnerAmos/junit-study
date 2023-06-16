package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuctionPage extends PageObjetc {

    private static final String URL_AUCTION = "http://localhost:8080/leiloes";
    private static final String URL_NEW_AUCTION = "http://localhost:8080/leiloes/new";

    public AuctionPage(WebDriver browser) throws InterruptedException {
        super(browser);
    }

    public Boolean isContainsPageSource(String phrase) {
        return browser.getPageSource().contains(phrase);
    }

    public Boolean isPageAuction() {
        return browser.getCurrentUrl().equals(URL_AUCTION);
    }

    public Boolean isPageNewAuction() {
        return browser.getCurrentUrl().equals(URL_NEW_AUCTION);
    }

    public void newAuctionLink() {
        browser.findElement(By.id("novo_leilao_link")).click();
    }

    public void saveAuction() {
        browser.findElement(By.id("button-submit")).submit();
    }

    public void fillFormAuction(String name, String value, String date) {
        browser.findElement(By.id("nome")).sendKeys(name);
        browser.findElement(By.id("valorInicial")).sendKeys(value);
        browser.findElement(By.id("dataAbertura")).sendKeys(date);
    }

    // Método busca através do HTML com Xpath se os ultimos elementos adicionados existem na pagina
    public boolean isAuctionRegister(String name, String value, String date) {
        WebElement columnName = browser.findElement(By.xpath(String.format("(//td[contains(text(),'%s')])[last()]", name)));
        WebElement columnValue = browser.findElement(By.xpath(String.format("(//td[contains(text(),'%s')])[last()]", value)));
        WebElement columnDate = browser.findElement(By.xpath(String.format("(//td[contains(text(),'%s')])[last()]", date)));
        // WebElement é o Objeto que  é retornado do findElement.

        return columnName.getText().contains(name)
                && columnValue.getText().contains(value)
                && columnDate.getText().contains(date);
    }

    public boolean containsFieldsErrors() {
        String pageSource = browser.getPageSource();
        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
