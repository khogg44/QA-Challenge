package khogg.demos.qachallenge;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Superclass which sets up the basics of a test using WebDriver
 * Each particular test-case should extend this class
 *
 * Created by viktor on 9/9/16.
 */
public abstract class AbstractUITest {
    private final Logger logger = Logger.getLogger(getClass());
    protected WebDriver driver;
    protected Properties settings;
    protected DesiredCapabilities caps;
    private static String settingsFile = "settings.properties";
    private static String DEFAULT_IMPLICIT_WAIT_SECONDS = "30";

    /**
     * Processes settings from a file and creates an appropriately-configured WebDriver instance
     */
    @BeforeClass
    public void testSetup() {
        settings = loadPropsFile(settingsFile);

        switch (settings.getProperty("browser").toLowerCase()) {
            case "chrome":
                caps = DesiredCapabilities.chrome();
                caps.setBrowserName("chrome");
                logger.debug("Starting ChromeDriver...");
                driver = new ChromeDriver(caps);
                break;

            case "firefox":
                FirefoxProfile ffProfile = new FirefoxProfile();
                ffProfile.setPreference("browser.startup.homepage", "about:blank");
                ffProfile.setPreference("startup.homepage_welcome_url", "about:blank");
                ffProfile.setPreference("startup.homepage_welcome_url.additional", "about:blank");

                logger.debug("Starting FirefoxDriver...");
                driver = new FirefoxDriver(ffProfile);
                break;

            default:
                logger.debug("Starting HtmlUnitDriver...");
                driver = new HtmlUnitDriver(true) {
                    @Override
                    protected WebClient newWebClient(BrowserVersion version) {
                        WebClient webClient = super.newWebClient(version);
                        webClient.getOptions().setThrowExceptionOnScriptError(false);
                        return webClient;
                    }
                };
        }
        driver.manage().timeouts().implicitlyWait(
                Long.parseLong(settings.getProperty("implicit.wait.seconds", DEFAULT_IMPLICIT_WAIT_SECONDS)),
                TimeUnit.SECONDS);
    }

    /**
     * Performs test cleanup by closing WebDriver instance
     */
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /**
     * Creates a Properties object from a file on the classpath
     * @param fileName  The .properties file containing key=value pairs
     * @return  Properties object composed from input file
     */
    private Properties loadPropsFile(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try {
            InputStream resourceStream = loader.getResourceAsStream(fileName);
            props.load(resourceStream);
        } catch (IOException ioe) {
            logger.error("Couldn't load file: " + fileName);
        }
        return props;
    }
}