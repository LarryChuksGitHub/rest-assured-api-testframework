package listeners;

import annotations.FrameworkAnnotation;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;
import org.testng.annotations.Test;
import report.ExtentLogger;
import report.ExtentReport;
import utils.JiraUtils;

import java.lang.reflect.Method;

public class FrameworkListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReport();

    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.publishReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createReport(result.getName());
       String [] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).assignAuthor();
        String [] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).assignCategory();
       ExtentLogger.assignAuthor(authors);
      ExtentLogger.assignAuthor(categories);
      String testDescription = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
        ExtentLogger.assignTestDescription(testDescription);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
       ExtentLogger.pass(result.getName()+ " is passed!");

    }

    @Override
    public void onTestFailure(ITestResult result) {
       //ExtentLogger.fail("The test: " + result.getName() + " failed! The error message: " + result.getThrowable());
        var jiraKey = JiraUtils.createIssueInJira(result.getName(), result.getThrowable().toString());
        ExtentLogger.fail("The test: " + result.getName() +" failed! error message: "+ result.getThrowable()  + " Issues created: " + "http://localhost:8080/browse/"+jiraKey);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getTestName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

        this.onTestFailure(result);
    }


}
