package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import report.ExtentLogger;
import report.ExtentReport;

import java.lang.reflect.Method;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite(){
       // ExtentReport.initReport();


    }

    @AfterSuite
    public void AfterSuite(){
       // ExtentReport.publishReport();

    }

    @BeforeMethod
    public void beforeMethod(Method method){
      // ExtentReport.createReport(method.getName());

    }

    @AfterMethod
    public void teardown(ITestResult iTestResult, Method method){
        if (iTestResult.getStatus() == ITestResult.FAILURE){
            //ExtentReport.getTestReport().fail("The test: " +method.getName() + " failed! The error meesage: " + iTestResult.getThrowable());
           //ExtentLogger.logFailResponse("The test: " +method.getName().toString() + " failed! The error meesage: " + iTestResult.getThrowable().toString());
            // ExtentLogger.fail(method.getName() +"Test failed");

        }
    }

}
