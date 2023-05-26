package com.yakushkin.aqa_demo.cucumber.testng.hook;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ScreenshotHook {

    @After
    public void takeScreenshot(Scenario scenario) {
        byte[] screenshotAsBytes = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshotAsBytes, "image/png", scenario.getName());
    }
}
