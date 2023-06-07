package webTestCase;

import Utils.fakerUtils;
import base_test.BaseTest;
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
        PropertiesHelpers.setValue("New_User_Password",randomPassword);
    }
    @Test(priority = 5)
    public void verifyUserWithNewPassword() throws MessagingException, IOException, InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        String username = PropertiesHelpers.getValue("New_User_UserName");
        String password = PropertiesHelpers.getValue("New_User_Password");
        String secretKey = PropertiesHelpers.getValue("New_User_SecretKey");
        driver.navigate().to(url_Address);
        signInPage.loadPage(driver,url_Address);
        signInPage.signIn(username, password);
        twoFactorAuthPage.authenticationWithSecretKeyWithScanCode(secretKey, "no");
    }
}