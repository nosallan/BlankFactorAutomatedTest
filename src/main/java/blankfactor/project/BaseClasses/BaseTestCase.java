package blankfactor.project.BaseClasses;

import blankfactor.project.EnhancedDriver.AssertElement;
import blankfactor.project.EnhancedDriver.Selenium;
import blankfactor.project.Reporting.ExtentTestManager;
import blankfactor.project.Reporting.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
//base configuration for all the tests
public class BaseTestCase {

    protected String section;
    private static String suiteName;
    protected Selenium driver;
    private String testName;
    private String baseUrl;

    private String browserOrMobileDeviceName;
    private boolean headlessMode;
    private String projectName;
    private String buildNumber;
    private boolean isWebRunnerExecution;
    private String deviceType;
    private String deviceName;

    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }

    public BaseTestCase(String browserOrMobileDeviceName, String baseUrl, String suiteName,
                        boolean headlessMode, String projectName, String buildNumber,
                        boolean isWebRunnerExecution, String deviceType) {

        this.browserOrMobileDeviceName = browserOrMobileDeviceName;
        this.baseUrl = baseUrl;
        this.suiteName = suiteName;
        this.headlessMode = headlessMode;
        this.projectName = projectName;
        this.buildNumber = buildNumber;
        this.isWebRunnerExecution = isWebRunnerExecution;
        this.deviceType = deviceType;
    }
    //add the base url, set the Suite name and the DOM before start the suite
    @BeforeSuite
    public void beforeSuite(ITestContext ctx) {
        ExtentTestManager.addSystemInfo("Environment: ", baseUrl);
        suiteName = ctx.getCurrentXmlTest().getSuite().getName() + " --> "+getClass().getSimpleName() +" - "+browserOrMobileDeviceName;
        DOMConfigurator.configure("src/main/resources/log4j.xml");
        Log.info(suiteName);
    }
    //set the class name before start the class
    @BeforeClass
    public void BeforeClass(ITestContext ctx) {
        section = ctx.getCurrentXmlTest().getSuite().getName();
        setTestName(getClass().getSimpleName());
    }
    //Starts the Thread for extent report, driver before to execute the methods and navigate to the
    // BaseURL provided
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method caller) {
        String TestName_Long = getTestName() +" - "+caller.getName()+" - "+ browserOrMobileDeviceName;
        Log.startTestCase(getTestName() +" - "+caller.getName());
        ExtentTestManager.startCurrentThreadTest(TestName_Long, getTestName() + " in "+ browserOrMobileDeviceName);
        ExtentTestManager.getExtentTest().assignCategory(section);
        driver = new Selenium( browserOrMobileDeviceName, suiteName ,headlessMode);
        driver.navigate(baseUrl);
    }
    //check if behavior is as expected
    public void assertTrue( Boolean comparison){
        AssertElement.assertTrue(driver, getTestName(), comparison);
    }
    //save a screenshot as evidence and close the browser for that the next method have a clean browser
    @AfterMethod
    public void afterMethod(ITestResult result,Method caller) {
        String imgPath = ExtentTestManager.getExtentTest().addScreenCapture(driver.getScreenshot(getTestName()));
        ExtentTestManager.getExtentTest().log(LogStatus.INFO, "Screenshot below: "+ imgPath);
        ExtentTestManager.endTest();
        Log.endTestCase(getTestName() +" - "+caller.getName());
        driver.closeBrowser();
    }
    //trying to close the browser just in case if kept open after run all the class
    @AfterClass
    public void afterClass() {
        try {
            driver.closeBrowser();
        }catch (Exception ex){}
    }
    //finish the extent report after the suite runs
    @AfterSuite
    public void afterSuite()  {
        ExtentTestManager.flush();

    }

}
