package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.FrameworkConstants;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.File;

public final class ExtentReport {
    private ExtentReport(){}

    private static ExtentReports reports;
    private static ExtentSparkReporter spark;
    private static ExtentTest createTest;

     static ExtentTest getTestReport(){
        return createTest;
    }
    static void setCreateTest(ExtentTest createTest) {
        ExtentReport.createTest = createTest;
    }

    public static void initReport(){
       reports = new  ExtentReports();
       spark = new ExtentSparkReporter(FrameworkConstants.getInstance().getGetReportOutputPath());
       spark.config().setTheme(Theme.DARK);
       spark.config().setDocumentTitle("Test Title");
       spark.config().setReportName("The report name");
       reports.attachReporter(spark);

    }

    public static void createReport(String message){
        //configureReport();
        createTest = reports.createTest(message);
        ExtentThreadLocalManager.setextentTestThreadLocal(createTest);
        createTest = ExtentThreadLocalManager.getextentTestThreadLocal();
    }

    @SneakyThrows
    public static void publishReport(){
        System.out.println("publishing");
        reports.flush();
        Desktop.getDesktop().browse(new File(FrameworkConstants.getInstance().getGetReportOutputPath()).toURI());
        ExtentThreadLocalManager.extentTestThreadLocal.remove();
    }

    @SneakyThrows
    public static void main(String[] args) {
       // getTestReport("pass method").pass("Passed");
       // getTestReport("fail method").fail("failed");
       // getTestReport("Skip method").fail("skipped");
        //getTestReport("info method").fail("info");
        publishReport();
        Desktop.getDesktop().browse(new File(FrameworkConstants.getInstance().getGetReportOutputPath()).toURI());
        ExtentThreadLocalManager.extentTestThreadLocal.remove();
    }



}
