package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.yakushkin.framework.DriverManager.initDriver;

@SpringBootTest
@ExtendWith({SoftAssertsExtension.class})
public class MainPageTest {

    @Autowired
    private MainPage mainPage;

    @BeforeAll
    static void setupDriver() {
        initDriver(Browsers.SAFARI);
    }

    @Test
    public void checkOpenMainPage() {
        mainPage.open();
    }

    @Test
    public void goToCatalogSection() {
        mainPage
                .open()
                .clickOnCatalogSection();
    }
}