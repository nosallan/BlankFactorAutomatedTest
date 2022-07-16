package blankfactor.project.DriverUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
//different utilities that helps on the code
public class Utilities {
    public static String reportFolder;

    //set the folder where we are going to have the evidence and logs
    public static void setReportFolder(String reportFolder) {
        Utilities.reportFolder = reportFolder;
    }
    //obtain folder
    public static String getReportFolder() {
        return reportFolder;
    }
    //get the current date with year, month, day, hour, minutes and seconds
    public static  String getCurrentCompleteDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();
        String currentDate=dateFormat.format(date);
        setReportFolder(currentDate);
        return currentDate;
    }
    //get the current date with year, month and day
    public static  String getCurrentDate(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
    //obtains a random Number between a range
    public static int getRandomNumber( int min, int max){
        return new Random().nextInt((max - min) + 1) + min;
    }
}
