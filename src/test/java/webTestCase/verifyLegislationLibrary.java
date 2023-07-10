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

    @Test(priority = 1, invocationCount = 3, enabled = true)
    public void verifysubscribeItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.subscribeItems();
    }

    @Test(priority = 2, enabled = true, invocationCount = 3)
    public void verifyLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.addLegalRegister();
    }
    @Test(priority = 3, enabled = true)
    public void verifyEditLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.editLegalRegister();
    }
    @Test(priority = 4, enabled = true)
    public void verifyDeleteLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.deleteLegalRegister();
    }
    @Test(priority = 5, enabled = true)
    public void unsubscribeAllItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.unsubscribeAllItems();
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
