package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.yakushkin.enumiration.OnlinerBaseUrl.CATALOG_PAGE;
import static com.yakushkin.enumiration.OnlinerBaseUrl.MAIN_PAGE;
import static java.time.Duration.ofSeconds;

@Component
public class MainPage extends BasePage {
    private static final String MAIN_NAVIGATION_SECTION_XPATH_PATTERN = "//ul[@class='b-main-navigation']" +
                                                                        "//span[text()='%s']/parent::*";

    @Step("open Onliner home page ( https://www.onliner.by/ )")
    public MainPage open() {
        Selenide.open(MAIN_PAGE.getUrl());
        webdriver()
                .shouldHave(url(MAIN_PAGE.getUrl()))
                .shouldHave(title(MAIN_PAGE.getTitle()));

        return this;
    }

    @Step("verify Onliner navigation logo is present")
    public SelenideElement verifyNavigationLogo() {
        webdriver()
                .shouldHave(url(MAIN_PAGE.getUrl()))
                .shouldHave(title(MAIN_PAGE.getTitle()));

        return $x("//div[@class='b-top-logo']")
                .shouldBe(and("presented and displayed", exist, visible), ofSeconds(5));
    }

    @Step("click on 'Каталог' section in main navigation bar")
    public CatalogPage clickOnCatalogSection() {
        $x(String.format(MAIN_NAVIGATION_SECTION_XPATH_PATTERN, "Каталог"))
                .shouldBe(visible, ofSeconds(10))
                .click();

        webdriver()
                .shouldHave(url(CATALOG_PAGE.getUrl()))
                .shouldHave(title(CATALOG_PAGE.getTitle()));

        return new CatalogPage();
    }
}
