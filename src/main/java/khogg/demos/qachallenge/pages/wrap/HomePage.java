package khogg.demos.qachallenge.pages.wrap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Defines the structure of the site home-page as seen by a logged-out user.
 * Provides methods for loading the page and navigating to the account signup table
 *
 * Selectors are made public for assertions from test-case classes
 *
 * Created by viktor on 9/9/16.
 */
public class HomePage {
    private WebDriver driver;
    public static String pageUrl = "https://qa.wrapdev.net";

    public HomePage(WebDriver wd) {
        this.driver = wd;
    }

    public void loadPage() {
        driver.get(pageUrl);
    }

    public void clickFreeTrialButton() {
        driver.findElement(By.xpath("//li[@id='freeTrial']/a")).click();
    }
}
