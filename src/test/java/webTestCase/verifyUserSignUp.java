package webTestCase;

import base_test.BaseTest;
import dataprovider.DataProviderManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import lahebo.web_pages.Dashboard;
import lahebo.web_pages.SignUpPage;
import lahebo.web_pages.TwoFactorAuthPage;

import java.util.Hashtable;

public class verifyUserSignUp extends BaseTest {
    @Test(priority = 1, dataProvider = "getSignUpDataHashTable", dataProviderClass = DataProviderManager.class)
    public void fillSignUpPage(Hashtable<String, String> data) throws InterruptedException {
        System.out.println("Signup getting started");
        signUpPage.goToSignUpPage();
        boolean status =signUpPage.fillSignUpForm(data);

        if (signUpPage.verifyExpectedResult()) {
            Assert.assertTrue(status, "Data not correct");

        } else {
            Assert.assertFalse(status, "Data not correct");
        }
        System.out.println("Signup getting end");

    }
}
