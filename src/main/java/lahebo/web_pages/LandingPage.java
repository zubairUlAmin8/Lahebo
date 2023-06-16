package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import keywords.WebUI;
import lahebo.path_repo.LandingPagePR;
import lahebo.web_elements.LandingPageElements;
import lahebo.objectRepo.LandingPageOR;
import org.openqa.selenium.*;

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
        try {
            if (landingPageElements.LagislationLibraryTab.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException exception) {
            LogUtils.info(exception.getMessage());

        }
        return false;
    }

    public void waitForPageLoading() throws InterruptedException {
        LogUtils.info("Waiting for Loading");
        waits.waitForVisibilityOfItem(driver,landingPageElements.loaderMain, 30);
        LogUtils.info("Loading Started");
        waits.waitForInvisibilityOfItem(driver, LandingPagePR.loaderMain, 30);
        LogUtils.info("Loading finsihed");

    }

    public void signOut() throws InterruptedException {
        waits.waitForElements(driver,landingPageElements.profileIcon,5);
        landingPageElements.profileIcon.click();
        waits.waitForElements(driver,landingPageElements.signOutBtn, 5);
        landingPageElements.signOutBtn.click();

    }

    public void goToDepartmentST() throws InterruptedException {
        WebUI.clickElement(LandingPageOR.organizationSettingTabList);
        WebUI.clickElement(LandingPageOR.departmentsST,10);
    }
    public void goToLocationST() throws InterruptedException {
        WebUI.clickElement(LandingPageOR.organizationSettingTabList);
        WebUI.clickElement(LandingPageOR.locationST,10);
    }
    public void goToDashBoard() throws InterruptedException {
        WebUI.clickElement(LandingPageOR.dashboardTab);
    }

}
