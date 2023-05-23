package com.yakushkin.aqa_demo.framework;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.yakushkin.aqa_demo.util.PropertiesReader.getProperty;

@Component
@Slf4j
public class DriverManager {

    private static final long DEFAULT_PAGE_LOAD_TIMEOUT = Long.parseLong(getProperty("driver.default.page_load_timeout"));
    private static final boolean REMOTE_MODE = Boolean.parseBoolean(getProperty("driver.remote.mode"));
    private static final String REMOTE_SERVER_URL = getProperty("driver.remote.outer.url");
    private static final String ASSERTION_MODE = getProperty("assertion_mode");

    public static void initDriver(String browserName) {
        Configuration.browser = browserName;
        Configuration.pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
        Configuration.assertionMode = AssertionMode.valueOf(ASSERTION_MODE.toUpperCase());
        if (REMOTE_MODE) {
            Configuration.remote = REMOTE_SERVER_URL;
        }

        open();
        getWebDriver().manage().window().maximize();
    }

    public static void initDriver(String browserName, boolean remoteMode) {
        Configuration.browser = browserName;
        Configuration.pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
        Configuration.assertionMode = AssertionMode.valueOf(ASSERTION_MODE.toUpperCase());
        if (remoteMode) {
            Configuration.remote = REMOTE_SERVER_URL;
        }

        open();
        getWebDriver().manage().window().maximize();
    }

    public static void initDriver(String browserName, boolean remoteMode, String remoteServerUrl) {
        Configuration.browser = browserName;
        Configuration.pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
        Configuration.assertionMode = AssertionMode.valueOf(ASSERTION_MODE.toUpperCase());
        if (remoteMode) {
            Configuration.remote = remoteServerUrl;
        }

        open();
        getWebDriver().manage().window().maximize();
    }

}
