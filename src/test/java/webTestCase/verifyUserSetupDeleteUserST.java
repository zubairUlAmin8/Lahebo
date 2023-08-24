package webTestCase;

import Utils.LogUtils;
import Utils.fakerUtils;
import helpers.PropertiesHelpers;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class verifyUserSetupDeleteUserST extends BaseTest {


    //Open Browser and Hit the Base URL
    String managerUserName;
    String managerPassword;
    @Test(priority = 0)
    public void DeleteNormalUser() throws IOException, InterruptedException, MessagingException {
        landingPage.goToUserSetupST();
        userSetupPage.deleteManager(PropertiesHelpers.getValue("NORMAL_USER_NAME"));
    }

}
