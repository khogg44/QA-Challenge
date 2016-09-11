package khogg.demos.qachallenge.pages.wrap;

import khogg.demos.qachallenge.utils.BrowserInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Defines the structure of the registration modal and
 * provides methods for navigating through the registration scenario using email & password
 *
 * The process is broken up into 3 steps
 * 3 step WebElements identify the modal state and each serve as a base for further element lookups
 * It is expected that StaleElementReferenceException(s) will serve to identify unexpected modal states as test-failures
 *
 * Selectors are made public for assertions from test-case classes
 *
 * Created by viktor on 9/9/16.
 */
public class RegisterDialog {
    private WebDriver driver;
    private BrowserInteractions util;

    private WebElement elSignupStep1 = null;
    private WebElement elSignupStep2 = null;
    private WebElement elSignupStep3 = null;

    public static String cssSignupStep1 = "wm-signup div[ng-switch-when='index']";
    public static String cssSignupStep2 = "wm-signup div[ng-switch-when='finish-signup']";
    public static String cssSignupStep3 = "wm-signup div[ng-switch-when='account-info']";

    public static String cssEmailInputField = "form input[ng-model='vm.email']";
    public static String cssSignupBtn = "form button";

    public static String cssUsernameField = "form input[ng-model='vm.username']";
    public static String cssUsernameSuggestionBtn = "form .signup_suggested-username b";
    public static String cssPasswordField = "form input[ng-model='vm.password']";
    public static String cssCreateAccountStep2Btn = "form button";

    public static String cssFirstNameField = "form input[ng-model='vm.firstName']";
    public static String cssLastNameField = "form input[ng-model='vm.lastName']";
    public static String cssCompanyField = "form input[ng-model='vm.company']";
    public static String cssPhoneField = "form input[ng-model='vm.phone']";
    public static String cssFinalizeBtn = "form button ";


    public RegisterDialog(WebDriver wd) {
        this.driver = wd;
        this.util = new BrowserInteractions();
    }

    public WebElement getElSignupStep1() {
        if (elSignupStep1 == null) {
            elSignupStep1 = driver.findElement(By.cssSelector(cssSignupStep1));
        }
        return elSignupStep1;
    }

    public WebElement getElSignupStep2() {
        if (elSignupStep2 == null) {
            elSignupStep2 = driver.findElement(By.cssSelector(cssSignupStep2));
        }
        return elSignupStep2;
    }

    public WebElement getElSignupStep3() {
        if (elSignupStep3 == null) {
            elSignupStep3 = driver.findElement(By.cssSelector(cssSignupStep3));
        }
        return elSignupStep3;
    }

    public void enterEmailClickSignup(String email) {
        WebElement emailInputField = getElSignupStep1().findElement(By.cssSelector(cssEmailInputField));
        emailInputField.sendKeys(email);
        getElSignupStep1().findElement(By.cssSelector(cssSignupBtn)).click();
        util.waitWhileLoading(1000);
    }

    public WebElement getUsernameField() {
        return getElSignupStep2().findElement(By.cssSelector(cssUsernameField));
    }

    /**
     * @return the username suggestion button so its text-content can be used in a test-case
     */
    public WebElement getUsernameSuggestionBtn() {
        return getElSignupStep2().findElement(By.cssSelector(cssUsernameSuggestionBtn));
    }

    /**
     * Clicks the username suggestion button which should fill in the field above it
     * @return the final value of the input field so it can be compared to the suggestion
     */
    public String enterDefaultUsername() {
        getUsernameSuggestionBtn().click();
        return getElSignupStep2().findElement(By.cssSelector(cssUsernameField)).getAttribute("value");
    }

    public void enterPassword(String password) {
        getElSignupStep2().findElement(By.cssSelector(cssPasswordField)).sendKeys(password);
    }

    public void clickCreateAccountOnStep2() {
        getElSignupStep2().findElement(By.cssSelector(cssCreateAccountStep2Btn)).click();
    }

    public void enterAccountInfoOnStep3AndFinalize(String firstName, String lastName, String company, String phone) {
        WebElement elStep3 = getElSignupStep3();
        elStep3.findElement(By.cssSelector(cssFirstNameField)).sendKeys(firstName);
        elStep3.findElement(By.cssSelector(cssLastNameField)).sendKeys(lastName);
        elStep3.findElement(By.cssSelector(cssCompanyField)).sendKeys(company);
        if (phone != null) {
            elStep3.findElement(By.cssSelector(cssPhoneField)).sendKeys(phone);
        }
        elStep3.findElement(By.cssSelector(cssFinalizeBtn)).click();
    }


}
