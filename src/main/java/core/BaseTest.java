package core;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import utils.ExtentReport;

public class BaseTest {
	
	@AfterMethod(alwaysRun = true)

	public void getResult(ITestResult result) {

		

		if (result.getStatus() == ITestResult.SUCCESS) {

			ExtentReport.extentlog.log(Status.PASS, "Test Case : "+ result.getName()+" is passed ");



		} else if (result.getStatus() == ITestResult.FAILURE) {

			ExtentReport.extentlog.log(Status.FAIL, "Test case : "+ result.getName()+" is failed ");

			ExtentReport.extentlog.log(Status.FAIL, "Test case is failed due to:  " + result.getThrowable());

			

		} else if (result.getStatus() == ITestResult.SKIP) {

			ExtentReport.extentlog.log(Status.SKIP, "Test case is Skiped " + result.getName());

		}

		//ExtentReport.extentreport.endTest(ExtentReport.extentlog);
		ExtentReport.extentreport.flush();

	}



	@AfterSuite(alwaysRun = true)

	public void endReport() {

		//ExtentReport.extentreport.flush();

		ExtentReport.extentreport.flush();

		//Logging.setinstanceNull();

	}

}
