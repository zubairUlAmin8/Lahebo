package webTestCase;

import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Hashtable;

public class verifyUserSignOut extends BaseTest {

    @Test(priority = 1, enabled = true)
    public void verifyUserSignOut() throws InterruptedException {
        landingPage.signOut();
        Assert.assertTrue(signInPage.verifyLoginPage(), "User could not sign OUt");
    }
}
