package base_test;

import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import lahebo.web_pages.BasePage;

public class BaseTest {
    protected BasePage basePage = new BasePage();
    int bm=1;
    int am=1;
    static protected WebDriver driver;
    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeSuite
    public void loadFirst() {
        System.out.println("Before Suite");
        PropertiesHelpers.loadAllFiles();
    }

//    @BeforeClass
//    public void beforeClass() {
//        System.out.println("Before Class");
//    }
//    @BeforeGroups
//    public void beforeGroup() {
//        System.out.println("Before Group");
//    }
//    @BeforeTest
//    public void beforetest() {
//        System.out.println("Before Test");
//    }
    @BeforeTest
    public void startDriver() {
        System.out.println("Before Method"+bm);
        ++bm;
        System.out.println("Before opening driver"+ driver);
        driver =basePage.startDriver();
        System.out.println("after opening driver"+ driver);
        System.out.println("Before method Thread Id:" + Thread.currentThread().getId());
    }


//    @BeforeTest
//    public void check() {
//        String className = this.getClass().getSimpleName();
//        System.out.println(className);
//
//    }

    @AfterTest
    public void closeDriver() throws InterruptedException {
        System.out.println("Before closing driver"+ driver);
        driver.quit();
//        Thread.sleep(6000);
//    ExtentReportManager.flushReports();
    }

}


