package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.yakushkin.enumiration.CatalogNavigationClassifier;
import com.yakushkin.enumiration.ComputerAndNetworksVerticalMenuPoint;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.yakushkin.enumiration.OnlinerBaseUrl.CATALOG_PAGE;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.stream;
import static org.openqa.selenium.By.className;

@Component
public class CatalogPage extends BasePage {

    private static final String CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH = "//li[@class='catalog-navigation-classifier__item ']";
    private static final String CATALOG_CLASSIFIER_XPATH_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'%s')]";
    private static final String COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN = "//div[@class='catalog-navigation-list__aside-title' and normalize-space(text())='%s']";
    private static final String ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN = "//div[normalize-space(text())='%s']/parent::*//a/span";
    private static final String CATEGORY_XPATH_PATTERN = "//div[contains(text(),'%s')]/parent::*//span[contains(text(),'%s')]";

    public CatalogPage() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    //    @Override
    @Step("open Onliner Catalog page ( https://catalog.onliner.by/ )")
    public CatalogPage open() {
        Selenide.open(CATALOG_PAGE.getUrl());
        webdriver()
                .shouldHave(url(CATALOG_PAGE.getUrl()))
                .shouldHave(title(CATALOG_PAGE.getTitle()));

        return this;
    }

    @Step("verify the Catalog page has correct url, browser title and page title")
    public CatalogPage verifyNavigationTitle() {
        webdriver()
                .shouldHave(url(CATALOG_PAGE.getUrl()))
                .shouldHave(title(CATALOG_PAGE.getTitle()));

        $x("//div[@class='catalog-navigation__title']")
                .shouldBe(and("presented and displayed", exist, visible), ofSeconds(5))
                .shouldHave(text("Каталог"));

        return this;
    }

    @Step("verify the Catalog page contains all necessary classifiers in navigation bar")
    public CatalogPage verifyCatalogNavigationClassifiers() {
        final List<String> expectedClassifierTitles = stream(CatalogNavigationClassifier.values())
                .map(CatalogNavigationClassifier::getTitle)
                .toList();

        $$x(CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH)
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(15))
                .shouldHave(containExactTextsCaseSensitive(expectedClassifierTitles));

        return this;
    }

    @Step("click by 'Компьютеры и сети' classifier")
    public CatalogPage clickOnComputersAndNetworksClassifier() {
        $x(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Компьютеры"))
                .shouldBe(visible, ofSeconds(15))
                .click();
        return this;
    }


    @Step("click by 'Электроника' classifier")
    public CatalogPage clickOnElectronicsClassifier() {
        $x(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Электроника"))
                .shouldBe(visible, ofSeconds(15))
                .click();
        return this;
    }

    @Step("click by 'Наушники' category")
    public CategoryPage clickOnHeadPhoneCategory() {
        $x(String.format(CATEGORY_XPATH_PATTERN, "Аудиотехника", "Наушники"))
                .shouldBe(visible, ofSeconds(15))
                .click();
        return new CategoryPage();
    }

    @Step("hover to 'Комплектующие' title")
    public CatalogPage hoverToAccessoriesAsideTitle() {
        $x(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие"))
                .shouldBe(visible, ofSeconds(15))
                .hover();
        return this;
    }

    @Step("hover to 'Аудиотехника' title")
    public CatalogPage hoverToAudioEquipmentAsideTitle() {
        $x(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Аудиотехника"))
                .shouldBe(visible, ofSeconds(15))
                .hover();
        return this;
    }

    @Step("verify the each goods category in 'Комплектующие' has title, count of goods and start price")
    public CatalogPage verifyCategoriesForAccessories() {
        $$x(String.format(ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие"))
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("title is not blank", element ->
                        !element.findElement(className("catalog-navigation-list__dropdown-title")).getText().isBlank()))
                .shouldHave(allMatch("count of goods is not blank", element ->
                        !element.findElement(className("catalog-navigation-list__dropdown-description"))
                                .getText().split("\n")[0].isBlank())) // 0 - index of count of goods in description
                .shouldHave(allMatch("start price of goods is not blank", element ->
                        !element.findElement(className("catalog-navigation-list__dropdown-description"))
                                .getText().split("\n")[1].isBlank())); // 1 - index of start price in description

        return this;
    }

    @Step("verify the vertical menu contains necessary pints in 'Компьютеры и сети' classifier")
    public CatalogPage verifyComputerAndNetworksVerticalMenuPoints() {
        final List<String> expectedVerticalMenuPointTitles = stream(ComputerAndNetworksVerticalMenuPoint.values())
                .map(ComputerAndNetworksVerticalMenuPoint::getName)
                .toList();

        $$x("//div[contains(@class,'aside catalog-navigation-list__aside_active')]" +
            "//div[contains(@class,'catalog-navigation-list__aside-list')]" +
            "//div[contains(@class,'title')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(containExactTextsCaseSensitive(expectedVerticalMenuPointTitles));

        return this;
    }
}
