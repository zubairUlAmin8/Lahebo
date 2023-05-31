package webTestCase;

import base_test.BaseTest;
import dataprovider.DataProviderManager;
import org.testng.annotations.Test;
import lahebo.web_pages.Dashboard;
import lahebo.web_pages.SignUpPage;
import lahebo.web_pages.TwoFactorAuthPage;

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
