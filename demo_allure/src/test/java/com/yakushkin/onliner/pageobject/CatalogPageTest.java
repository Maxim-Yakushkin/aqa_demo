package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith({SoftAssertsExtension.class})
public class CatalogPageTest {

    @Autowired
    private CatalogPage catalogPage;

    @Test
    void checkOpenCatalogPage() {
        catalogPage.open();
    }

    @Test
    void checkNavigationTitle() {
        catalogPage
                .open()
                .verifyNavigationTitle();
    }

    @Test
    void checkThatClassifierMenuContainsNecessaryClassifiers() {
        catalogPage
                .open()
                .verifyCatalogNavigationClassifiers();
    }

    @Test
    void checkThatComputersAndNetworksClassifierContainsNecessaryPointsInVerticalMenu() {
        catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .hoverToAccessoriesAsideTitle()
                .verifyComputerAndNetworksVerticalMenuPoints();
    }

    @Test
    void checkThatAllCategoriesInClassifierContainsNecessaryFiledValues() {
        Configuration.assertionMode = AssertionMode.SOFT;
        catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .hoverToAccessoriesAsideTitle()
                .verifyCategoriesForAccessories();
    }

    @Test
    void checkPreviewStructureForEachTradeItemOnPageWithListOfGoods() {
        Configuration.assertionMode = AssertionMode.SOFT;
        catalogPage
                .open()
                .clickOnElectronicsClassifier()
                .hoverToAudioEquipmentAsideTitle()
                .clickOnHeadPhoneCategory()
                .verifyProductCards()
                .verifyProductCardTitles()
                .verifyProductPrices()
                .verifyProductDescriptions()
                .verifyProductRatings()
                .verifyProductImages()
                .verifyProductCheckboxes();
    }
}
