package com.yakushkin.configuration;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.yakushkin.util.LogHelper.LOG_WEB_DRIVER_CONFIGURATION_PATTERN;

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

        log.info(LOG_WEB_DRIVER_CONFIGURATION_PATTERN,
                Configuration.browser, Configuration.pageLoadTimeout, remoteMode, Configuration.remote,
                Configuration.assertionMode.name());

        open();
        getWebDriver().manage().window().maximize();
    }
}
