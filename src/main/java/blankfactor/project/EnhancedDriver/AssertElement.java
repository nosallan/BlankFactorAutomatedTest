package blankfactor.project.EnhancedDriver;

import blankfactor.project.Reporting.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AssertElement {

    /**
     * Checks for elements.
     */
    private static final String ELEMENT_NOT_PRESENT = "Element %s is not present, it contains the text:  ";
    private static final String ELEMENT_NOT_CONTAINS = "Element %s does not contain text %sm it contains %s";
    private static final String COMPARISON_TRUE = "The expected comparison is true";
    private static final String COMPARISON_FALSE = "The expected comparison is false";
    private static final String SCREENSHOT = "Screenshot below: ";

    //element is present
    public static void assertElementIsPresent(Selenium driver, String testName, By locator) {
        if(driver.isElementDisplayed(locator))
            ExtentTestManager.logAssertPass(testName);
        else {
            ExtentTestManager.logAssertFail();
            Assert.fail(String.format(ELEMENT_NOT_PRESENT, locator));
        }
        String destination = driver.getScreenshot(testName);
        ExtentTestManager.logInfo(SCREENSHOT,ExtentTestManager.getExtentTest().addScreenCapture(destination));
    }
    //element is not present
    public static void assertElementIsNotPresent(Selenium driver, String testName, By locator) {
        if(driver.isElementDisplayed(locator)){
            ExtentTestManager.logAssertFail();
            Assert.fail(String.format(ELEMENT_NOT_PRESENT, locator));
        }else
            ExtentTestManager.logAssertPass(testName);
        String destination = driver.getScreenshot(testName);
        ExtentTestManager.logInfo(SCREENSHOT,ExtentTestManager.getExtentTest().addScreenCapture(destination));
    }

    //The element contains a specific text
    public static void assertElementContains(Selenium driver, String testName, By locator, String text) {
        if(driver.contains(locator, text))
            ExtentTestManager.logAssertPass(testName);
        else{
            ExtentTestManager.logAssertFail();
            Assert.fail(String.format(ELEMENT_NOT_CONTAINS, locator));
        }
        String destination = driver.getScreenshot(testName);
        ExtentTestManager.logInfo(SCREENSHOT,ExtentTestManager.getExtentTest().addScreenCapture(destination));
    }
    //check if the result is approved
    public static void assertTrue(Selenium driver, String testName, boolean comparison) {
        if (comparison) {
            ExtentTestManager.logAssertPass(testName);
        }else{
            ExtentTestManager.logAssertFail();
            Assert.fail(String.format(COMPARISON_FALSE));
        }
        String destination = driver.getScreenshot(testName);
        ExtentTestManager.logInfo(SCREENSHOT,ExtentTestManager.getExtentTest().addScreenCapture(destination));
    }


}
