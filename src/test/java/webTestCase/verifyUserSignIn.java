package webTestCase;

import Utils.utility;
import base_test.BaseTest;
import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import web_pages.Dashboard;
import web_pages.LandingPage;
import web_pages.SignInPage;
import web_pages.TwoFactorAuthPage;

import java.io.IOException;
import java.util.Hashtable;

public class verifyUserSignIn extends BaseTest {
    LandingPage landingPage = new LandingPage();
    TwoFactorAuthPage twoFactorAuthPage = new TwoFactorAuthPage();
    @Test(priority = 1, dataProvider = "getSignInDataHashTable", dataProviderClass = DataProviderManager.class)
    public void superAdminSignIn(Hashtable<String, String> data) throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        basePage.loadPage(driver,url_Address);

        if (signInPage.signIn(data)) {
            Assert.assertTrue(true, "Password or Username is  correct");
            twoFactorAuthPage.authentication();
            landingPage.waitForPageLoading();
            Assert.assertTrue(landingPage.verifyLandingPage(), "landing page could not displayed");

        } else {

            System.out.println("im here into 2");
            boolean expResult = signInPage.verifyExpectedResult();
            Assert.assertFalse(expResult, "Password or Username is not correct");

        }

    }
}
