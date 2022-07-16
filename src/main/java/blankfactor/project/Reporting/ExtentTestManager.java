package blankfactor.project.Reporting;

import blankfactor.project.DriverUtilities.Utilities;
import blankfactor.project.EnhancedDriver.FrameworkProperties;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * Extent test functionalities.
 */
public class ExtentTestManager {
    static ExtentTest test;
    public static String htmlFileName = FrameworkProperties.htmlFolder +"\\"+ Utilities.getCurrentDate()
            +"\\"+Utilities.getCurrentCompleteDate()+"\\report_"+Utilities.getReportFolder()+".html";
    private static ExtentReports extent = new ExtentReports(htmlFileName);
    //flush the extent report
    public static void flush(){
        extent.flush();
    }
    //se device of the extent report
    public static String setDevice(String device, String browser) {
        addSystemInfo("Browsers Tested", browser);
        return device;
    }
    //end the extent report
    public static synchronized void endTest() {
        extent.endTest(test);
    }
    //log information on the report
    public static synchronized void logInfo(String info, String details){
        Log.info(info+" : "+details);
        getExtentTest().log(LogStatus.INFO, info, details);
    }
    //change the name of the test
    public static synchronized void changeName(String nameTest){
        Log.info("Name of the test changed to: "+nameTest);
        getExtentTest().getTest().setName(nameTest);
    }
    //log an URL on the report
    public static synchronized void logInfoURL(String info, String details){
        Log.info(info+" : "+details);
        String detailsLog="<a href=\"" + details + "\" target=\"_blank\">" +details +"</a>";
        getExtentTest().log(LogStatus.INFO, info, detailsLog);
    }
    //log a warning in the report
    public static void logWarning(String message, String details){
        Log.warn(message+" : "+details);
        getExtentTest().log(LogStatus.WARNING, message, details);
    }
    //log an error in the report
    public static void logError(String message, String details){
        Log.error(message+" : "+details);
        getExtentTest().log(LogStatus.FAIL, message, details);
    }
    //log a fatal error in the report
    public static void logFatal(String message){
        Log.fatal(message);
        getExtentTest().log(LogStatus.FATAL, message);
    }
    //accept the test
    public static void logAssertPass(String details){
        Log.info("Test passed, " + details);
        getExtentTest().log(LogStatus.PASS, details, "<span class='label success'> Test passed </span>");
    }
    //mark has fail
    public static void logAssertFail(){
        Log.fatal("Test failed");
        getExtentTest().log(LogStatus.FAIL, " <span class='label failure'> Test failed </span>");
    }
    //mark the test as skipped
    public static void logTestSkipped(){
        Log.info("Test Skipped");
        getExtentTest().log(LogStatus.SKIP, " <span class='label failure'> Test skipped </span>");
    }
    //start the report thread
    public static synchronized ExtentTest startCurrentThreadTest(String testName, String desc) {
        test = extent.startTest(testName, desc);
        return test;
    }
    //obtains the report
    public static synchronized ExtentTest getExtentTest(){
        return test;
    }
    //add system information on the report
    public static void addSystemInfo(String param, String value){
        Log.info("Added System Info: "+param+" = "+value);
        extent.addSystemInfo(param, value);
    }

}
