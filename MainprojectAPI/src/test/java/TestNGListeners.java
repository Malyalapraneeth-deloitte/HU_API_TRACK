import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class TestNGListeners extends Report implements ITestListener {
    ExtentReports extentReport=ReportGenerator();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test=extentReport.createTest(result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS,"Successful");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test.log(Status.FAIL,result.getThrowable());
    }
    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extentReport.flush();
    }}
