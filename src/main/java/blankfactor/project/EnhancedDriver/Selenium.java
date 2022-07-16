package blankfactor.project.EnhancedDriver;

import blankfactor.project.DriverUtilities.Utilities;
import blankfactor.project.EnhancedDriver.Base.BaseDriverInitialization;
import blankfactor.project.Reporting.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.List;

public class Selenium {
    private static WebDriver driver = null;
    private static WebDriverWait wait;
    private static long timeInSeconds = 5;
    //Initialize the driver
    public Selenium( String browserOrMobileDeviceName, String suiteName,boolean headlessMode) {
        driver = BaseDriverInitialization.initializeBrowser(browserOrMobileDeviceName,suiteName, headlessMode);
    }
    //Initialize the object of the page
    public void initElements(Object page){
        PageFactory.initElements(this.driver, page);
    }
    //Waits using JS functionality to check if the document is ready
    public void waitForLoad() {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>)
                wd ->((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    //Navigate to a specific URL
    public void navigate(String URL) {
        try {
            ExtentTestManager.logInfoURL("Navigating to URL" , URL);
            driver.get(URL);
            waitForLoad();
        }
        catch (Exception e) {
            ExtentTestManager.logError("Catch - Not able to navigate --- " + e.getMessage(), "");
        }
    }
    //Close the browser
    public void closeBrowser(){
        try {
            driver.quit();
        } catch (Exception e) {

        }
    }
    //Save a screenshot for evidence
    public String getScreenshot(String testName)  {
        String screenshotFile="screenshots\\" + testName + "-" + Utilities.getRandomNumber(1000, 9999) + ".png";
        String destination = FrameworkProperties.htmlFolder + "\\" + Utilities.getCurrentDate() +"\\"+Utilities.getReportFolder()
                +"\\"+ screenshotFile;
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
        }
        catch (Exception ex) {
            System.out.println("ex " + ex.getMessage());
        }
        return screenshotFile;
    }
    //Check if a specific element is displayed on the page
    public boolean isElementDisplayed(By locator) {
        try {
            boolean element = driver.findElement(locator).isDisplayed();
            if (element) {
                ExtentTestManager.logInfo("Is Element Displayed? " + locator,"Yes, It is displayed");
                return true;
            }
            else {
                ExtentTestManager.logInfo("Is Element Displayed? " + locator,"No, It is not displayed");
                return false;
            }
        } catch (Exception e) {
            ExtentTestManager.logInfo("Is Element Displayed? " + locator,"No, It is not displayed");
            return false;
        }
    }
    //do a click to a specific element
    public void click(By locator){
        try {
            WebElement element = driver.findElement(locator);
            ExtentTestManager.logInfo("Clicking on web element", locator.toString());
            element.click();

        } catch (Exception e) {
            //this is for the latest versions of chrome, on some cases do correctly the click but
            //shows an "Unknown error"
            if(!e.toString().contains("unknown error:"))
                ExtentTestManager.logError("Not able to click, element not found or may not be visible: " +
                    locator,ExtentTestManager.getExtentTest().addScreenCapture(getScreenshot("Failed clicking")));
        }
    }
    public void clickOver(By locator1, By locator2){
        try{
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(locator1);
            action.moveToElement(we).perform();
            waitFor(2);
            WebElement we2 = driver.findElement(locator2);
            action.moveToElement(we2).perform();
            waitFor(2);
            action.click().perform();
            waitFor(2);
        }
        catch (Exception e) {
            if(!e.toString().contains("unknown error"))
            ExtentTestManager.logError("Not able to click, element not found or may not be visible: " +
                    locator2,ExtentTestManager.getExtentTest().addScreenCapture(getScreenshot("Failed clicking")));
        }

    }
    //obtains the current URL of the browser
    public String getCurrentPage() {
        String currentPage = driver.getCurrentUrl();
        ExtentTestManager.getExtentTest().log(LogStatus.INFO,"Getting current URL", currentPage);
        return currentPage;
    }
    //obtains the current title of the browser
    public String getTitle() {
        String pageTitle = "";
        try {
            pageTitle = driver.getTitle();
            ExtentTestManager.logInfo("Getting Title", driver.getTitle());
        } catch (Exception e) {
            ExtentTestManager.logError("Error getting Title", ExtentTestManager.getExtentTest().addScreenCapture(getScreenshot("Failed getting title")));
        }
        return pageTitle;
    }//get a element
    public WebElement getElement(By locator){
        try{
            return driver.findElement(locator);
        } catch (Exception e) {
            ExtentTestManager.logError("No element found using the given locator, error due to System Lag/UI Change",
                    ExtentTestManager.getExtentTest().addScreenCapture(getScreenshot("Element not found")));
        }
        return null;
    }
    //get different elements with the same characteristic
    public List<WebElement> getElements(By locator){
        try{
            return driver.findElements(locator);
        } catch (Exception e) {
            ExtentTestManager.logError("No element found using the given locator, error due to System Lag/UI Change",
                    ExtentTestManager.getExtentTest().addScreenCapture(getScreenshot("Elements not found")));
        }
        return null;
    }
    //fill a text field with a specific data
    public void sendKeys(By locator, String data){
        try {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(data);
            ExtentTestManager.logInfo("Data was entered!", data);
        } catch (Exception e) {
            ExtentTestManager.logError("Not able to enter data in " + locator + ", element not found or may not be ready for use"
                    , ExtentTestManager.getExtentTest().addScreenCapture(getScreenshot("Failed sending data")));
        }
    }
    //Sleeps the thread for X seconds
    public void waitFor(Integer seconds) {
        try {
            Thread.currentThread();
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Check if an element contains a specific text
    public boolean contains(By locatorText, String expectedText){
        String textContainedByElement;
        try {
            textContainedByElement = driver.findElement(locatorText).getText().trim();
            if (textContainedByElement.contains(expectedText)) {
                ExtentTestManager.logInfo("Contains? " + textContainedByElement + " - and -  " + expectedText + "(expected) of locator: "+locatorText, "Yes, it Contains");
                return true;
            } else {
                ExtentTestManager.logInfo("Contains? " + textContainedByElement + " - and -  " + expectedText
                        + "(expected) of locator: "+locatorText, "No, it does not Contain /Text:" + textContainedByElement + " /Expected: " + expectedText);
                return false;
            }
        } catch (Exception e) {
            ExtentTestManager.logError("The element you are looking for:  " + locatorText,ExtentTestManager.getExtentTest().addScreenCapture(getScreenshot(" Was not found")));
            return false;
        }
    }


}
