package blankfactor.project.Reporting;

import org.apache.log4j.Logger;

public class Log {

    //Initialize Log4j logs
    private static Logger Log = Logger.getLogger(Log.class.getName());//

    // This is to print log for the beginning of the main case, as we usually run so many main cases as a main suite
//public static void startTestCase(String sTestCaseName){
    public static void startTestCase(String sTestCaseName){

   Log.info("****************************STARTING TEST CASE****************************");
   Log.info("$$$$$$$$$$$$$$$$            "+sTestCaseName+ "            $$$$$$$$$$$$$$$$");
   Log.info("**************************************************************************");

    }

    //This is to print log for the ending of the main case
    public static void endTestCase(String sTestCaseName){
        Log.info("XXXXXXXXXXXXXXXXXXXXXXX "+"-END OF THE TEST: "+sTestCaseName+" XXXXXXXXXXXXXXXXXXXXXX");
       Log.info("X");


    }

    // Need to create these driverUtilities, so that they can be called
    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

}