package com.adf;

import com.adf.base.TestBase;
import com.adf.pages.FilesPage;
import com.adf.pages.LoginPage;
import com.adf.pages.SettingsPage;
import com.adf.util.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



public class AppTest extends TestBase
{
    SettingsPage settingsPage;
    LoginPage loginPage;
    FilesPage filesPage;
    WebDriver driver = initialization();

    public AppTest(){super();}


    @Test (priority = 1)
    public void shouldChangeProvider() {

        settingsPage = new SettingsPage()
                .navigateToSettingsPage()
                .clickProviderDropDown()
                .selectDropDownOption(Constants.ECM_OPTION)
                .verifyOptionIsSelected(Constants.ECM_OPTION)
                .clickApplyButton();
    }

    @Test (priority = 2)
    public void shouldLogin(){
        loginPage = new LoginPage()
                .navigateToLoginPage()
                .loginWithValidCredentials(Constants.EMAIL_ADDRESS, Constants.PASSWORD)
                .verifyUserIsLoggedIn();
    }

    @Test (priority = 3)
    public void shouldCreateNewFolder() {
        //shouldLogin();

        filesPage = new FilesPage()
                .navigateToFilesPage()
                .createNewFolderWithSpecifiedName(Constants.FOLDER_NAME)
                .verifyThatNewFolderIsCreatedWithSpecifiedName(Constants.FOLDER_NAME);
    }

    @Test (priority = 4)
    public void shouldCreateNewFolderAndTryToCreateAFolderWithSameName() {
        //shouldCreateNewFolder();

        filesPage = new FilesPage()
                .createNewFolderWithSpecifiedName(Constants.FOLDER_NAME)
                .verifySameFolderErrorMessageIsDisplayed()
                .clickCreateNewFolderDialogCancelButton();
    }

    @Test (priority = 5)
    public void shouldDeleteFolder() {
        //shouldCreateNewFolder();

        filesPage = new FilesPage()
                .selectFolderRowByName(Constants.FOLDER_NAME)
                .verifyFolderNameRowIsSelected(Constants.FOLDER_NAME)
                .clickSelectedRowActionMenuButton()
                .deleteSelectedFolder(Constants.FOLDER_NAME)
                .verifyDeletedFolderMessageIsDisplayed(Constants.FOLDER_NAME);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
