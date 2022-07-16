package pages;

import blankfactor.project.BaseClasses.BaseTestCase;
import clientProperties.ClientProperties;

/**
 * Base to execute the test cases, extents the base test of the framework (blankfactor) folder
 * Takes all the parameters from the client properties
 */
public class ExecutionBaseTestCase  extends BaseTestCase {
    public ExecutionBaseTestCase() {
        super(ClientProperties.BROWSER,
                ClientProperties.BASE_URL, ClientProperties.SUITE_NAME, ClientProperties.HEADLESS_MODE,
                ClientProperties.SOURCE_PROJECT_NAME, ClientProperties.SOURCE_BUILD_NAME,
                ClientProperties.IS_WEB_RUNNER_EXECUTION,
                 ClientProperties.TYPE_OF_DEVICE);
    }
}
