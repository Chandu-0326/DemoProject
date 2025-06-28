package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Started");
		
		test = report.createTest(methodname);
	}

	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Passed");
		test.log(Status.PASS, methodname+"---Passed");
	}

	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Failed");
		test.log(Status.FAIL, methodname+"---Failed");
		test.log(Status.INFO, result.getThrowable());
		
		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		
		String screenshotname = methodname+"- "+jutil.toGetSystemDateandTime();
		
		try {
			String path = wutil.toTakesScreenshot(BaseClass.sDriver, screenshotname);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"---Skipped");
		test.log(Status.SKIP, methodname+"---Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("---Suite Execution Started");
		
		ExtentSparkReporter htmlreport = new ExtentSparkReporter("./ExtentRepots/Reports-"+new JavaUtility().toGetSystemDateandTime()+".html");
		htmlreport.config().setDocumentTitle("VTIGER EXECUTION REPORT");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("Vtiger Execution Report");
		
		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("url", "http://localhost:8888/");
		report.setSystemInfo("username", "admin");
		report.setSystemInfo("password", "pasword");
		report.setSystemInfo("Base Browser", "chrome");
		report.setSystemInfo("Reporter Name", "Chandana");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("---Suite Execution Finished");
		report.flush();
	}

}
