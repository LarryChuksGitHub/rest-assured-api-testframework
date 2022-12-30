package report;

import com.aventstack.extentreports.ExtentTest;

public class ExtentThreadLocalManager {

    static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    static void setextentTestThreadLocal(ExtentTest extentTest){
        extentTestThreadLocal.set(extentTest);
    }

    static ExtentTest getextentTestThreadLocal(){
        //return ExtentReport.getTestReport();

       return extentTestThreadLocal.get();
    }

}
