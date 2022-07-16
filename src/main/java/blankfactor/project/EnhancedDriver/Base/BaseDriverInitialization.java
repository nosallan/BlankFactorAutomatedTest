package blankfactor.project.EnhancedDriver.Base;

import blankfactor.project.Reporting.ExtentTestManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import blankfactor.project.Reporting.Log;

//initialization of the driver
public class BaseDriverInitialization {
    private static WebDriver driverSub;
    static DesiredCapabilities capability;
    static Actions actions;
    //apply the configuration provided to can run the test on different drivers (chrome, firefox)
    public static WebDriver initializeBrowser(String browser, String suiteName, boolean headlessMode) {
        Log.info("Opening browser");
        ExtentTestManager.setDevice("Desktop",browser);
        capability = new DesiredCapabilities();
        capability.setCapability("name", suiteName);
        try {
            switch (browser.toLowerCase()){
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setHeadless(headlessMode);
                    driverSub = new FirefoxDriver(firefoxOptions);
                    Log.info("Firefox browser started");
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if(headlessMode)
                        chromeOptions.addArguments("--headless", "--window-size=1920,1200");
                    driverSub = new ChromeDriver(chromeOptions);
                    Log.info("Chrome browser started");
                    break;
            }
            int implicitWaitTime = 10;
            driverSub.manage().timeouts()
                    .implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
            driverSub.manage().timeouts().pageLoadTimeout(implicitWaitTime, TimeUnit.SECONDS);
            actions = new Actions(driverSub);
            driverSub.manage().window().maximize();
            Thread.sleep(1000);
        }
        catch (Exception e) {
            Log.error("Catch - Not able to open browser --- " + e.getMessage());
            System.out.println("Catch - Not able to open browser --- Web Driver  " + e.getMessage());
        }
        return driverSub;
    }
}
