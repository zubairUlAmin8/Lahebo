package lahebo.web_pages;

import Utils.waits;
import lahebo.web_elements.DashboardElements;

public class Dashboard extends BasePage{
    DashboardElements dashboardElements;

    public boolean verifyDashboardDisplay() {
        dashboardElements = new DashboardElements(driver);
        waits.waitForElements(driver, dashboardElements.profileIcon, 5000);
        dashboardElements.profileIcon.click();
        return true;
    }
}
