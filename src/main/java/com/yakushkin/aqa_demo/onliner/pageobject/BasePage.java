package com.yakushkin.aqa_demo.onliner.pageobject;

import lombok.Data;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static java.time.Duration.ofSeconds;

@Data
public abstract class BasePage {

    public void clickOnShoppingCartIcon() {
        $$x("//div[@class='auth-bar auth-bar--top']//a[@href='https://cart.onliner.by']")
                .first()
                .shouldBe(visible, ofSeconds(30))
                .click();
    }
}
