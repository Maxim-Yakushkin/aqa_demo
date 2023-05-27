package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import lombok.Data;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.yakushkin.framework.DriverManager.initDriver;
import static java.time.Duration.ofSeconds;

@Data
public abstract class BasePage {

    public BasePage() {
        initDriver(Browsers.CHROME);
    }

    public void clickOnShoppingCartIcon() {
        $$x("//div[@class='auth-bar auth-bar--top']//a[@href='https://cart.onliner.by']")
                .first()
                .shouldBe(visible, ofSeconds(30))
                .click();
    }
}
