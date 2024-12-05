package redBus.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import redBus.PageObjectModel.busSearchPage;

import java.io.File;
import java.io.IOException;


public class baseClass {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected JavascriptExecutor js;
    protected busSearchPage searchPage;  // Make sure it's accessible in all tests

    @BeforeClass
    public void setup() {
        String reportName = this.getClass().getSimpleName() + ".html"; // Report file name based on class
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("RedBus Test Report - " + this.getClass().getSimpleName());
        spark.config().setDocumentTitle("Automation Test Report");

        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void initialize() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.redbus.in/");
        busSearchPage searchPage = new busSearchPage(driver);
        js = (JavascriptExecutor) driver;

    }

    @AfterClass
    public void tearDown() {
        // If needed, quit the driver after all tests are executed
        // driver.quit();
        extent.flush();
    }

    protected String captureScreenshot(String screenshotName) {
        // Relative path to the screenshots folder from project root
        String projectPath = System.getProperty("user.dir");
        String screenshotPath = projectPath + File.separator + "screenshots" + File.separator + screenshotName + ".jpg";

        try {
            // Ensure the screenshots directory exists
            File screenshotsDir = new File(projectPath + File.separator + "screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs(); // Create directories if they do not exist
            }

            // Capture the screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(screenshot, destination);
        } catch (IOException e) {
            System.err.println("Error while capturing screenshot: " + e.getMessage());
        }

        return screenshotPath; // Return the absolute path for further use
    }

    @AfterMethod
    public void endTest(ITestResult result) throws InterruptedException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log the failure status along with the throwable cause
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

            } else if (result.getStatus() == ITestResult.SUCCESS) {
            // Log the success status
            test.log(Status.PASS, "The test is passed");
        } else {
            // Log the skipped status
            test.log(Status.SKIP, "The test is skipped");
        }
        Thread.sleep(3000);

        //driver.quit();
    }

}
