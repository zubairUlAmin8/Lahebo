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
    SignInPage signInPage = new SignInPage();
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
            boolean expResut = signInPage.verifyExpectedResult();
            Assert.assertFalse(expResut, "Password or Username is not correct");

        }

    }
}
