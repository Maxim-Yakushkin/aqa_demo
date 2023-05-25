package com.yakushkin.aqa_demo.junit;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.yakushkin.aqa_demo.framework.DriverManager.initDriver;
import static io.qameta.allure.Allure.step;
import static java.time.Duration.ofSeconds;

@SpringBootTest
@ActiveProfiles("test")
class AqaDemoApplicationTest {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        initDriver(Browsers.EDGE);
    }

    @Test
    void openOnlinerHome() {
        step("Open Onliner Home page", () -> open("https://www.onliner.by/"));
        step("check URL and browser title", () -> webdriver()
                .shouldHave(url("https://www.onliner.by/"), ofSeconds(10))
                .shouldHave(title("Onlínerr"), ofSeconds(10)));
    }

    @Test
    void openOnlinerCatalog() {
        step("Open Online Catalog page", () -> open("https://catalog.onliner.by/"));
        step("check URL and browser title", () -> webdriver()
                .shouldHave(url("https://catalog.onliner.by/"), ofSeconds(10))
                .shouldHave(title("Каталог Onlíner"), ofSeconds(10)));
    }

}
