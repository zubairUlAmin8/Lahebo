package lahebo.elements;

import org.openqa.selenium.By;

public interface landingElements {
      By LagislationLibraryTab=By.cssSelector("a[href=\"/legislation-library\"]>span");
      By loaderMain =By.cssSelector( "div[class=\"absolute inset-0 w-full z-[1111] bg-base-white/60 flex items-center justify-center\"]");
      By profileIcon = By.cssSelector("#headlessui-menu-button-5");
      By signOutBtn =By.cssSelector( "#headlessui-menu-item-12");
      By organizationSettingTabList = By.cssSelector("#organizationSettings");
      By legislationLibraryTab =By.cssSelector( "#legislationView");
      By riskRegisterTab = By.cssSelector("#risksView");
      By actionCenterTab =By.cssSelector( "#actionsView");
      By reportTab = By.cssSelector("#reportsView");
      By organizationDetailsST = By.cssSelector("#reportsView");
      By departmentsST =By.cssSelector( "#deptItem");
      By locationST = By.cssSelector("#reportsView");
      By jobFunctionST = By.cssSelector("#reportsView");
      By userSetupST =By.cssSelector( "#reportsView");
      By manageSubscriptionST = By.cssSelector("#reportsView");
      By riskMatrixST = By.cssSelector("#reportsView");
      By dashboardTab=By.cssSelector("input[type=\"text\"]");

}
