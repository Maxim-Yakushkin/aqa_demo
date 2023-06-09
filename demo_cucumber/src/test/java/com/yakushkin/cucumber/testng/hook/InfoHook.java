package com.yakushkin.cucumber.testng.hook;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class InfoHook {

    @BeforeAll
    public static void startMessage() {
        System.out.println("Cucumber was STARTED successfully");
    }

    @AfterAll
    public static void endMessage() {
        System.out.println("Cucumber was FINISHED successfully");
    }
}
