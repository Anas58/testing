package utilities;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.testng.ITestResult;

public class TestListener extends ExtentITestListenerClassAdapter {

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTestManager.createMethod(result, false);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentTestManager.log(result, false);
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentTestManager.log(result, false);
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ExtentTestManager.log(result, false);
    }

}