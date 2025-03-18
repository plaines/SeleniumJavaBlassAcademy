package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.FileManager;
import utilities.Logs;

public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Logs.info("Comenzando el test %s%n", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.info("Test exitoso %s%n", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.info("Test fallido %s%n", result.getName());
        FileManager.getScreenshot(result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.info("Test ignorado %s%n", result.getName());
    }
}
