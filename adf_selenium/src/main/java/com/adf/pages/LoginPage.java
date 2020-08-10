package com.adf.pages;

import com.adf.base.TestBase;
import com.adf.util.Constants;
import com.adf.util.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import sun.rmi.runtime.Log;

public class LoginPage extends TestBase {
    @FindBy(css = "input[data-automation-id=username]")
    WebElement usernameInput;

    @FindBy(css = "input[data-automation-id=password]")
    WebElement passwordInput;

    @FindBy(css = "button[data-automation-id=login-button]")
    WebElement loginButton;


    @FindBy(id = "userinfo_container")
    WebElement userInfoElement;

    public LoginPage () {
        PageFactory.initElements(driver, this);
    }

    public LoginPage navigateToLoginPage () {
        driver.get(Constants.LOGIN_URL);
        TestUtil.waitForLoad(driver);

        return this;
    }

    public LoginPage completeUsername(String username){
        usernameInput.sendKeys(username);

        return this;
    }

    public LoginPage completePassword(String password){
        passwordInput.sendKeys(password);

        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();

        return this;
    }

    public LoginPage loginWithValidCredentials(String username, String password){
        completeUsername(username);
        completePassword(password);
        clickLoginButton();
        wait.until(ExpectedConditions.elementToBeClickable(userInfoElement));

        return this;
    }

    public LoginPage verifyUserIsLoggedIn () {
        String loggedInUserInfo = userInfoElement.getText();
        Assert.assertTrue(loggedInUserInfo.contains("gueste guest"),"User is not logged in.");

        return this;
    }
}
