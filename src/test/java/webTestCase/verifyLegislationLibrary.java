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
        signInPage.signInUser();
    }

    @Test(priority = 1, invocationCount = 20, enabled = true)
    public void verifysubscribeItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.subscribeItems();
        Thread.sleep(5000);
    }

    @Test(priority = 2, enabled = false, invocationCount = 5)
    public void verifyLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.addLegalRegister();
        Thread.sleep(5000);
    }
    @Test(priority = 3, enabled = true)
    public void unsubscribeAllItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.unsubscribeAllItems();
        Thread.sleep(5000);
    }

    @Test( enabled = false, invocationCount = 20)
    public void testscrap() throws InterruptedException {
//        for (int i = 0; i < 20; i++) {
            landingPage.goToDashBoard();
            WebUI.waitSpinner(LandingPageOR.spinnerLoader, 10);
            landingPage.goToActionCenter();
            WebUI.waitSpinner(LandingPageOR.spinnerLoader, 10);
            landingPage.goToLegislationLibrary();
            WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader,5);
//            WebUI.waitSpinner(LandingPageOR.spinnerLoader, 10);
//        }

    }


}
