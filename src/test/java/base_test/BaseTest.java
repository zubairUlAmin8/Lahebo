package base_test;

import driver.DriverManager;
import driver.TargetFactory;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;
import web_pages.BasePage;

public class BaseTest {
    protected BasePage basePage = new BasePage();
    int bm=1;
    int am=1;
    public static  WebDriver driver;
    private ThreadLocal<String> testName = new ThreadLocal<>();

    public BaseTest() {
        System.out.println("BaseTest Contructor");
    }


//    @BeforeSuite
//    public void loadFirst() {
//        System.out.println("Before Suite");
//        PropertiesHelpers.loadAllFiles();
//    }
//
////    @BeforeClass
////    public void beforeClass() {
////        System.out.println("Before Class");
////    }
////    @BeforeGroups
////    public void beforeGroup() {
////        System.out.println("Before Group");
////    }
////    @BeforeTest
////    public void beforetest() {
////        System.out.println("Before Test");
////    }
//    @BeforeTest
//    public void startDriver() {
//        System.out.println("Before Method"+bm);
//        ++bm;
//        System.out.println("Before opening driver"+ driver);
//        driver =basePage.startDriver();
//        System.out.println("after opening driver"+ driver);
//        System.out.println("Before method Thread Id:" + Thread.currentThread().getId());
//    }
//
//
////    @BeforeTest
////    public void check() {
////        String className = this.getClass().getSimpleName();
////        System.out.println(className);
////
////    }
//
//    @AfterTest
//    public void closeDriver() throws InterruptedException {
//        System.out.println("Before closing driver"+ driver);
//        driver.quit();
////        Thread.sleep(6000);
////    ExtentReportManager.flushReports();
//    }

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("this is my BeforeSuite");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("this is my BeforeMethod");
    }

    @Parameters("BROWSER")
    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.out.println("we are here");
         driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        BasePage.setBrowser(driver);
        driver.manage().window().maximize();
        System.out.println("intilization in beforemethod"+ driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        PropertiesHelpers.loadAllFiles();
         driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }
}


