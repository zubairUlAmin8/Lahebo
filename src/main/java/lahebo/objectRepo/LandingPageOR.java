package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface LandingPageOR {
      By LagislationLibraryTab=By.cssSelector("a[href=\"/legislation-library\"]>span");
      By loaderMain =By.cssSelector( "div[class=\"absolute inset-0 w-full z-[1111] bg-base-white/60 flex items-center justify-center\"]");
      By profileIcon = By.cssSelector("#profileDD");
      By profileSetting = By.cssSelector("#profileDD>div>div");

      By signOutBtn =By.cssSelector( "#profileDD>div>button[class*=\"sign-out-btn\"]");
      By organizationSettingTabList = By.cssSelector("#organizationSettings");
      By legislationLibraryTab =By.cssSelector( "#legislationView");

      By sideBarSwitcher =By.cssSelector( "#sideBar>div");
      By riskRegisterTab = By.cssSelector("#risksView");
      By actionCenterTab =By.cssSelector( "#actionsView");
      By reportTab = By.cssSelector("#reportsView");
      By organizationDetailsST = By.cssSelector("#reportsView");
      By departmentsST =By.cssSelector( "#deptItem");
      By locationST = By.cssSelector("#locItem");
      By jobFunctionST = By.cssSelector("#jobFunctionItem");
      By userSetupST =By.cssSelector( "#userSetupItem");
      By manageSubscriptionST = By.cssSelector("#reportsView");
      By riskMatrixST = By.cssSelector("#reportsView");
      By dashboardTab=By.cssSelector("#dashboardView");
      By popUpAlert=By.cssSelector("div[role=\"alert\"]");
      By organizationSettingArrow=By.cssSelector("#organizationSettings>svg:last-of-type");
      By sideBarRadioBtn=By.cssSelector("#sideBar");
      By spinnerLoader=By.cssSelector("#dotSpinner");
      By confirmModel=By.cssSelector("#defaultModal");
      By appModel=By.cssSelector("#app-modal");

}
