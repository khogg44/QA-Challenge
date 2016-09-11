package khogg.demos.qachallenge.pages.wrap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Defines the structure of the signup page as seen by a logged-out user and
 * provides methods for initiating a signup.
 *
 * Currently the only case covered is for a requesting a personal account on a desktop browser
 *
 * Selectors are made public for assertions from test-case classes
 * Created by viktor on 9/9/16.
 */
public class SignupPage {
    private WebDriver driver;
    public static String cssDesktopPersonalAccountSignupBtn = ".buttons-desktop a[ng-click*='plans.personal']";

    public SignupPage(WebDriver wd) {
        this.driver = wd;
    }

    public void clickPersonalSignupButton() {

        // There's a ton of these buttons in the page's html, so we have to constrain the selector
        // Since the test covers only a desktop browser we need a child of div.buttons-desktop
        WebElement signupBtn = driver.findElements(By.cssSelector(cssDesktopPersonalAccountSignupBtn)).get(0);

        signupBtn.click();
    }

}
