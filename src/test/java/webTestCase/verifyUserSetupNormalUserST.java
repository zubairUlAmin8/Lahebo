package webTestCase;

import Utils.LogUtils;
import Utils.fakerUtils;
import helpers.PropertiesHelpers;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class verifyUserSetupNormalUserST extends BaseTest {


    //Open Browser and Hit the Base URL
    String managerUserName;
    String managerPassword;
    @Test(priority = 0)
    public void AddNewManager() throws IOException, InterruptedException, MessagingException {
        landingPage.goToUserSetupST();
        managerUserName=userSetupPage.addNewNormalUser();
        landingPage.signOut();

    }
    @Test(priority = 1)

    public void checkpassowrd() throws InterruptedException, MessagingException, IOException {
        Thread.sleep(5000);
        managerPassword= twoFactorAuthPage.getGmailInboxPasswordForNewUser();
        PropertiesHelpers.setValue("NORMAL_USER_NAME", managerUserName);
        PropertiesHelpers.setValue("NORMAL_USER_PASSWORD", managerPassword);
        LogUtils.info("NORMAL_USER_NAME: "+managerUserName+" ----NORMAL_USER_PASSWORD: "+managerPassword+" Has been set to config file");
    }
    @Test(priority=2, enabled = true)
    public void verifyNewManager() throws InterruptedException {
        String username = PropertiesHelpers.getValue("NORMAL_USER_NAME");
        String password = PropertiesHelpers.getValue("NORMAL_USER_PASSWORD");
        signInPage.signIn(username, password);

        String randomPassword = fakerUtils.generateRandomPassword();
        resetPasswordPage.fillNewUserResetPasswordForm(password,randomPassword);

        PropertiesHelpers.setValue("NORMAL_USER_PASSWORD", randomPassword);
        String secretKey = twoFactorAuthPage.getSecretKeyNewUser();
        PropertiesHelpers.setValue("NORMAL_USER_SECRET_KEY", secretKey);
        LogUtils.info("Secret Key"+secretKey);
        twoFactorAuthPage.authenticationWithSecretKeyWithScanCode(secretKey, "yes");
    }

}
