package com.yakushkin.util;

public class LogHelper {

    public static final String LOG_WEB_DRIVER_CONFIGURATION_PATTERN = """
            \n===== WebDriver configuration =====
            browser: [{}]
            page load timeout: [{}]
            remote mode: [{}]
            remote URL: [{}]
            assertion mode: [{}]
            """;
}
