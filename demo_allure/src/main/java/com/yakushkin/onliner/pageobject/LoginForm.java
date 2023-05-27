package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofSeconds;

@Component
public class LoginForm {

    public LoginForm() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @Step("typing the login and password into login form")
    public LoginForm typingCredentials(String userNameOrEmail, String password) {
        $x("//input[contains(@placeholder,'Ник или e-mail')]")
                .shouldBe(visible, ofSeconds(5))
                .setValue(userNameOrEmail);
        $x("//input[contains(@placeholder,'Пароль')]")
                .shouldBe(visible, ofSeconds(5))
                .setValue(password);

        return this;
    }

    @Step("click on the 'Войти' button")
    public LoginForm clickOnLoginButton() {
        $x("//button[contains(text(),'Войти')]")
                .shouldBe(and("clickable", exist, visible), ofSeconds(5))
                .click();

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
