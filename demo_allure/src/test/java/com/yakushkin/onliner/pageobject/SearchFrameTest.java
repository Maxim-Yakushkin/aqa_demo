package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith({SoftAssertsExtension.class})
public class SearchFrameTest {

    @Autowired
    private MainPage mainPage;

    @Test
    void checkSearchFrameTabs() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .verifyAllSearchTabItems();
    }

    @Test
    void checkSearchResultCategoriesInCatalogTab() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .verifyCatalogSearchResultCategories();
    }

    @Test
    void checkSearchResultInNewsTabWithSelectedCheckBoxWithVideo() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .clickOnNews()
                .clickInNewsOnVideoFilterCheckbox()
                .verifyNewsSearchResultWithVideo();
    }
}
