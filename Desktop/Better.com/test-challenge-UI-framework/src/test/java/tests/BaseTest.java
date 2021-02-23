package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import utils.ConfigReader;
import utils.Driver;

import java.io.IOException;

public class BaseTest {

    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;

    @BeforeTest
    @Parameters({"test", "env_url"})
    public void beforeTest(@Optional String test, @Optional String env_url) {
        String reportName = "report";
        if (test != null) {
            reportName = test;
        }

        String filePath = System.getProperty("user.dir") + "/test-output/" + reportName + ".html";
        extentReports = new ExtentReports();
        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("Test Results");
        String env = ConfigReader.getProperty("url");
        if (env_url != null) {
            env = env_url;
        }
        extentReports.setSystemInfo("Environment", env);
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @AfterTest
    public void afterTest() {
        extentReports.flush();
        Driver.close();
    }


    @BeforeMethod
    @Parameters("env_url")
    public void setup(@Optional String env_url) {
        String url = ConfigReader.getProperty("url");
        if (env_url != null) {
            url = env_url;
        }
        Driver.get().get(url);
        BasePage.wait(3);
    }


    @AfterMethod
    public void teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getName());
            extentTest.fail(result.getThrowable());
            try {
                extentTest.addScreenCaptureFromPath(BasePage.getScreenshot(result.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test case was skipped : " + result.getName());
        }
    }
}
