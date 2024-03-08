package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import jdk.jfr.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.yakushkin.framework.DriverManager.initDriver;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@SpringBootTest
@ExtendWith({SoftAssertsExtension.class})
public class MainPageTest {

    @Autowired
    private MainPage mainPage;

    @BeforeAll
    static void setupDriver() {
        initDriver(Browsers.FIREFOX);
    }

    //    Примеры использования аннотаций для формирования отчета
    @Test
    @Epic("Web interface - 1")
    @Feature("Essential features - 1")
    @Story("Authentication - 1")
    @DisplayName("Test Authentication - 1")
    @Description("This test attempts to log - 1")
    @Severity(CRITICAL)
    public void checkOpenMainPage() {
        mainPage.open();
    }

    @Test
    @Epic("Web interface - 2")
    @Feature("Essential features - 2")
    @Story("Authentication - 2")
    @DisplayName("Test Authentication - 2")
    @Description("This test attempts to log - 2")
    @Severity(BLOCKER)
    public void goToCatalogSection() {
        mainPage
                .open()
                .clickOnCatalogSection();
    }
}