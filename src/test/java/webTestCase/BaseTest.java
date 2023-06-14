package webTestCase;

import Utils.LogUtils;
import driver.DriverManager;
import driver.TargetFactory;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;
import lahebo.web_pages.*;

public class BaseTest {
    protected BasePage basePage = new BasePage();
     SignInPage signInPage;
     LandingPage landingPage ;
     TwoFactorAuthPage twoFactorAuthPage ;
     Dashboard dashboard;
     SignUpPage signUpPage;
     ForgetPasswordPage forgetPasswordPage;
     ResetPasswordPage resetPasswordPage;
     NewUserSubscriptionPlanPage newUserSubscriptionPlanPage;
    SubscribePlanPage subscribePlanPage;
    Congratulation congratulation;
    CardPaymentPage cardPaymentPage;
    DepartmentsPage departmentsPage;

    WebDriver driver;
    private ThreadLocal<String> testName = new ThreadLocal<>();



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
        congratulation = new Congratulation(driver);
        departmentsPage = new DepartmentsPage(driver);
    }
    @Parameters("BROWSER")
    @BeforeClass
    public  void beforeSuite(@Optional("chrome") String browser) {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        LogUtils.info("Driver is Just Initialized");
        initObject();

    }
    @Parameters("BROWSER")
    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
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


