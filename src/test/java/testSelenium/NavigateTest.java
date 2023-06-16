package testSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.NoSuchElementException;
import pageObject.NavigatePages;

public class NavigateTest {

    private NavigatePages navigatePages;

    @BeforeEach
    void beforeEach() throws InterruptedException {
        navigatePages = new NavigatePages();
    }

    @AfterEach
    void afterEach() {
        navigatePages.closePage();
    }

    @Test
    void shouldNavigateInAllPages() throws InterruptedException {
        navigatePages.fillFormLogin("fulano", "pass");
        navigatePages.submitLogin();
        Assertions.assertTrue(navigatePages.isPageAuction());

        navigatePages.buttonNewAuctionLink();
        Assertions.assertTrue(navigatePages.isPageNewAuction());

        navigatePages.buttonReturn();
        Assertions.assertTrue(navigatePages.isPageAuction());

        navigatePages.buttonEdit();;
        Assertions.assertTrue(navigatePages.isPageEdit());

        navigatePages.buttonReturn();
        Assertions.assertTrue(navigatePages.isPageAuction());

        navigatePages.buttonBid();
        Assertions.assertTrue(navigatePages.isPageBid());

        navigatePages.buttonNewBid();
        navigatePages.isBidRegister("100");

        navigatePages.buttonHome();
        Assertions.assertTrue(navigatePages.isPageAuction());

        navigatePages.buttonGoOut();
        Assertions.assertThrows(NoSuchElementException.class, () -> navigatePages.isUserLogged());
        Assertions.assertTrue(navigatePages.isUserLogout());

        navigatePages.buttonToEnter();
        Assertions.assertTrue(navigatePages.isPageLogin());
    }

}