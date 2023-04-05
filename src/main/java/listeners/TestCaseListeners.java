package listeners;

import base.PredefinedActions;
import constants.Filepath;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;
import utility.PropertyUtils;

public class TestCaseListeners extends PredefinedActions implements ITestListener {

    String browser, env;

    public void onTestStart(ITestResult result) {
        ExtentManager.createTestCase(result);
        startBrowser(browser, env);
        ExtentManager.log("STEP: Test Case about to start" + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        ExtentManager.testSuccess(result);
        closeBrower();
    }

    public void onTestFailure(ITestResult result) {
        ExtentManager.testFail(result);
        closeBrower();
    }

    public void onTestSkipped(ITestResult result) {
        ExtentManager.testSkip(result);
        closeBrower();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        browser = System.getProperty("browserType") == null ? PropertyUtils.getValue(Filepath.CONFIGPATH, "browserType") : System.getProperty("browserType");
        env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        ExtentManager.initExtentReports();
    }

    public void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }

}
