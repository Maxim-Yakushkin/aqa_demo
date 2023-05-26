package com.yakushkin.aqa_demo.onliner.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.files.DownloadActions.click;
import static java.time.Duration.ofSeconds;

public class ProductPage extends BasePage {

    private static final String XPATH_EXPRESSION = "//a[contains(text(),'%s')]";

    public void openByUrl(String url) {
        open(url);
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
        final SelenideElement element = $x("//div[@class='product-recommended__sidebar-close']");
        if (element.exists()) {
            element.click();
        }
    }

    public void verifyOrderButtonHasNameInCart(String buttonName) {
        $$x(String.format(XPATH_EXPRESSION, buttonName))
                .first()
                .shouldBe(visible, ofSeconds(5));
    }
}
