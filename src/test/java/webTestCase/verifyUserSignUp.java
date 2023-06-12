package webTestCase;

import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import lahebo.web_pages.SignUpPage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

public class verifyUserSignUp extends BaseTest {
    @Test(priority = 1,enabled = true, dataProvider = "getSignUpDataHashTable", dataProviderClass = DataProviderManager.class)
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
    @Test(priority=2, enabled = true)
    public void saveNewUserIntoFile() {
        signUpPage.saveDataIntoFile();
    }

    @Test(priority = 3, enabled = true)
    public void verifyGmailOTP() throws MessagingException, IOException, InterruptedException {
        Thread.sleep(5000);
        String OTPCode = twoFactorAuthPage.getGmailInboxOTP();
        boolean status = twoFactorAuthPage.authentication(OTPCode);
        Assert.assertTrue(status, "OTP is not correct");
    }

    @Test(priority=4)
    public void verifyNewUser() throws InterruptedException {
        String username = PropertiesHelpers.getValue("New_User_UserName");
        String password = PropertiesHelpers.getValue("New_User_Password");
        signInPage.signIn(username, password);
        String secretKey = twoFactorAuthPage.getSecretKeyNewUser();
        System.out.println("Secret Key"+secretKey);
        twoFactorAuthPage.authenticationWithSecretKeyWithScanCode(secretKey, "yes");
    }
    @Test(priority = 5)
    public void choosePlan() {
        subscribePlanPage.choosePlan();
        Assert.assertTrue(subscribePlanPage.confirmPlan(),"Payment are not correct");
        cardPaymentPage.fillCardDetails();
    }
    @Test(priority = 6)
    public void verifyCongratulation() {
        Assert.assertTrue(congratulation.verifyCongratulation(),"Congratulation page");
    }
}
