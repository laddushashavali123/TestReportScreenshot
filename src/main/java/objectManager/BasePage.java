package objectManager;

import com.vimalselvam.cucumber.listener.Reporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerHelper;
import utils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class BasePage {
    PropertyUtils appProperty = new PropertyUtils();
    private static final Logger logger = LoggerHelper.getLogger(BasePage.class);
    String URL = appProperty.getPropertyValue("url");
    private WebDriver driver;

    protected enum BY_TYPE {

        BY_XPATH, BY_LINKTEXT, BY_ID, BY_CLASSNAME,
        BY_NAME, BY_CSSSELECTOR, BY_PARTIALLINKTEXT, BY_TAGNAME
    }


    protected By getLocator(String locator, BasePage.BY_TYPE type) {

        switch (type) {
            case BY_XPATH:
                return By.xpath(locator);

            case BY_LINKTEXT:
                return By.linkText(locator);
            case BY_ID:
                return By.id(locator);

            case BY_CSSSELECTOR:
                return By.cssSelector(locator);
            case BY_CLASSNAME:
                return By.className(locator);

            case BY_NAME:
                return By.name(locator);

            case BY_PARTIALLINKTEXT:
                return By.partialLinkText(locator);

            case BY_TAGNAME:
                return By.tagName(locator);

        }
        throw new IllegalArgumentException("Invalid By Type, Please provide correct locator type");

    }

    public WebDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        try {
            String browserName = appProperty.getPropertyValue("browser");
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    return driver;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary(appProperty.getPropertyValue("firefoxBinaryPath"));
                    driver = new FirefoxDriver(firefoxOptions);
                    return driver;
            }
        } catch (Exception e) {
            logger.error("Error initiating driver: " + e);
        }
        return driver;
    }

    /**
     * This function is to make the driver wait explicitly for a condition to be
     * satisfied
     *
     * @param locator - By object of the element whose
     *                visibility/presence/clickability has to be checked
     */
    public void addExplicitWait(WebDriver driver, By locator, String condition, int inttimeoutinseconds) {

        WebDriverWait webDriverWait = new WebDriverWait(driver, inttimeoutinseconds, 250L);
        try {
            if (condition.equalsIgnoreCase("visibility")) {
                webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
                logger.info("Driver waits explicitly until the element with" + locator.
                        toString().replace("By.", " ") + " is visible");
            } else if (condition.equalsIgnoreCase("clickable")) {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
                logger.info("Driver waits explicitly until the element with" + locator.
                        toString().replace("By.", " ") + " is clickable");
            } else if (condition.equalsIgnoreCase("presence")) {
                webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
                logger.info("Driver waits explicitly until the element with" + locator.
                        toString().replace("By.", " ") + " is present");
            } else {
                logger.error("Condition String should be visibility or clickable or presence");
            }
        } catch (NoSuchElementException e) {
            logger.error("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
            throw new NoSuchElementException("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found");
        }
    }

    /**
     * This function clicks on the element which can be located by the By Object
     *
     * @param locator - By object to locate the element to be clicked
     * @param driver  - WebDriver object
     */
    public void click(WebDriver driver, By locator) {
        try {
            addExplicitWait(driver, locator, "clickable", 1000);
            driver.findElement(locator).click();
            logger.info("Click is performed on element with"
                    + locator.toString().replace("By.", " "));
        } catch (NoSuchElementException e) {
            logger.error("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
            throw new NoSuchElementException("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found");
        }
    }


    /**
     * Below function is for launching the browser
     */
    public void launchBrowser(WebDriver driver) {
        if (URL.length() != 0) {
            try {
                driver.get(URL);
                driver.manage().window().maximize();
                logger.info(URL + " is opened successfully");
            } catch (Exception e) {
                logger.error("Unable to load the Base URL: ", e.fillInStackTrace());

            }
        } else {
            logger.error("Unable to load the Base URL: " + URL + " Please provide a valid Base URL");
        }

    }

    /**
     * Below function is for closing the browser
     */
    public void closeDriver(WebDriver driver) {
        driver.quit();
        logger.info("Url " + URL + " is closed");
    }

    /**
     * This function is to get the visible text of an element within UI
     *
     * @param locator - By object to locate the element from which the text has
     *                to be taken
     * @return String - returns the innertext of the specified element
     */
    public String getText(WebDriver driver, By locator) {
        String text = null;
        try {
            logger.info("The value on the field with"
                    + locator.toString().replace("By.", " ")
                    + " is obtained");
            text = driver.findElement(locator).getText();
        } catch (NoSuchElementException e) {
            logger.error("Unable to get the text. The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
            throw new NoSuchElementException("Unable to get the text. The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found");
        }
        return text;
    }

    /**
     * This function is to select a dropdown option using its index
     *
     * @param locator - By object to locate the dropdown which is to be selected
     * @param index   - index of the dropdown option to be selected
     */
    public void selectDropDownByIndex(By locator, int index) {
        try {
            Select dropDown = new Select(driver.findElement(locator));
            dropDown.selectByIndex(index);
            logger.info("The dropdown option with index: " + index
                    + " is selected");
        } catch (NoSuchElementException e) {
            logger.error("Unable to select the dropdown; The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
            throw new NoSuchElementException("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found");
        }
    }

    /**
     * This function is to select the dropdown options that have a value
     * matching the argument
     *
     * @param locator - By object to locate the dropdown which is to be selected
     * @param value   - value to match against the dropdown option to be selected
     */
    public void selectDropDownByValue(By locator, String value) {
        try {
            Select dropDown = new Select(driver.findElement(locator));
            dropDown.selectByValue(value);
            logger.info("The dropdown option with value: " + value
                    + " is selected");
        } catch (NoSuchElementException e) {
            logger.error("Unable to select the dropdown; The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
            throw new NoSuchElementException("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found");
        }
    }

    /**
     * This function is used to assert the expected text in UI
     */
    public void AssertText(WebDriver driver, String text) {
        try {
            Assert.assertTrue(driver.getPageSource().contains(text));
            logger.info("" + text + " has been verified successfully in current page");
        } catch (NoSuchElementException e) {
            logger.info("" + text + " not displayed");
            java.util.logging.Logger.getLogger(BasePage.class.getName()).log(Level.SEVERE, "" + text + " not asserted", e);

        }
    }


    public void failIfElementNotVisible(WebDriver driver, By locator, String locatorName) {
        try {
            boolean isElementVisible = driver.findElement(locator).isDisplayed();
            Assert.assertTrue(isElementVisible);
            Reporter.addStepLog("Element " + locatorName + " is visible.");
            logger.info("The element with "
                    + locator.toString().replace("By.", " ") + " is visible");
        } catch (NoSuchElementException e) {
            logger.error("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
        }
    }

    /**
     * This function is to pass a text into an input field within UI
     *
     * @param locator     - By object to locate the input field to which text has to
     *                    be send
     * @param value       - Text value which is to be send to the input field
     * @param locatorName - Name of the locator to declared. i.e., Name of the
     *                    locator_Button,Name of the locator_Link,etc
     */
    public void type(WebDriver driver, By locator, String value, String locatorName) {
        try {

            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(value);
            Reporter.addStepLog("User is able to enter " + value + " into " + locatorName + " successfully");
            logger.info("String " + value + " is send to element with"
                    + locator.toString().replace("By.", " "));
        } catch (NoSuchElementException e) {
            logger.error("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
            throw new NoSuchElementException("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found");
        }
    }

    /**
     * This function is to select the dropdown options that have a value
     * matching the argument
     *
     * @param locator - By object to locate the dropdown which is to be selected
     * @param value   - value to match against the dropdown option to be selected
     */
    public void selectDropDownByVisibleText(WebDriver driver, By locator, String value) {
        try {
            Select dropDown = new Select(driver.findElement(locator));
            dropDown.selectByVisibleText(value);
            Reporter.addStepLog("User is able to select the dropdown option with value: " + value
                    + " successfully");
            logger.info("The dropdown option with value: " + value
                    + " is selected");
        } catch (NoSuchElementException e) {
            logger.error("Unable to select the dropdown; The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found", e.fillInStackTrace());
            throw new NoSuchElementException("The element with"
                    + locator.toString().replace("By.", " ")
                    + " not found");
        }
    }

    public List<String> getContentFromSearchResultTable(WebDriver driver, By table, String locatorName) {
        try {
            List<String> searchContent = new ArrayList<>();
            WebElement Table = driver.findElement(table);
            List<WebElement> allOptions = Table.findElements(By.tagName("td"));
            for (WebElement we : allOptions) {
                searchContent.add(we.getText());
            }
            return searchContent;
        } catch (NoSuchElementException e) {
            logger.error("selectFromTable: Unable to find the element: ", e.fillInStackTrace());
            throw new NoSuchElementException("The element with"
                    + locatorName + " not found");
        }
    }
}