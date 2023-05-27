package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static java.time.Duration.ofSeconds;

public class ProductPage extends BasePage {

    private static final String XPATH_EXPRESSION = "//a[contains(text(),'%s')]";

    public void openByUrl(String url) {
        Selenide.open(url);
        webdriver().shouldHave(url(url));
    }

    public void verifyCurrentProductUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl), ofSeconds(10));
    }


    public void clickOnBuyNowButton(String buttonName) {
        $$x(String.format(XPATH_EXPRESSION, buttonName))
                .first()
                .shouldBe(visible, ofSeconds(5))
                .click();
    }

    public void closeProductRecommendedSidebar() {
        final SelenideElement recommendedSidebar = $x("//div[@class='product-recommended__sidebar-close']");
        if (recommendedSidebar.exists()) {
            recommendedSidebar
                    .shouldBe(visible, ofSeconds(10))
                    .click();
        }
    }

    public void verifyOrderButtonHasNameInCart(String buttonName) {
        $$x(String.format(XPATH_EXPRESSION, buttonName))
                .first()
                .shouldBe(visible, ofSeconds(5));
    }
}
