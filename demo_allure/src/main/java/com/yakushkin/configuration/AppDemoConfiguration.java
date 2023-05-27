package com.yakushkin.configuration;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@SpringBootConfiguration
@Slf4j
public class AppDemoConfiguration {

    @Value("${driver.default.name}")
    private String defaultWebDriver;
    @Value("${driver.default.page_load_timeout}")
    private long defaultPageLoadTimeout;
    @Value("${driver.remote.mode}")
    private boolean remoteMode;
    @Value("${docker_mode}")
    private boolean dockerMode;
    @Value("${driver.remote.outer.url}")
    private String remoteOuterServerUrl;
    @Value("${driver.remote.inner.url}")
    private String remoteInnerServerUrl;
    @Value("${assertion_mode}")
    private String assertionMode;

    @Bean
    public void setupDefaultWebDriver() {
        Configuration.browser = defaultWebDriver;
        Configuration.pageLoadTimeout = defaultPageLoadTimeout;
        if (remoteMode) {
            Configuration.remote = dockerMode ? remoteInnerServerUrl : remoteOuterServerUrl;
        }
        Configuration.assertionMode = AssertionMode.valueOf(assertionMode.toUpperCase());

        open();
        getWebDriver().manage().window().maximize();
    }

    @Bean
    public void setupAllureListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }
}
