package com.yakushkin.configuration;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@SpringBootConfiguration
@Slf4j
public class AppCucumberConfiguration {

    @Value("${driver.default.name}")
    private String defaultWebDriver;
    @Value("${driver.default.page_load_timeout}")
    private long defaultPageLoadTimeout;
    @Value("${driver.remote.mode}")
    private boolean remoteMode;
    @Value("${jenkins_docker_mode}")
    private boolean jenkinsDockerMode;
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
            Configuration.remote = jenkinsDockerMode ? remoteInnerServerUrl : remoteOuterServerUrl;
        }
        Configuration.assertionMode = AssertionMode.valueOf(assertionMode.toUpperCase());

        open();
        getWebDriver().manage().window().maximize();
    }
}
