package webTestCase;

import org.testng.annotations.Test;

public class verifyProfileSetting extends BaseTest {

    @Test()
    public void verifyEditProfile() throws InterruptedException {
        signInPage.signInUser();
//        landingPage.waitForPageLoading();
        landingPage.goToProfileSetting();
        profileDetailsPage.editProfile();
//        landingPage.waitForPageLoading();
        landingPage.waitForPopUpInvisibilty();
        profileDetailsPage.changePassword();


    }
}
