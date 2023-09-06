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
    @Test(priority = 1, invocationCount = 1, enabled = true)
    public void verifySubscribeItems() throws InterruptedException {
        landingPage.sideBarExpand();
        landingPage.goToLegislationLibrary();
//        Thread.sleep(5000);
        legislationLibraryPage.subscribeItems();
    }

    @Test(priority = 2, enabled = true, invocationCount = 1)
    public void verifyAddLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.addLegalRegister();
    }
    @Test(priority = 3, enabled = true)
    public void verifyEditLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.editLegalRegister();
    }
    @Test(priority = 4, enabled = true, invocationCount = 1)
    public void verifyAllDeleteLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.deleteAllLegalRegister();
    }
    @Test(priority = 5, enabled = true)
    public void unsubscribeAllItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.unsubscribeAllItems();
    }

//    @Test(priority = 6, enabled = false, invocationCount = 2)
//    public void verifyAddLegalRegisterForRisk() throws InterruptedException {
//        landingPage.goToLegislationLibrary();
//        legislationLibraryPage.addLegalRegister();
//    }
//    @Test(priority = 7, enabled = true, invocationCount = 1)
//    public void verifyAllDeleteLegalRegister() throws InterruptedException {
//        landingPage.goToLegislationLibrary();
//        legislationLibraryPage.deleteAllLegalRegister();
//    }

}
