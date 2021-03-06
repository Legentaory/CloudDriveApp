package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserActionTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    private static WebDriver webDriver;

    private static HomePage homePage;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
    }

    @AfterAll
    public static void afterAll(){
        webDriver.quit();
        webDriver = null;
    }

    @BeforeEach
    public void beforeEach(){
        baseUrl = "http://localhost:" + port;
    }

    @Test
    public void userBehavior() throws InterruptedException {
        webDriver.get(baseUrl + "/signup");
        signup();
        login();
        uploadFile();
        insertCredential();
        insertNote();
        downloadFile();
        deleteFile();
        editNote();
        deleteNote();
        editCredential();
        deleteCredential();
        logout();
        assert webDriver.getCurrentUrl().equals(baseUrl + "/login");
    }

    private void signup(){
        String userName = "ShuaiBi";
        String firstName = "Shuai";
        String lastName = "Bi";
        String password = "taitamashuai";
        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.signUp(firstName,lastName,userName,password);
        signupPage.redirectToLogin();
    }

    private void login() throws InterruptedException {
        String userName = "ShuaiBi";
        String password = "taitamashuai";
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(userName, password);
    }

    private void insertNote(){
        String title = "Hello World!";
        String description = "Hello you!";
        homePage.addNote(title, description);
        backToHome();
    }

    private void uploadFile(){
        homePage.uploadFile("123.pdf");
        backToHome();
    }

    private void insertCredential(){
        String url = "test.com";
        String username = "username";
        String password = "password";
        homePage.addCredential(url, username, password);
        backToHome();
    }

    private void downloadFile(){
        homePage.viewFile();
    }

    private void deleteFile(){
        homePage.deleteFile();
        backToHome();
    }

    private void editNote(){
        String title = "Hello World!";
        String description = title;
        homePage.editNote(title, description);
        backToHome();
    }

    private void deleteNote(){
        homePage.deleteNote();
        backToHome();
    }

    private void editCredential(){
        String url = "test.com";
        String username = "username1";
        String password = "password1";
        homePage.editCredential(url, username, password);
        backToHome();
    }

    private void deleteCredential(){
        homePage.deleteCredential();
        backToHome();
    }

    private void logout(){
        homePage.logout();
    }

    private void backToHome(){
        ResultPage page = new ResultPage(webDriver);
        page.clickOk();
    }
}
