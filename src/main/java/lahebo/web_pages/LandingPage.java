package lahebo.web_pages;

import Utils.waits;
import lahebo.path_repo.LandingPagePR;
import lahebo.web_elements.LandingPageElements;

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
