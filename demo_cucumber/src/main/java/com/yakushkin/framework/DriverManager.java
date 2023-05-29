package com.yakushkin.framework;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.yakushkin.util.LogHelper.LOG_WEB_DRIVER_CONFIGURATION_PATTERN;
import static com.yakushkin.util.PropertiesReader.getProperty;

@Component
@Slf4j
public class DriverManager {

    private static final long DEFAULT_PAGE_LOAD_TIMEOUT = Long.parseLong(getProperty("driver.default.page_load_timeout"));
    private static final boolean REMOTE_MODE = Boolean.parseBoolean(getProperty("driver.remote.mode"));
    private static final boolean DOCKER_MODE = Boolean.parseBoolean(getProperty("jenkins_docker_mode"));
    private static final String REMOTE_OUTER_SERVER_HOST = getProperty("driver.remote.outer.host");
    private static final String REMOTE_INNER_SERVER_HOST = getProperty("driver.remote.inner.host");
    private static final String REMOTE_OUTER_SERVER_PORT = getProperty("driver.remote.outer.port");
    private static final String REMOTE_INNER_SERVER_PORT = getProperty("driver.remote.inner.port");
    private static final String REMOTE_SERVER_HUB = getProperty("driver.remote.hub.address");
    private static final String REMOTE_SERVER_URL = DOCKER_MODE ?
            REMOTE_INNER_SERVER_HOST + ":" + REMOTE_INNER_SERVER_PORT + REMOTE_SERVER_HUB :
            REMOTE_OUTER_SERVER_HOST + ":" + REMOTE_OUTER_SERVER_PORT + REMOTE_SERVER_HUB;
    private static final String ASSERTION_MODE = getProperty("assertion_mode");

    public static void initDriver(String browserName) {
        initDriverConfiguration(browserName, REMOTE_MODE, REMOTE_SERVER_URL);
    }

    public static void initDriver(String browserName, boolean remoteMode) {
        initDriverConfiguration(browserName, remoteMode, REMOTE_SERVER_URL);
    }

    public static void initDriver(String browserName, boolean remoteMode, String remoteServerUrl) {
        initDriverConfiguration(browserName, remoteMode, remoteServerUrl);
    }

    private static void initDriverConfiguration(String browserName, boolean remoteMode, String remoteServerUrl) {
        Configuration.browser = browserName;
        Configuration.pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
        Configuration.assertionMode = AssertionMode.valueOf(ASSERTION_MODE.toUpperCase());
        if (remoteMode) {
            Configuration.remote = remoteServerUrl;
        }

        logInitDriverConfiguration();

        open();
        getWebDriver().manage().window().maximize();
    }

    private static void logInitDriverConfiguration() {
        log.info(LOG_WEB_DRIVER_CONFIGURATION_PATTERN,
                Configuration.browser, Configuration.pageLoadTimeout, REMOTE_MODE, Configuration.remote,
                Configuration.assertionMode.name());
    }

}
