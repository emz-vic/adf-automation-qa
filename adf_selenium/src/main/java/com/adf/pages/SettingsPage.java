package com.adf.pages;

import com.adf.base.TestBase;
import com.adf.util.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;
import java.util.Set;


public class SettingsPage extends TestBase {

    @FindBy(id = "adf-provider-selector")
     WebElement providerDropDown;

    @FindBy(css = "button[data-automation-id=host-button]" )
     WebElement applyButton;

    public SettingsPage(){
        PageFactory.initElements(driver, this);
    }

    public SettingsPage navigateToSettingsPage () {
        driver.get(Constants.SETTING_URL);
        wait.until(ExpectedConditions.elementToBeClickable(applyButton));

        return this;
    }

    public SettingsPage clickProviderDropDown (){
        wait.until(ExpectedConditions.elementToBeClickable(providerDropDown));
        providerDropDown.click();

        return this;
    }

    public SettingsPage selectDropDownOption(String option){
        List<WebElement> providerOptions = driver.findElements(By.tagName("mat-option"));
        for (int i = 0; i < providerOptions.size(); i++ ) {

            WebElement currentProviderOption = providerOptions.get(i);

            wait.until(ExpectedConditions.elementToBeClickable(currentProviderOption));

            if(currentProviderOption.getText().equals(option)) {
                currentProviderOption.click();
                break;
            }
        }

        return this;
    }

    public SettingsPage clickApplyButton(){
        wait.until(ExpectedConditions.elementToBeClickable(applyButton));
        applyButton.click();

        return this;
    }

    public SettingsPage verifyOptionIsSelected(String option){
        String dropdownText = providerDropDown.getText();
        Assert.assertEquals(dropdownText, option, "Correct option is not selected.");

        return this;
    }
}
