package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupLoginTest {

    @LocalServerPort
    private int port;

    public static WebDriver webDriver;

    public String baseURL;

    @BeforeAll
    public static void beforAll(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll(){
        webDriver.quit();
        webDriver = null;
    }

    @BeforeEach
    public void beforeEach(){
        baseURL = "http://localhost:" + port;
    }


    public void signup(){
        String userName = "ShuaiBi";
        String firstName = "Shuai";
        String lastName = "Bi";
        String password = "taitamashuai";

        webDriver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.signUp(firstName,lastName,userName,password);
    }

    public void login() throws InterruptedException {
        String userName = "ShuaiBi";
        String password = "taitamashuai";
        webDriver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(userName, password);
    }

    @Test
    public void userEntry() throws InterruptedException {
        signup();
        login();
    }
}
