package base_test;

import driver.DriverManager;
import driver.TargetFactory;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;
import lahebo.web_pages.*;

public class BaseTest {
    protected BasePage basePage = new BasePage();
    public static SignInPage signInPage;
    public static LandingPage landingPage ;
    public static TwoFactorAuthPage twoFactorAuthPage ;
    public static Dashboard dashboard;
    public static SignUpPage signUpPage;
    public static ForgetPasswordPage forgetPasswordPage;
    public static ResetPasswordPage resetPasswordPage;
    public static NewUserSubscriptionPlanPage newUserSubscriptionPlanPage;
    public static SubscribePlanPage subscribePlanPage;
    public static CardPaymentPage cardPaymentPage;

    public  WebDriver driver;
    private ThreadLocal<String> testName = new ThreadLocal<>();

    public BaseTest() {
        System.out.println("BaseTest Contructor");
    }

    void initObject() {
        signInPage = new SignInPage(driver);
        landingPage = new LandingPage(driver);
        twoFactorAuthPage = new TwoFactorAuthPage(driver);
        signUpPage = new SignUpPage(driver);
        dashboard = new Dashboard(driver);
        forgetPasswordPage = new ForgetPasswordPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);
        newUserSubscriptionPlanPage = new NewUserSubscriptionPlanPage(driver);
        newUserSubscriptionPlanPage = new NewUserSubscriptionPlanPage(driver);
        subscribePlanPage = new SubscribePlanPage(driver);
        cardPaymentPage = new CardPaymentPage(driver);
    }
    @Parameters("BROWSER")
    @BeforeClass
    public  void beforeSuite(@Optional("chrome") String browser) {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.out.println("this is my BeforeSuite");
        driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        System.out.println("Driver is created");
        initObject();

    }

    @Parameters("BROWSER")
    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        System.out.println("we are BeforeMethod");
//        BasePage.setBrowser(driver);
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
//        DriverManager.quit();
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        PropertiesHelpers.loadAllFiles();
         driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }
}


