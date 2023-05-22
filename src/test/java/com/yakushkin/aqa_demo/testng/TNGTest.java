package com.yakushkin.aqa_demo.testng;

import com.codeborne.selenide.testng.SoftAsserts;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;
import static java.time.Duration.ofSeconds;

@SpringBootTest
@ActiveProfiles("test")
@Listeners(SoftAsserts.class)
public class TNGTest extends AbstractTestNGSpringContextTests {

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
