package webTestCase;

import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import lahebo.web_pages.LandingPage;
import lahebo.web_pages.SignInPage;
import lahebo.web_pages.TwoFactorAuthPage;

import java.util.Hashtable;

public class verifyUserSignIn extends BaseTest {

    @Test(priority = 1, enabled = false, dataProvider = "getSignInDataHashTable", dataProviderClass = DataProviderManager.class)
    public void superAdminSignInDataDriven(Hashtable<String, String> data) throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        basePage.loadPage(driver, url_Address);
        if (signInPage.signIn(data)) {
            Assert.assertTrue(true, "Password or Username is  correct");
            Assert.assertTrue(twoFactorAuthPage.authenticationWithSecretKey(), "2FA Authentication Failed");
            landingPage.waitForPageLoading();
            Assert.assertTrue(landingPage.verifyLandingPage(), "landing page could not displayed");
        } else {
            System.out.println("im here into 2");
            boolean expResult = signInPage.verifyExpectedResult();
            Assert.assertFalse(expResult, "Password or Username is not correct");
        }
    }

    @Test(priority = 2, enabled = true)
    public void signInUser() throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        String userName = PropertiesHelpers.getValue("New_User_UserName");
        String password = PropertiesHelpers.getValue("New_User_Password");
        String secretKey = PropertiesHelpers.getValue("New_User_SecretKey");
        System.out.println(secretKey);
        basePage.loadPage(driver, url_Address);
        if (signInPage.signIn(userName, password)) {
            Assert.assertTrue(true, "Password or Username is  correct");
            Assert.assertTrue(twoFactorAuthPage.authenticationWithSecretKey(secretKey), "2FA Authentication Failed");
            landingPage.waitForPageLoading();
            Assert.assertTrue(landingPage.verifyLandingPage(), "landing page could not displayed");
        } else {
            System.out.println("im here into 2");
            boolean expResult = signInPage.verifyExpectedResult();
            Assert.assertFalse(expResult, "Password or Username is not correct");
        }
    }
    @Test(priority = 3, enabled = false)
    public void verifyUserSignOut() {
        landingPage.signOut();
        Assert.assertTrue(signInPage.verifyLoginPage(), "User could not sign OUt");
    }
}
