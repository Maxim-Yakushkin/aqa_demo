package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith({SoftAssertsExtension.class})
public class LoginFormTest {

    @Autowired
    private MainPage mainPage;
    @Autowired
    private CatalogPage catalogPage;

    @Test
    @Severity(SeverityLevel.BLOCKER)
    void loginFromMainPage() {
        Configuration.assertionMode = AssertionMode.SOFT;
        mainPage
                .open()
                .clickOnEnterButton()
                .typingCredentials("***", "***") // type e-mail and password instead ***
                .clickOnLoginButton()
                .clickOnRecaptchaCheckbox();

        mainPage
                .verifyNavigationLogo();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    void loginFromCatalogPage() {
        Configuration.assertionMode = AssertionMode.SOFT;
        catalogPage
                .open()
                .clickOnEnterButton()
                .typingCredentials("***", "***") // type e-mail and password instead ***
                .clickOnLoginButton()
                .clickOnRecaptchaCheckbox();

        catalogPage
                .verifyNavigationTitle();
    }

    @Test
    void checkClosingLoginForm() {
        mainPage
                .open()
                .clickOnEnterButton()
                .clickOnCloseCross();

        mainPage
                .verifyNavigationLogo();
    }
}
