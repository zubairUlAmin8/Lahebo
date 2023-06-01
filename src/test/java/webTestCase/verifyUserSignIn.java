package webTestCase;

import base_test.BaseTest;
import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import lahebo.web_pages.LandingPage;
import lahebo.web_pages.SignInPage;
import lahebo.web_pages.TwoFactorAuthPage;

import java.util.Hashtable;

public class verifyUserSignIn extends BaseTest {

    @Test(priority = 1, dataProvider = "getSignInDataHashTable", dataProviderClass = DataProviderManager.class)
    public void superAdminSignIn(Hashtable<String, String> data) throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        basePage.loadPage(driver,url_Address);
        if (signInPage.signIn(data)) {
            Assert.assertTrue(true, "Password or Username is  correct");
            Assert.assertTrue( twoFactorAuthPage.authentication(), "2FA Authentication Failed");
            landingPage.waitForPageLoading();
            Assert.assertTrue(landingPage.verifyLandingPage(), "landing page could not displayed");

        } else {
            System.out.println("im here into 2");
            boolean expResult = signInPage.verifyExpectedResult();
            Assert.assertFalse(expResult, "Password or Username is not correct");

        }
    }
    @Test(priority = 2)
    void mytest() throws InterruptedException {
        landingPage.goToTab();

    }
}
