package khogg.demos.qachallenge;

import khogg.demos.qachallenge.pages.wrap.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

/**
 * A test-case written in response to the following request:
 * @see https://wrap.co/wraps/edbd20f5-85ea-400b-a92b-c3b65e9726f8
 *
 * The requirements were outlined as follows:
 *
 * 1. Register for a personalized account
 * 2. Create a wrap from a template
 * 3. Publish the wrap
 *
 * Created by viktor on 9/9/16.
 */
public class CreateUserPublishWrapTest extends AbstractUITest {
    protected final Logger logger = Logger.getLogger(getClass());

    @Test
    public void createUserAndPublishWrap() {
        logger.info("Test-case: Create Wrap user and publish a wrap using a template");

        logger.info("1. Go to the Wrap site");
        HomePage homePage = new HomePage(driver);
        homePage.loadPage();

        logger.info("2. Click free trial button");
        homePage.clickFreeTrialButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("/plans"),
                "Failed to verify presence of plans table via url");

        logger.info("3. Click sign up button in personal column");
        new SignupPage(driver).clickPersonalSignupButton();

        RegisterDialog regDialog = new RegisterDialog(driver);
        Assert.assertNotNull(regDialog.getElSignupStep1(),
                "Failed to verify registration dialog appearance at step 1");

        logger.info("4. Provide email and continue");
        regDialog.enterEmailClickSignup("khogg44@yahoo.com");
        Assert.assertNotNull(regDialog.getElSignupStep2(),
                "Failed to verify the signup flow transitioned to step 2");

        logger.info("5. Click username suggestion button and make sure suggestion is filled in");
        Assert.assertTrue(regDialog.getUsernameSuggestionBtn().getText().equals(regDialog.enterDefaultUsername()),
                "Failed to verify that suggested username was entered upon clicking the button");

        logger.info("6. Enter the password and click the button to continue");
        regDialog.enterPassword("Challenge1");
        regDialog.clickCreateAccountOnStep2();
        Assert.assertNotNull(regDialog.getElSignupStep3(),
                "Failed to verify the signup flow transitioned to step 3");

        logger.info("7. Fill in the rest of the account info, finalize the account and arrive on authoring page logged-in");
        regDialog.enterAccountInfoOnStep3AndFinalize("Viktor", "R", "Wrap Media", null);
        Assert.assertNotNull(driver.findElement(By.cssSelector(WrapsPage.cssNewWrapBtn)),
                "Failed to verify arriving on the authoring page logged-in");

        logger.info("8. Create a new wrap from a template in 'Other' category and publish it");
        WrapsPage wrapsPage = new WrapsPage(driver);
        wrapsPage.clickNewWrapsBtn();
        Assert.assertNotNull(driver.findElement(By.cssSelector(WrapsPage.cssCreateNewWrapModal)),
                "Failed to find modal 'Create a New Wrap' after button click");

        wrapsPage.clickOtherTemplateCategory();
        Assert.assertEquals("Other", driver.findElement(By.cssSelector(WrapsPage.cssActiveTemplateCategoryTab)).getText(),
                "Failed to verify that Other category was selected");
        Assert.assertNotNull(driver.findElement(By.cssSelector(WrapsPage.cssTemplateSubcategories)),
                "Failed to verify that template subcategories appeared");

        wrapsPage.clickFirstCreateWrapBtn();
        Assert.assertNotNull(driver.findElement(By.cssSelector(WrapsPage.cssWrapEditor)),
                "Failed to verify landing in wrap-editor");

        wrapsPage.clickQuickTourCloseBtn();
        wrapsPage.clickPublishBtn();
        wrapsPage.waitForPublishSuccess();
        wrapsPage.clickPublishSuccessTourCloseBtn();
        Assert.assertNotNull(wrapsPage.getPublishSuccessNotification(),
                "Failed to verify publish success via publish notification modal");
    }
}
