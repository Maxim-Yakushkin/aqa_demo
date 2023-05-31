package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.tagName;

@Component
public class LoginForm {

    private static final SelenideElement AUTH_CONTAINER = $x("//div[contains(@class,'auth-container auth-container_max-width_mmmm')]");

    public LoginForm() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @Step("check Login form contains HEADER, BODY and FOOTER")
    public LoginForm checkLoginFormStructure() {
        AUTH_CONTAINER
                .$(className("auth-form__header"))
                .shouldBe(and("Login header is presented", exist, visible), ofSeconds(15));
        AUTH_CONTAINER
                .$(className("auth-form__body"))
                .shouldBe(and("Login body is presented", exist, visible), ofSeconds(15));
        AUTH_CONTAINER
                .$(className("auth-form__footer"))
                .shouldBe(and("Login footer is presented", exist, visible), ofSeconds(15));

        return this;
    }

    @Step("check Login HEADER contains necessary web elements")
    public LoginForm checkLoginHeaderStructure() {
        final SelenideElement loginHeader = AUTH_CONTAINER
                .$(className("auth-form__header"))
                .shouldBe(and("Login header is presented", exist, visible), ofSeconds(15));
        loginHeader
                .$(className("auth-form__preview"))
                .shouldBe(and("Login header preview section is presented", exist, visible), ofSeconds(15));
        loginHeader
                .$(className("auth-form__close"))
                .shouldBe(and("Login header close section is presented", exist, visible), ofSeconds(15));

        return this;
    }

    @Step("check Login BODY contains necessary web elements")
    public LoginForm checkLoginBodyStructure() {
        final SelenideElement loginBody = AUTH_CONTAINER
                .$(className("auth-form__body"))
                .shouldBe(and("Login body is presented", exist, visible), ofSeconds(15));
        $x("//div[contains(normalize-space(text()),'Вход')]")
                .shouldBe(exist, ofSeconds(15));
        $x("//div[contains(normalize-space(text()), 'Через социальные сети')]")
                .shouldBe(and("Presented as body section with alternative login", exist, visible));
        $(tagName("form"))
                .shouldBe(exist, ofSeconds(15));

        return this;
    }

    @Step("check Login FOOTER contains necessary web elements")
    public LoginForm checkLoginFooterStructure() {
        final SelenideElement loginFooter = AUTH_CONTAINER
                .$(className("auth-form__footer"))
                .shouldBe(and("Login footer is presented", exist, visible), ofSeconds(15));
        $x("//*[contains(normalize-space(text()),'© 2001–2023 Onlíner')]")
                .shouldBe(and("Login footer section with text is presented", exist, visible));

        return this;
    }

    @Step("click on the recaptcha checkbox near 'Я не робот' line")
    public void clickOnRecaptchaCheckbox() {
        try {
            switchTo().frame($x("//iframe[@title='reCAPTCHA']"));
            $x("//label[contains(text(),'Я не робот')]")
                    .shouldBe(and("clickable", exist, visible), ofSeconds(5))
                    .click();
        } finally {
            switchTo().defaultContent();
        }
    }

    @Step("click on the closing cross in login form")
    public void clickOnCloseCross() {
        $x("//div[@class='auth-form__close']")
                .shouldBe(and("clickable", exist, visible), ofSeconds(5))
                .click();
    }
}
