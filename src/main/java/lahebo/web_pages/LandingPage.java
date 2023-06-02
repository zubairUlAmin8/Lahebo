package lahebo.web_pages;

import Utils.waits;
import driver.DriverManager;
import helpers.PropertiesHelpers;
import lahebo.path_repo.LandingPagePR;
import lahebo.web_elements.LandingPageElements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LandingPage {
    WebDriver driver;
    LandingPageElements landingPageElements;
    public LandingPage(WebDriver driver) {
        this.driver=driver;
        landingPageElements = new LandingPageElements(driver);
    }


    public void goToTab() throws InterruptedException {
        waits.waitForElements(driver,landingPageElements.LagislationLibraryTab,5);
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
        waits.waitForVisibilityOfItem(driver,landingPageElements.loaderMain, 30);
//        Thread.sleep(500);
        waits.waitForInvisibilityOfItem(driver, LandingPagePR.loaderMain, 30);
    }
}
