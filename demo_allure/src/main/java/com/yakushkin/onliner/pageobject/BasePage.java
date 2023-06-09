package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Data;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofSeconds;

@Data
public abstract class BasePage {

    @Step("typing to search line the next request '{request}'")
    public SearchFrame typingIntoSearchLine(String request) {
        $x("//input[@class='fast-search__input']")
                .shouldBe(visible, ofSeconds(5))
                .sendKeys(request);
        switchToSearchFrame();

        return new SearchFrame();
    }

    @Step("switch to search frame")
    public SearchFrame switchToSearchFrame() {
        final SelenideElement searchFrame = $x("//iframe[@class='modal-iframe']")
                .should(and("active", exist, visible), ofSeconds(5));
        switchTo().frame(searchFrame);

        return new SearchFrame();
    }

    @Step("click on 'Вход' button")
    public LoginForm clickOnEnterButton() {
        $x("//div[contains(text(),'Вход')]")
                .shouldBe(visible, ofSeconds(5))
                .click();

        return new LoginForm();
    }
}
