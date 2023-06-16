package testSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.AuctionPage;
import pageObject.LoginPage;

public class AuctionTest {

    private AuctionPage auctionPage;

    @BeforeEach
    void beforeEach() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.fillFormLogin("fulano", "pass");
        auctionPage = loginPage.submitLogin();
    }

    @AfterEach
    void afterEach() {
        this.auctionPage.closePage();
    }

    @Test
    void shouldRegisterAuction() {
        Assertions.assertTrue(auctionPage.isContainsPageSource("Leilões cadastrados"));
        Assertions.assertTrue(auctionPage.isPageAuction());
        auctionPage.newAuctionLink();

        Assertions.assertTrue(auctionPage.isContainsPageSource("Novo Leilão"));
        Assertions.assertTrue(auctionPage.isPageNewAuction());

        auctionPage.fillFormAuction("XPTO", "500", "10/10/2010");
        auctionPage.saveAuction();

        Assertions.assertTrue(auctionPage.isContainsPageSource("Leilão salvo com sucesso"));
        Assertions.assertTrue(auctionPage.isPageAuction());

        Assertions.assertTrue(auctionPage.isAuctionRegister("XPTO", "10/10/2010", "500.00"));
    }

    @Test
    void shouldShowErrorIfFieldsEmpty() {
        auctionPage.newAuctionLink();
        auctionPage.saveAuction();
        Assertions.assertTrue(auctionPage.containsFieldsErrors());
    }

}