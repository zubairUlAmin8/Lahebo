package webTestCase;

import Utils.LogUtils;
import Utils.fakerUtils;
import helpers.PropertiesHelpers;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class verifyUserSetupManagerST extends BaseTest {


    //Open Browser and Hit the Base URL
    String managerUserName;
    String managerPassword;
    @Test(priority = 0)
    public void AddNewManager() throws IOException, InterruptedException, MessagingException {
        landingPage.goToUserSetupST();
        managerUserName=userSetupPage.addNewManagerUser();
        landingPage.signOut();

    }
    @Test(priority = 1)

    public void checkpassowrd() throws InterruptedException, MessagingException, IOException {
        Thread.sleep(5000);
        managerPassword= twoFactorAuthPage.getGmailInboxPasswordForNewUser();
        PropertiesHelpers.setValue("MANAGER_USER_NAME", managerUserName);
        PropertiesHelpers.setValue("MANAGER_PASSWORD", managerPassword);
        LogUtils.info("MANAGER_USER_NAME: "+managerUserName+" ----MANAGER_PASSWORD: "+managerPassword+" Has been set to config file");
    }
    @Test(priority=2, enabled = true)
    public void verifyNewManager() throws InterruptedException {
        String username = PropertiesHelpers.getValue("MANAGER_USER_NAME");
        String password = PropertiesHelpers.getValue("MANAGER_PASSWORD");
        signInPage.signIn(username, password);

        String randomPassword = fakerUtils.generateRandomPassword();
        resetPasswordPage.fillNewUserResetPasswordForm(password,randomPassword);

        PropertiesHelpers.setValue("MANAGER_PASSWORD", randomPassword);
        String secretKey = twoFactorAuthPage.getSecretKeyNewUser();
        PropertiesHelpers.setValue("MANAGER_SECRET_KEY", secretKey);
        LogUtils.info("Secret Key"+secretKey);
        twoFactorAuthPage.authenticationWithSecretKeyWithScanCode(secretKey, "yes");
    }

}
