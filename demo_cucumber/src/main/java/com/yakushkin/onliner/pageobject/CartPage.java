package com.yakushkin.onliner.pageobject;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.back;
import static java.time.Duration.ofSeconds;

public class CartPage {

    public void clickOnBrowserBackArrow() {
        back();
    }

    public ProductPage clickOnFirstProductLink() {
        $$x("//div[@class='cart-form__offers-flex']//a[contains(@class,'cart-form__link_base-alter')]")
                .first()
                .shouldBe(visible, ofSeconds(5))
                .click();

        return new ProductPage();
    }
}
