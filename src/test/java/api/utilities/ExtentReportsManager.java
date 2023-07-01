package api.utilities;


	
	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
	import org.testng.TestListenerAdapter;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.markuputils.ExtentColor;
	import com.aventstack.extentreports.markuputils.MarkupHelper;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager implements ITestListener{

		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		
		public void onStart(ITestContext testContext) {
			
			String timestamp = new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String repName = "Test-Report-"+timestamp+".html";
			
			sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
			sparkReporter.config().setReportName("Pet Store Uses API");
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Pet Store Uses API");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("user", "Shubham_Amale");			
			
		}
		
		public void onTestSuccess(ITestResult tr)
		{
			test=extent.createTest(tr.getName());
			test.assignCategory(tr.getMethod().getGroups());
			test.createNode(tr.getName());
			test.log(Status.PASS, "Test Passed");
		}
		
		public void onTestFailure(ITestResult tr) {
			test=extent.createTest(tr.getName());
			test.assignCategory(tr.getMethod().getGroups());
			test.createNode(tr.getName());
			test.log(Status.FAIL, "Test Failed");
			test.log(Status.FAIL, tr.getThrowable().getMessage());
			
		}
		
		public void onTestSkipped(ITestResult tr) {
			test=extent.createTest(tr.getName());
			test.assignCategory(tr.getMethod().getGroups());
			test.createNode(tr.getName());
			test.log(Status.SKIP, "Test Skipped");
			test.log(Status.SKIP, tr.getThrowable().getMessage());
			
		}
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}
	}

