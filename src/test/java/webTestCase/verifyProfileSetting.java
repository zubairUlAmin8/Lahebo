package webTestCase;

import Utils.fakerUtils;
import com.github.javafaker.Faker;
import constants.TestDataConstants;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verifyProfileSetting extends BaseTest {

    @Test(priority = 1)
    public void verifyEditProfile() throws InterruptedException {
        signInPage.signInUser();
        landingPage.goToProfileSetting();
        profileDetailsPage.editProfile("Zubair", "Amin", fakerUtils.generateRandomAustralianPhoneNumber());
        landingPage.waitForPopUpInvisibilty();
        profileDetailsPage.editProfile("Sarah", "Johnson", fakerUtils.generateRandomAustralianPhoneNumber());
        landingPage.waitForPopUpInvisibilty();
    }
    @Test(priority = 2, enabled = false)
    public void verifyChangePassword() throws InterruptedException {
        String oldPassword = TestDataConstants.Existing_User_Password;
        String newPassword = fakerUtils.generateRandomPassword();
        profileDetailsPage.changePassword(oldPassword,newPassword);
        Assert.assertTrue(signInPage.verifyLoginPage(), "Password has not been changed");
        PropertiesHelpers.setValue("Existing_User_Password", newPassword);
        signInPage.signInUser();
    }
}
