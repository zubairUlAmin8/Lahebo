package webTestCase;

import Utils.fakerUtils;
import constants.TestDataConstants;
import driver.DriverManager;
import helpers.PropertiesHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verifyLegislationLibrary extends BaseTest {
    @Test(priority = 0, enabled = true)
    public void login() throws InterruptedException {
        signInPage.signInUser();
    }

    @Test(priority = 1, invocationCount = 5, enabled = true)
    public void verifysubscribeItems() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.subscribeItems();
        Thread.sleep(5000);
    }

    @Test(priority = 2, enabled = true, invocationCount = 5)
    public void verifyLegalRegister() throws InterruptedException {
        landingPage.goToLegislationLibrary();
        legislationLibraryPage.addLegalRegister();
        Thread.sleep(5000);
    }

    @Test( enabled = false)
    public void testscrap() {
        DriverManager.getDriver().get("https://www.legislation.gov.au/Details/C2021C00336");
        WebElement element= DriverManager.getDriver().findElement(By.id("MainContent_RadPageHTML"));
        System.out.println(element.getAttribute("innerText"));
    }

}
