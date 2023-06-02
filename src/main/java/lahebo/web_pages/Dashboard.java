package lahebo.web_pages;

import Utils.Two2FActorAuthentication;
import Utils.waits;
import lahebo.web_elements.DashboardElements;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;


public class Dashboard extends BasePage{
    DashboardElements dashboardElements;
    WebDriver driver;
    public Dashboard(WebDriver driver) {
        this.driver = driver;
        dashboardElements = new DashboardElements(driver);

    }
    public boolean verifyDashboardDisplay() {
        waits.waitForElements(driver, dashboardElements.profileIcon, 5000);
        dashboardElements.profileIcon.click();
        return true;
    }
}
