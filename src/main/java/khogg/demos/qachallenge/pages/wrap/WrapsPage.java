package khogg.demos.qachallenge.pages.wrap;

import khogg.demos.qachallenge.utils.BrowserInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Defines the structure of the Wraps home-page as seen by a logged-in user and
 * provides methods for navigating through the wrap creation and publishing scenario
 *
 * Selectors are made public for assertions from test-case classes
 *
 * Created by viktor on 9/10/16.
 */
public class WrapsPage {
    private WebDriver driver;
    private BrowserInteractions util;

    public static String urlWraps = "https://authoring.qa.wrapdev.net/#/wraps";
    public static String cssNewWrapBtn = ".wraps button.wraps_create-btn";
    public static String cssCreateNewWrapModal = "wm-wrap-settings";

    public static String cssTemplateCategory = "wm-template-layout-manager .template-categories_category";
    public static String xpathOtherTemplateCategory =
            "//wm-template-layout-manager"+
            "//div[@class='template-categories_category']"+
            "[.//div[@class='template-categories_category-name'][contains(text(),'Other')]]";

    public static String cssActiveTemplateCategoryTab = ".template-layout-manager_subcategories wm-vertical-tabs .o-tab.is-active";
    public static String cssTemplateSubcategories = ".template-layout-manager_subcategories wm-template-subcategories";
    public static String cssCreateWrapBtn = "wm-card-group button.btn-success";

    public static String cssWrapEditor = "wm-wrap-editor";
    public static String urlWrapEditorFragment = "/design/help?type=AUTHORING";
    public static String cssQuickTourCloseBtn = ".help-tour[type='AUTHORING'] button.help-tour_nav--close";
    public static String cssPublishBtn = "wm-wrap-editor .action-bar .action-bar_item button.btn-success";
    public static String cssPublishSuccessTourCloseBtn = ".modal-dialog .help-tour[type='PUBLISH'] button.help-tour_nav--close";
    public static String urlPublishSuccessFragment = "/design/help?type=PUBLISH";
    public static String cssPublishSuccessNotification = ".publish-notification";

    public WrapsPage(WebDriver wd) {
        this.driver = wd;
        this.util = new BrowserInteractions();
    }

    public void loadWrapsPage() {
        driver.get(urlWraps);
    }

    private WebElement getNewWrapsBtn() {
        // This button should be in view near the top of the page, but we need to wait while content loads
        return new WebDriverWait(driver,5)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssNewWrapBtn)));
    }

    public void clickNewWrapsBtn() {
        getNewWrapsBtn().click();
    }

    private WebElement getOtherTemplateCategory() {
        WebElement elOtherCategory = driver.findElement(By.xpath(xpathOtherTemplateCategory));
        return elOtherCategory;
    }

    public void clickOtherTemplateCategory() {
        WebElement elOtherCategory = getOtherTemplateCategory();
        if (! elOtherCategory.isDisplayed()) {
            util.makeVisible(driver,elOtherCategory);
        }
        elOtherCategory.click();
    }

    public void clickFirstCreateWrapBtn() {
        driver.findElement(By.cssSelector(cssCreateWrapBtn)).click();
    }

    public void clickQuickTourCloseBtn() {
        driver.findElement(By.cssSelector(cssQuickTourCloseBtn)).click();
    }

    public void clickPublishBtn() {
        driver.findElement(By.cssSelector(cssPublishBtn)).click();
    }

    public void waitForPublishSuccess() {
        // Publishing can take a very long time.
        // We'll wait for 30s and hope that the implicit wait time covers the rest of the process
        util.waitWhileLoading(30000);
    }

    public void clickPublishSuccessTourCloseBtn() {
        driver.findElement(By.cssSelector(cssPublishSuccessTourCloseBtn)).click();
    }

    public WebElement getPublishSuccessNotification() {
        return driver.findElement(By.cssSelector(cssPublishSuccessNotification));
    }
}
