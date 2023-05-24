package web_pages;

import Utils.Two2FActorAuthentication;
import Utils.waits;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebElement;
import web_elements.DashboardElements;
import web_elements.TwoFactorAuthElements;

public class Dashboard extends BasePage{
    DashboardElements dashboardElements;

    public boolean verifyDashboardDisplay() {
        dashboardElements = new DashboardElements(driver);
        waits.waitForElements(driver, dashboardElements.profileIcon, 5000);
        return true;
    }
}
