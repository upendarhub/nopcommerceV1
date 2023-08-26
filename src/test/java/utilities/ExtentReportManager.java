package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import freemarker.template.SimpleDate;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    String repName;
    public void onStart(ITestContext context){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        repName = context.getName()+"_"+timeStamp;

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//"+repName);
        sparkReporter.config().setDocumentTitle("nopcommerce Automation Report");
        sparkReporter.config().setReportName("nopcommerce Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application","nopcommerce.com");
        extentReports.setSystemInfo("Module","Admin");
        extentReports.setSystemInfo("Sub-Module","Customers");
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name",System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment","QA");
    }
    public void onTestStart(ITestResult result){
//        extentTest = extentReports.createTest(result.getName());
//        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result){
//        extentTest = extentReports.createTest(result.getName());
        extentTest = extentReports.createTest(result.getName());
        test.set(extentTest);
        test.get().log(Status.PASS,"Test Case Passed is : "+result.getName());
    }
    public void onTestFailure(ITestResult result){
//        extentTest = extentReports.createTest(result.getName());
        extentTest = extentReports.createTest(result.getName());
        test.set(extentTest);
        test.get().log(Status.FAIL,"Test Case Failed is : "+result.getName());
        test.get().log(Status.FAIL,"Test Case Failed Cause is : "+result.getThrowable().getMessage());

        try {
            String imagePath = new BaseClass().takeScreenShot(result.getName());
            test.get().addScreenCaptureFromPath(imagePath);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result){
//        extentTest = extentReports.createTest(result.getName());
        test.get().log(Status.SKIP,"Test Case Skipped is : "+result.getName());
        test.get().log(Status.SKIP,"Test Case Skippes Cause is : "+result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context){
        extentReports.flush();
    }
}
