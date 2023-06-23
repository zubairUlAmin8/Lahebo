package listeners;

import Utils.BrowserInfoUtils;
import Utils.LogUtils;
import annotations.FrameworkAnnotation;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import enums.AuthorType;
import enums.CategoryType;
import helpers.CaptureHelpers;
import helpers.PropertiesHelpers;
import helpers.ScreenRecoderHelpers;
import keywords.WebUI;
import org.testng.*;
import report.ExtentReportManager;

import lahebo.web_pages.SignInPage;

import java.awt.*;
import java.io.IOException;

import static constants.FrameworkConstants.*;


public class ListenerTest implements ITestListener, ISuiteListener, IInvokedMethodListener {
    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;
    private ScreenRecoderHelpers screenRecorder;

    public ListenerTest() {

    }

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    public AuthorType[] getAuthorType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        AuthorType authorType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author();
        return authorType;
    }

    public CategoryType[] getCategoryType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        CategoryType categoryType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category();
        return categoryType;
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LogUtils.info("End Suite: " + iSuite.getName());
        WebUI.stopSoftAssertAll();
        //End Suite and execute Extents Report
        ExtentReportManager.flushReports();

        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
            screenRecorder.stopRecording(true);
        }
    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("========= INSTALLING CONFIGURATION DATA =========");
//        try {
//            FileUtils.deleteDirectory(new File("target/allure-results"));
//            System.out.println("Deleted Directory target/allure-results");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        PropertiesHelpers.loadAllFiles();
        ExtentReportManager.initReports();
        System.out.println("========= INSTALLED CONFIGURATION DATA =========");
        System.out.println("");
        LogUtils.info("Starting Suite: " + iSuite.getName());
        try {
            screenRecorder = new ScreenRecoderHelpers();
        } catch (IOException | AWTException e) {
            System.out.println(e.getMessage());
        }
        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
            screenRecorder.startRecording(iSuite.getName());
        }


    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LogUtils.error("Test failed but it is in defined success ratio: " + getTestDescription(iTestResult));
        ExtentReportManager.logMessage("Test failed but it is in defined success ratio: " + getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("Test case: " + getTestDescription(iTestResult) + " is failed.");
        count_failedTCs = count_failedTCs + 1;

        if (SCREENSHOT_FAILED_STEPS.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }


        //Allure report screenshot file and log
        LogUtils.error("FAILED !! Screenshot for test case: " + getTestName(iTestResult));
        LogUtils.error(iTestResult.getThrowable());

        //Extent report screenshot file and log
        ExtentReportManager.addScreenShot(DriverManager.getDriver(), getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());

        //AllureManager.takeScreenshotToAttachOnAllureReport();
        //AllureManager.saveTextLog(iTestResult.getThrowable().toString());
//        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
//            screenRecorder.stopRecording(true);
//        }

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("Test case: " + getTestDescription(iTestResult) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;

        if (SCREENSHOT_SKIPPED_STEPS.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " is skipped.");
//        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
//            screenRecorder.stopRecording(true);
//        }

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestDescription(iTestResult) + " is starting...");
//        setCustomTestName(iTestResult);
        if (iTestResult.getName().equals("superAdminSignIn")) {
            ExtentReportManager.createTest(iTestResult.getName() + " TC_" + count_totalTCs);

        } else {
            ExtentReportManager.createTest(iTestResult.getName());

        }
        ExtentReportManager.addAuthors(getAuthorType(iTestResult));
        ExtentReportManager.addCategories(getCategoryType(iTestResult));
        ExtentReportManager.addDevices();
        ExtentReportManager.info(BrowserInfoUtils.getOSInfo());
        count_totalTCs = count_totalTCs + 1;


    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestDescription(iTestResult) + " is passed.");
        count_passedTCs = count_passedTCs + 1;

        if (SCREENSHOT_PASSED_STEPS.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        //ExtentReports log operation for passed tests.
        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " is passed.");
//        if (VIDEO_RECORD.trim().toLowerCase().equals(YES)) {
//            screenRecorder.stopRecording(true);
//        }


    }

    public void setCustomTestName(ITestResult iTestResult) {
        if (iTestResult.getName().equals("superAdminSignIn")) {
            String testName = iTestResult.getName();
//
            String modifiedTestName = testName + ": "+ SignInPage.getTestName();
            iTestResult.setTestName(modifiedTestName);
        }

    }
}
