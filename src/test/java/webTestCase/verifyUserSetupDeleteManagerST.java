package webTestCase;

import helpers.PropertiesHelpers;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class verifyUserSetupDeleteManagerST extends BaseTest {


    //Open Browser and Hit the Base URL
    String managerUserName;
    String managerPassword;
    @Test(priority = 0)
    public void DeleteNewManager() throws IOException, InterruptedException, MessagingException {
        landingPage.goToUserSetupST();

        userSetupPage.deleteManager(PropertiesHelpers.getValue("MANAGER_USER_NAME"));


    }

}
