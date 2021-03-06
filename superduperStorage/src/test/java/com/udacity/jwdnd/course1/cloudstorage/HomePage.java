package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pojos.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pojos.File;
import com.udacity.jwdnd.course1.cloudstorage.pojos.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "btnLogout")
    private WebElement logoutButton;

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "btn-file-upload")
    private WebElement btnUpload;

    @FindBy(xpath = "//button[@id = 'btnAddNewNote']")
    private WebElement btnAddNewNote;

    @FindBy(id = "btnAddNewCredential")
    private WebElement btnAddNewCredential;

    @FindBy(id = "note-title")
    private WebElement txtNoteTitle;

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(xpath = "//a[@href='#nav-notes']")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "note-description")
    private WebElement txtNoteDescription;

    @FindBy(id = "btnSaveChanges")
    private WebElement btnSaveChanges;

    @FindBy(id = "tableNoteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement tableNoteDescription;

    @FindBy(id = "btn-file-view")
    private WebElement btnFileView;

    @FindBy(id = "btn-file-delete")
    private WebElement btnFileDelete;

    @FindBy(id = "btnEditNote")
    private WebElement btnEditNote;

    @FindBy(id = "btnEditCredential")
    private WebElement btnEditCredential;

    @FindBy(id = "note-description")
    private WebElement txtModifyNoteDescription;

    @FindBy(id = "ancDeleteNote")
    private WebElement ancDeleteNote;

    @FindBy(id = "aDeleteCredential")
    private WebElement aDeleteCredential;

    @FindBy(id = "credential-url")
    private WebElement txtCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement txtCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement txtCredentialPassword;

    @FindBy(id = "btnCredentialSaveChanges")
    private WebElement btnCredentialSaveChanges;

    @FindBy(id = "tblCredentialUrl")
    private WebElement tblCredentialUrl;

    @FindBy(id = "tblCredentialUsername")
    private WebElement tblCredentialUsername;

    @FindBy(id = "tblCredentialPassword")
    private WebElement tblCredentialPassword;

    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void uploadFile(String url){
        fileUpload.sendKeys(url);
        btnUpload.click();
    }

    public void addNote(String noteTitle, String description){
        navNotesTab.click();
        btnAddNewNote.click();
        txtNoteTitle.sendKeys(noteTitle);
        txtNoteDescription.sendKeys(description);
        btnSaveChanges.click();
    }

    public void addCredential(String url, String username, String password){
        navCredentialsTab.click();
        btnAddNewCredential.click();
        txtCredentialUrl.sendKeys(url);
        txtCredentialUsername.sendKeys(username);
        txtCredentialPassword.sendKeys(password);
        btnSaveChanges.click();
    }

    public void viewFile(){
        btnFileView.click();
    }

    public void editNote(String noteTitle, String description){
        navNotesTab.click();
        btnEditNote.click();
        txtNoteTitle.clear();
        txtNoteTitle.sendKeys(noteTitle);
        txtNoteDescription.clear();
        txtNoteDescription.sendKeys(description);
        btnSaveChanges.click();
    }

    public void editCredential(String url, String username, String password){
        navCredentialsTab.click();
        btnEditCredential.click();
        txtCredentialUrl.clear();
        txtCredentialUrl.sendKeys(url);
        txtCredentialUsername.clear();
        txtCredentialUsername.sendKeys(username);
        txtCredentialPassword.clear();
        txtCredentialPassword.sendKeys(password);
        btnSaveChanges.click();
    }

    public void deleteFile(){
        btnFileDelete.click();
    }

    public void deleteNote(){
        navNotesTab.click();
        ancDeleteNote.click();
    }

    public void deleteCredential(){
        navCredentialsTab.click();
        aDeleteCredential.click();
    }

    public void logout(){
        logoutButton.click();
    }


}
