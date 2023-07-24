package webTestCase;

import Utils.fakerUtils;
import constants.TestDataConstants;
import driver.DriverManager;
import helpers.PropertiesHelpers;
import keywords.WebUI;
import lahebo.objectRepo.LandingPageOR;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verifyLegislationLibrary extends BaseTest {
    @Test(priority = 0, enabled = true)
    public void login() throws InterruptedException {
//        signInPage.signInUserBypass2FA();
                signInPage.signInUser();

    }

    @Test(priority = 1, invocationCount = 2, enabled = true)
    public void verifysubscribeItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.subscribeItems();
    }

    @Test(priority = 2, enabled = true, invocationCount = 2)
    public void verifyAddLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.addLegalRegister();
    }
    @Test(priority = 3, enabled = false)
    public void verifyEditLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.editLegalRegister();
    }
    @Test(priority = 4, enabled = false, invocationCount = 5)
    public void verifyDeleteLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.deleteLegalRegister();
    }
    @Test(priority = 5, enabled = false)
    public void unsubscribeAllItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.unsubscribeAllItems();
    }

}
