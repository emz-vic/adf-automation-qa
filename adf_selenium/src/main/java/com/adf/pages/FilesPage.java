package com.adf.pages;

import com.adf.base.TestBase;
import com.adf.util.Constants;
import com.adf.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class FilesPage extends TestBase {

    @FindBy(css = "button[data-automation-id=create-new-folder]")
    WebElement createNewFolderButton;

    @FindBy(id = "adf-folder-name-input")
    WebElement createNewFolderDialogNameInput;

    @FindBy(id = "adf-folder-create-button")
    WebElement createNewFolderDialogCreateButton;

    @FindBy(id = "adf-folder-cancel-button")
    WebElement createNewFolderDialogCancelButton;

    @FindBy(id = "document-list-container")
    WebElement documentContainer;

    @FindBy(css = "adf-datatable-row.adf-is-selected")
    WebElement selectedFolderRow;

    @FindBy(css = "button[data-automation-id='DOCUMENT_LIST.ACTIONS.FOLDER.DELETE']")
    WebElement deleteSelectedFolderAction;

    @FindBy(tagName = "simple-snack-bar")
    WebElement errorPopUp;

    public FilesPage () {
        PageFactory.initElements(driver, this);
    }

    public FilesPage navigateToFilesPage() {
        driver.get(Constants.FILES_URL);
        TestUtil.waitForLoad(driver);

        return this;
    }

    public FilesPage clickCreateNewFolderButton(){
        wait.until(ExpectedConditions.visibilityOf(createNewFolderButton));
        createNewFolderButton.click();

        return this;
    }

    public FilesPage completeFolderName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(createNewFolderDialogNameInput));
        createNewFolderDialogNameInput.sendKeys(name);

        return this;
    }

    public FilesPage clickCreateNewFolderDialogCreateButton(){
        wait.until(ExpectedConditions.elementToBeClickable(createNewFolderDialogCreateButton));
        createNewFolderDialogCreateButton.click();

        return this;
    }

    public FilesPage clickCreateNewFolderDialogCancelButton(){
        wait.until(ExpectedConditions.elementToBeClickable(createNewFolderDialogCancelButton));
        createNewFolderDialogCancelButton.click();

        return this;
    }

    public FilesPage createNewFolderWithSpecifiedName(String name){
        clickCreateNewFolderButton();
        completeFolderName(name);
        clickCreateNewFolderDialogCreateButton();

        return this;
    }

    public FilesPage selectFolderRowByName(String name){
        WebElement folderNameRow = getFolderNameWebElement(name);
        wait.until(ExpectedConditions.elementToBeClickable(folderNameRow));
        folderNameRow.click();

        return this;
    }

    public FilesPage clickSelectedRowActionMenuButton(){
        selectedFolderRow.findElement(By.cssSelector("button[aria-label=Actions]")).click();

        return this;
    }

    public FilesPage deleteSelectedFolder(String folderName){
        wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedFolderAction));
        deleteSelectedFolderAction.click();
        wait.until(ExpectedConditions.stalenessOf(getFolderNameWebElement(folderName)));

        return this;
    }

    public FilesPage verifyThatNewFolderIsCreatedWithSpecifiedName(String name){
        WebElement folderNameRow = getFolderNameWebElement(name);
        wait.until(ExpectedConditions.elementToBeClickable(folderNameRow));
        Assert.assertTrue(folderNameRow.getText().contains(name), "Folder is not created.");

        return this;
    }

    public FilesPage verifySameFolderErrorMessageIsDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("simple-snack-bar")));
        String errorMessage = errorPopUp.getText();
        Assert.assertEquals(errorMessage, Constants.SAME_FOLDER_ERROR_MESSAGE, "Same folder message is not displayed");

        return this;
    }

    public FilesPage verifyFolderNameRowIsSelected(String folderName){
        wait.until(ExpectedConditions.elementToBeClickable(selectedFolderRow));
        String isSelectedAttributeValue = selectedFolderRow.getAttribute("aria-selected");
        Assert.assertEquals(isSelectedAttributeValue,"true", "Folder with name '"+folderName+"' is not selected.");

        return this;
    }

    public FilesPage verifyDeletedFolderMessageIsDisplayed(String folderName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("simple-snack-bar")));
        String deleteMessage = errorPopUp.getText();
        Assert.assertEquals(deleteMessage, ""+folderName+" deleted", "Folder message is not displayed");

        return this;
    }


    private WebElement getFolderNameWebElement(String name) {
        String cssSelector = "div[data-automation-id=text_"+name+"]";
        return driver.findElement(By.cssSelector(cssSelector));
    }
}
