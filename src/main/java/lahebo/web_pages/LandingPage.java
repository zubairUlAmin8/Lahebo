package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import driver.DriverManager;
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
            waits.waitForVisibilityOfItem(driver,landingPageElements.LagislationLibraryTab, 5);
            if (landingPageElements.LagislationLibraryTab.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException exception) {
            LogUtils.error(exception.getMessage());
        }
        return false;
    }

    public void waitForPageLoading() throws InterruptedException {
        LogUtils.info("Waiting for Loading");
        waits.waitForVisibilityOfItem(driver,landingPageElements.loaderMain, 5);
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
        if (checkOrganizationSettingList()) {
            WebUI.clickElement(LandingPageOR.departmentsST,10);

        }
        else {
            WebUI.clickElement(LandingPageOR.organizationSettingTabList);
            WebUI.clickElement(LandingPageOR.departmentsST,10);
        }

    }
    public void goToJobFunctionsST() throws InterruptedException {
        if (checkOrganizationSettingList()) {
            WebUI.clickElement(LandingPageOR.jobFunctionST,10);
        } else {

            WebUI.clickElement(LandingPageOR.organizationSettingTabList);
            WebUI.clickElement(LandingPageOR.jobFunctionST,10);
        }
        LogUtils.info("came Location");
    }

    public void goToLocationST() throws InterruptedException {
        if (checkOrganizationSettingList()) {
            WebUI.clickElement(LandingPageOR.locationST, 10);
        } else {

            WebUI.clickElement(LandingPageOR.organizationSettingTabList);
            WebUI.clickElement(LandingPageOR.locationST,10);
        }
        LogUtils.info("came Location");
    }
    public void goToDashBoard() throws InterruptedException {
        WebUI.clickElement(LandingPageOR.dashboardTab);
    }
    public void goToLegislationLibrary() throws InterruptedException {
        WebUI.clickElement(LandingPageOR.legislationLibraryTab);
    }

    public boolean checkOrganizationSettingList() {
        WebElement element = DriverManager.getDriver().findElement(LandingPageOR.organizationSettingArrow);
        String string =element.getAttribute("class");
        LogUtils.info("String is my "+string);
        if (string.contains("angle-down")) {
            return true;

        }
        else {
            return false;
        }

    }

}
