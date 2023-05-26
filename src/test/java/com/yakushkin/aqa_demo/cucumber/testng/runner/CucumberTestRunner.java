package com.yakushkin.aqa_demo.cucumber.testng.runner;

import com.codeborne.selenide.testng.SoftAsserts;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/CucumberTestRunner.html",
                "json:target/cucumber-reports/CucumberTestRunner.json",
                "junit:target/cucumber-reports/CucumberTestRunner.xml"
        },
        monochrome = true,
        tags = "@Scenario_1",
        glue = "com.yakushkin.aqa_demo.cucumber.testng",
        features = "classpath:com/yakushkin/aqa_demo/cucumber/testng/feature"
)
@Listeners(SoftAsserts.class)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
