package webTestCase;

import base_test.BaseTest;
import dataprovider.DataProviderManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import lahebo.web_pages.Dashboard;
import lahebo.web_pages.SignUpPage;
import lahebo.web_pages.TwoFactorAuthPage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Hashtable;

public class verifyUserSignUp extends BaseTest {
    @Test(priority = 1, dataProvider = "getSignUpDataHashTable", dataProviderClass = DataProviderManager.class)
    public void fillSignUpPage(Hashtable<String, String> data) throws InterruptedException {
        System.out.println("Signup getting started");
        signUpPage.goToSignUpPage();
        boolean status = signUpPage.fillSignUpForm(data);

        if (signUpPage.verifyExpectedResult()) {
            Assert.assertTrue(status, "Data not correct");

        } else {
            Assert.assertFalse(status, "Data not correct");
        }
        System.out.println("Signup getting end");
    }

    @Test(priority = 2)
    public void verifyGmailOTP() throws MessagingException, IOException, InterruptedException {
        Thread.sleep(5000);
        String OTPCode = twoFactorAuthPage.getGmailInboxOTP();
        boolean status = twoFactorAuthPage.authentication(OTPCode);
        Assert.assertTrue(status, "OTP is not correct");
    }
    @Test(priority=3)
    public void verifyNewUser() throws InterruptedException {
        String username = SignUpPage.randomUsername;
        String password = "Password123!";
        signInPage.signIn(username, password);
        String secretKey = twoFactorAuthPage.getSecretKeyNewUser();
        System.out.println("Secret Key"+secretKey);
        twoFactorAuthPage.authenticationWithSecretKey(secretKey);
    }
    @Test(priority=4)
    public void saveNewUserIntoFile() {
        signUpPage.saveDataIntoFile();

    }
}
