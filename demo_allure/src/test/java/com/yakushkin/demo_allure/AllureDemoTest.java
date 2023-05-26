package com.yakushkin.demo_allure;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest
@ActiveProfiles("test")
class AllureDemoTest {

    @Value("${driver.default.name}")
    private String browser;

    @Test
    void test1() {
        Configuration.browser = browser;
        open("https://www.onliner.by");
    }
}