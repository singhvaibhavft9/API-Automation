package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ExtentReport {



	public static ExtentReports extentreport = null;

	public static ExtentTest extentlog;

    

	public static void initialize(String path) {

		if (extentreport == null) {

			 path= System.getProperty("user.dir") + "resources/extent-config.xml";
			ExtentSparkReporter reporter= new ExtentSparkReporter(path);
				  extentreport.setSystemInfo("Host Name", System.getProperty("user.name"));
		          extentreport.setSystemInfo("Environment", "QA");

			extentreport.attachReporter(reporter);

		}

	}

}

