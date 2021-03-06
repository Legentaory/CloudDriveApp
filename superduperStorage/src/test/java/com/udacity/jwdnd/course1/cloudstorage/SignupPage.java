package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(partialLinkText = "Login")
    private WebElement toLogin;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signUp(String firstname, String lastname, String username, String password) {
        inputFirstName.sendKeys(firstname);
        inputLastName.sendKeys(lastname);
        inputUserName.sendKeys(username);
        inputPassword.sendKeys(password);
        submitButton.click();
    }

    public void redirectToLogin() {
        toLogin.click();
    }
}
