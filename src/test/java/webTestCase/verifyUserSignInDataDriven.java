package webTestCase;

import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class verifyUserSignInDataDriven extends BaseTest {

    @Test(priority = 1, enabled = true, dataProvider = "getSignInDataHashTable", dataProviderClass = DataProviderManager.class)
    public void superAdminSignInDataDriven(Hashtable<String, String> data) throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        basePage.loadPage(driver, url_Address);
        if (signInPage.signIn(data)) {
            Assert.assertTrue(true, "Password or Username is  correct");
            Assert.assertTrue(twoFactorAuthPage.authenticationWithoutSecretKey(), "2FA Authentication Failed");
            landingPage.waitForPageLoading();
            Assert.assertTrue(landingPage.verifyLandingPage(), "landing page could not displayed");
            landingPage.signOut();
        } else {
            System.out.println("im here into 2");
            boolean expResult = signInPage.verifyExpectedResult();
            Assert.assertFalse(expResult, "Password or Username is not correct");
        }
    }

}
