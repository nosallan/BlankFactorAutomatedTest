package listeners;

import blankfactor.project.DriverUtilities.Utilities;
import blankfactor.project.Reporting.Log;
import clientProperties.ClientProperties;
import org.testng.IExecutionListener;

public class chromeExecution implements IExecutionListener {
    private long startTime;
    @Override
    public void onExecutionStart() {
        Log.info("AUTO IT - Started");
        ClientProperties.BROWSER = "chrome";
        startTime = System.currentTimeMillis();
        Log.info("Set chrome browser");
    }
    @Override
    public void onExecutionFinish() {
        Log.info("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
        Log.info("HTML Report has been saved on: " + Utilities.getReportFolder());
        Log.info("AUTO IT - Finished");
    }
}
