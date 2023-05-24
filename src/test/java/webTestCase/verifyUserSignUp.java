package webTestCase;

import base_test.BaseTest;
import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web_pages.Dashboard;
import web_pages.SignInPage;
import web_pages.SignUpPage;
import web_pages.TwoFactorAuthPage;

import java.util.Hashtable;

public class verifyUserSignUp extends BaseTest {


    SignUpPage signUpPage = new SignUpPage();
    Dashboard dashboard = new Dashboard();
    TwoFactorAuthPage twoFactorAuthPage = new TwoFactorAuthPage();

    @Test(priority = 1, dataProvider = "getSignUpDataHashTable", dataProviderClass = DataProviderManager.class)
    public void fillSignUpPage(Hashtable<String, String> data) throws InterruptedException {
        System.out.println("Signup getting started");
        signUpPage.goToSignUpPage();
        signUpPage.fillSignUpForm(data);
        System.out.println("Signup getting end");

    }
}
