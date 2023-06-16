package testSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void beforeEach() throws InterruptedException {
        loginPage = new LoginPage();
    }

    @AfterEach
    void afterEach() {
        this.loginPage.closePage();
    }

    @Test
    void shouldLoginValidData() throws InterruptedException {
        loginPage.fillFormLogin("fulano", "pass");
        loginPage.submitLogin();

        assertFalse(loginPage.isPageLogin());
        assertEquals("fulano", loginPage.getUsernameLogged());
    }

    @Test
    void shouldShowErroLoginInvalid() throws InterruptedException {
        loginPage.fillFormLogin("invalido", "0000");
        loginPage.submitLogin();

        assertTrue(loginPage.isPageLoginError());
        assertNull(loginPage.getUsernameLogged());
        assertTrue(loginPage.isContainsPageSource("Usuário e senha inválidos."));
    }

    @Test
    void dontShouldAcessRestrictedPage() {
        loginPage.currentUrl("login");

        assertTrue(loginPage.isPageLogin());
        assertFalse(loginPage.isContainsPageSource("Dados do Leilão"));

    }
}