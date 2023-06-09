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

    @Test
    @Severity(SeverityLevel.BLOCKER)
    void checkLoginForm() {
        Configuration.assertionMode = AssertionMode.SOFT;
        mainPage
                .open()
                .clickOnEnterButton()
                .checkLoginFormStructure()
                .checkLoginHeaderStructure()
                .checkLoginBodyStructure()
                .checkLoginFooterStructure();
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
