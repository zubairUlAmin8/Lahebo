package base_test;

import driver.DriverManager;
import driver.TargetFactory;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;
import web_pages.BasePage;
import web_pages.SignInPage;

public class BaseTest {
    protected BasePage basePage = new BasePage();
    public SignInPage signInPage;
    int bm=1;
    int am=1;
    public static  WebDriver driver;
    private ThreadLocal<String> testName = new ThreadLocal<>();

    public BaseTest() {
        System.out.println("BaseTest Contructor");
    }
    @Parameters("BROWSER")
    @BeforeSuite
    public  void beforeSuite(@Optional("chrome") String browser) {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.out.println("this is my BeforeSuite");
        driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        signInPage = new SignInPage(driver);
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("this is my BeforeMethod");
    }

    @Parameters("BROWSER")
    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        System.out.println("we are BeforeMethod");
//        BasePage.setBrowser(driver);
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


