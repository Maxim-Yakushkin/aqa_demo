package com.yakushkin.aqa_demo.configuration;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestConfiguration
@Slf4j
public class TestWebDriverConfiguration {

    @Value("${driver.default.name}")
    private String defaultWebDriver;
    @Value("${driver.default.page_load_timeout}")
    private long defaultPageLoadTimeout;
    @Value("${driver.remote.mode}")
    private boolean remoteMode;
    @Value("${driver.remote.outer.url}")
    private String remoteServerUrl;
    @Value("${assertion_mode}")
    private String assertionMode;

    @Bean
    public void setupDefaultWebDriver() {
        Configuration.browser = defaultWebDriver;
        Configuration.pageLoadTimeout = defaultPageLoadTimeout;
        if (remoteMode) {
            Configuration.remote = remoteServerUrl;
        }
        Configuration.assertionMode = AssertionMode.valueOf(assertionMode.toUpperCase());

        open();
        getWebDriver().manage().window().maximize();
    }
}
