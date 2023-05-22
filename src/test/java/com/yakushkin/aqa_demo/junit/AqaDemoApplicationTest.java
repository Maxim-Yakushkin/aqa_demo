package com.yakushkin.aqa_demo.junit;

import com.codeborne.selenide.Browsers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.yakushkin.aqa_demo.framework.DriverManager.initDriver;
import static java.time.Duration.ofSeconds;

@SpringBootTest
@ActiveProfiles("test")
class AqaDemoApplicationTest {

    @BeforeAll
    static void setUp() {
        initDriver(Browsers.EDGE);
    }

    @Test
    void openOnlinerHome() {
        open("https://www.onliner.by/");
        webdriver()
                .shouldHave(url("https://www.onliner.by/"), ofSeconds(10))
                .shouldHave(title("Onlíner"), ofSeconds(10));
    }

    @Test
    void openOnlinerCatalog() {
        open("https://catalog.onliner.by/");
        webdriver()
                .shouldHave(url("https://catalog.onliner.by/"), ofSeconds(10))
                .shouldHave(title("Каталог Onlíner"), ofSeconds(10));
    }

}
