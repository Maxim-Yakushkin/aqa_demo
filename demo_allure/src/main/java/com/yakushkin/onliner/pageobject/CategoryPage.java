package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

@Component
public class CategoryPage extends BasePage {

    private boolean minipay_popup_is_closed;

    @Step("verify the product cards are visible")
    public CategoryPage verifyProductCards() {
        closeMiniPayTutorialPopupIfVisible();
        $$x("//div[contains(@class,'schema-product__group')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10));

        return this;
    }

    @Step("verify the product card titles are visible and not blank")
    public CategoryPage verifyProductCardTitles() {
        closeMiniPayTutorialPopupIfVisible();
        $$x("//span[contains(@data-bind,'product.full_name')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()), ofSeconds(10));

        return this;
    }

    @Step("verify the product card start prices are visible and not blank")
    public CategoryPage verifyProductPrices() {
        closeMiniPayTutorialPopupIfVisible();
        $$x("//div[not(contains(@class,'schema-product_children'))]" +
            "/div[contains(@class,'schema-product__part_2')]" +
            "/div[contains(@class,'schema-product__part_3')]" +
            "//span[contains(@data-bind,'root.format.minPrice')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()));

        return this;
    }

    @Step("verify the product card descriptions are visible and not blank")
    public CategoryPage verifyProductDescriptions() {
        closeMiniPayTutorialPopupIfVisible();
        $$x("//span[contains(@data-bind,'product.description')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()), ofSeconds(10));

        return this;
    }

    @Step("verify the product card ratings are visible")
    public CategoryPage verifyProductRatings() {
        closeMiniPayTutorialPopupIfVisible();
        $$x("//div[@class='schema-product__rating-group']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10));

        return this;
    }

    @Step("verify the product card images are visible")
    public CategoryPage verifyProductImages() {
        closeMiniPayTutorialPopupIfVisible();
        $$x("//div[@class='schema-product__group']/div/div/div[@class='schema-product__image']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10));

        return this;
    }

    @Step("verify the product card checkboxes are visible")
    public CategoryPage verifyProductCheckboxes() {
        closeMiniPayTutorialPopupIfVisible();
        $$x("//div[not(contains(@class,'schema-product_children'))]" +
            "/div[contains(@class,'schema-product__part_1')]" +
            "/div[@class='schema-product__compare']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10));

        return this;
    }


    private void closeMiniPayTutorialPopupIfVisible() {
        if (!minipay_popup_is_closed) {
            final SelenideElement miniPayTutorialPopupButton =
                    $x("//span[contains(@class, 'schema-product__tutorial-button')]");
            if (miniPayTutorialPopupButton.exists() && miniPayTutorialPopupButton.isDisplayed()) {
                miniPayTutorialPopupButton
                        .shouldBe(visible, ofSeconds(10))
                        .click();
            }

            minipay_popup_is_closed = true;
        }
    }
}
