package lahebo.web_pages;

import Utils.waits;
import lahebo.web_elements.DashboardElements;
import lahebo.web_elements.SubscribePlanElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class SubscribePlanPage extends BasePage{
    SubscribePlanElements subscribePlanElements;
    WebDriver driver;
    public SubscribePlanPage(WebDriver driver) {
        this.driver = driver;
        subscribePlanElements = new SubscribePlanElements(driver);

    }

    public void choosePlan() {
        waits.waitForElements(driver, subscribePlanElements.liteSubScribeBtn, 5);
        String plan = "Lite";
        if (plan.equals("Lite")) {
            subscribePlanElements.liteSubScribeBtn.click();
            waits.waitForElements(driver, subscribePlanElements.noOfUserList, 5);
            Select noOfUserList = new Select(subscribePlanElements.noOfUserList);
            noOfUserList.selectByIndex(1);
            subscribePlanElements.buyNowBtn.click();
        }
    }

    public boolean confirmPlan() {
        waits.waitForVisibilityOfItem(driver, subscribePlanElements.confirmAmount, 10);
        String amount = subscribePlanElements.confirmAmount.getText();
        if (amount.equals("2400.00 AUD")) {
            subscribePlanElements.buyNowConfirmBtn.click();
            return true;
        } else {
            return false;
        }
    }
}
