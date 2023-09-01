package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import constants.FrameworkConstants;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.path_repo.LandingPagePR;
import lahebo.web_elements.LandingPageElements;
import lahebo.objectRepo.LandingPageOR;
import org.openqa.selenium.*;
import constants.FrameworkConstants.*;

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
    public void waitForPopUpInvisibilty() throws InterruptedException {
        WebUI.waitForElementVisible(LandingPageOR.popUpAlert, 5);
        WebUI.waitForInvisibilityOfItem(LandingPageOR.popUpAlert, 5);

    }

    public void signOut() throws InterruptedException {
        try {
            WebUI.waitForElementPresent(LandingPageOR.profileIcon,10 );

        } catch (ElementClickInterceptedException exception) {
            WebUI.waitForElementVisible(LandingPageOR.profileIcon,10);
        }
        WebUI.clickElement(LandingPageOR.profileIcon);
        WebUI.clickElement(LandingPageOR.signOutBtn);

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
        LogUtils.info("came Job Function");

    }
    public void goToUserSetupST() throws InterruptedException {
        if (checkOrganizationSettingList()) {
            WebUI.clickElement(LandingPageOR.userSetupST,10);
        } else {

            WebUI.clickElement(LandingPageOR.organizationSettingTabList);
            WebUI.clickElement(LandingPageOR.userSetupST,10);
        }

        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader,10);

        LogUtils.info("came userSetup");
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
    public void sideBarExpand() throws InterruptedException {
        if (checkSideBarStatus()) {
            LogUtils.info("Side Bar is Streaky Already");

        } else {
            WebUI.mouseHover(LandingPageOR.sideBarRadioBtn);
            WebUI.clickElement(LandingPageOR.sideBarSwitcher);
            LogUtils.info("Side Bar Made Streaky Now");

        }

    }
    public void goToRiskRegister() throws InterruptedException {
        WebUI.clickElement(LandingPageOR.riskRegisterTab);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);

    }
    public void goToActionCenter() throws InterruptedException {
        WebUI.clickElement(LandingPageOR.actionCenterTab);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);
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
    public boolean checkSideBarStatus() {
        WebElement element = DriverManager.getDriver().findElement(LandingPageOR.sideBarRadioBtn);
        String string =element.getAttribute("class");
        LogUtils.info("String is my "+string);
        if (string.contains("stick")) {
            return true;

        }
        else {
            return false;
        }

    }

    public void goToProfileSetting() {
        try {
            WebUI.waitForElementPresent(LandingPageOR.profileIcon,10 );

        } catch (ElementClickInterceptedException exception) {
            WebUI.waitForElementVisible(LandingPageOR.profileIcon,10);
        }
        WebUI.clickElement(LandingPageOR.profileIcon);
//        waits.waitForElements(driver,landingPageElements.signOutBtn, 5);
        WebUI.clickElement(LandingPageOR.profileSetting);
    }

}
