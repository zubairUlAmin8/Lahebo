package webTestCase;

import Utils.LogUtils;
import Utils.fakerUtils;
import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import lahebo.web_pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Hashtable;

public class verifyForgetPassword extends BaseTest {
    String randomPassword;

    @Test(priority = 1)
    public void goToForgetPage() throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        String userName = PropertiesHelpers.getValue("New_User_UserName");
        basePage.loadPage(driver, url_Address);
        signInPage.goToForgetPasswordPage();
    }
    @Test(priority = 2)
    public void submitRequestForPasswordChange() throws InterruptedException {
        String userName = PropertiesHelpers.getValue("New_User_UserName");
        System.out.println(userName);
        forgetPasswordPage.submitRequest(userName);
    }
    @Test(priority = 3)
    public void setUpNewPassword() throws MessagingException, IOException, InterruptedException {
        Thread.sleep(5000);
        String OTPCode = twoFactorAuthPage.getGmailInboxOTP();
        randomPassword = fakerUtils.generateRandomPassword();
        resetPasswordPage.fillResetPasswordForm(OTPCode,randomPassword);
    }
    @Test(priority = 4)
    public void twoFactorAuthentication() throws MessagingException, IOException, InterruptedException {
        String secretKey = PropertiesHelpers.getValue("New_User_SecretKey");
        System.out.println("Secret Key"+secretKey);
        twoFactorAuthPage.authenticationWithSecretKeyWithScanCode(secretKey, "no");
        System.out.println("Random Password: "+randomPassword);
        PropertiesHelpers.setValue("New_User_Password",randomPassword);
    }
    @Test(priority = 5)
    public void verifyUserWithNewPassword() throws MessagingException, IOException, InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        String username = PropertiesHelpers.getValue("New_User_UserName");
        String password = PropertiesHelpers.getValue("New_User_Password");
        String secretKey = PropertiesHelpers.getValue("New_User_SecretKey");
        signInPage.loadPage(driver,url_Address);
        signInPage.signIn(username, password);
        boolean status=twoFactorAuthPage.authenticationWithSecretKeyWithScanCode(secretKey, "no");
        Assert.assertTrue(status, "2FA Authentication Fails");
        System.out.println("done");
    }

    @Test
    public void check() {
        LogUtils.info("this is my logs");
    }
}