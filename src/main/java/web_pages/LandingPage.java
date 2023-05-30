package web_pages;

import Utils.waits;
import helpers.PropertiesHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import path_repo.LandingPagePR;
import web_elements.LandingPageElements;
import web_elements.SingInPageElements;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LandingPage extends BasePage{
    LandingPageElements landingPageElements;


    public void goToTab() throws InterruptedException {
        landingPageElements = new LandingPageElements(driver);
        waits.waitForElements(driver,landingPageElements.LagislationLibraryTab,20);
        landingPageElements.LagislationLibraryTab.click();
    }

    public boolean verifyLandingPage() {
        if (landingPageElements.LagislationLibraryTab.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForPageLoading() throws InterruptedException {
        landingPageElements = new LandingPageElements(driver);
        waits.waitForVisibilityOfItem(driver,landingPageElements.loaderMain, 30);
//        Thread.sleep(500);
        waits.waitForInvisibilityOfItem(driver,LandingPagePR.loaderMain, 30);
    }
}
