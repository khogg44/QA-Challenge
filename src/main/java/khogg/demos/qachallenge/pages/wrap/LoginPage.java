package khogg.demos.qachallenge.pages.wrap;

import khogg.demos.qachallenge.utils.BrowserInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Defines the structure of the login modal/page and
 * provides methods for logging in using email/password combo
 *
 * Selectors are made public for assertions from test-case classes
 *
 * Created by viktor on 9/10/16.
 */
public class LoginPage {
    private WebDriver driver;
    private BrowserInteractions util;
    private WebElement loginDialog;

    public static String urlLogin = "https://authoring.qa.wrapdev.net/#/login";
    public static String successUrlFragment = "/#/wraps?teamId=";

    public LoginPage(WebDriver wd) {
        this.driver = wd;
        this.util = new BrowserInteractions();
    }

    public void loadPage() {
        driver.get(urlLogin);
        loginDialog = driver.findElements(By.cssSelector("wm-auth-page div.auth")).get(0);
    }

    public void loginUsingEmailPassword(String email, String password) {
        WebElement emailField = driver.findElement(By.cssSelector(".login form input[ng-model='vm.emailOrUsername']"));
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.cssSelector(".login form input[ng-model='vm.password']"));
        passwordField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector(".login form button"));
        loginBtn.click();
        util.waitForTransitionViaStaleElement(driver,loginDialog,5);

    }
}
