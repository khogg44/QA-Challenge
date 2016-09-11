package khogg.demos.qachallenge.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * A collection of helper methods which can be used by test-cases or Page-Object methods
 *
 * Created by viktor on 9/9/16.
 */
public class BrowserInteractions {

    /**
     * In case an element needs to be clicked it may be necessary to first bring it into view
     * by scrolling down to its location.
     *
     * A JS command is employed to achieve this behavior.
     *
     * @param wd WebDriver instance through which to send the command
     * @param el the element which will be brought into view
     * @return the same element for chaining further actions
     */
    public WebElement makeVisible(WebDriver wd, WebElement el) {
        ((JavascriptExecutor) wd).executeScript("arguments[0].scrollIntoView();", el);
        return el;
    }

    /**
     * A generic timeout method used when its necessary to let the browser load a page
     * or perform other tasks whose completion is required by future actions/assertions
     *
     * @param millis the time to wait in milliseconds
     */
    public void waitWhileLoading(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ie){};
    }

    /**
     * A timeout method which returns when an element is no longer present or the maximum timeout is reached
     *
     * @param wd WebDriver instance through which to check for element staleness
     * @param el the element whose absence triggers the end of the timeout
     * @param seconds the upper bound for the wait-time
     */
    public void waitForTransitionViaStaleElement(WebDriver wd, WebElement el, int seconds) {
        new WebDriverWait(wd,seconds).until(ExpectedConditions.stalenessOf(el));
    }
}
