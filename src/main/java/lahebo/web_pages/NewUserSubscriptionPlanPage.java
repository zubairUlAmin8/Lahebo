package lahebo.web_pages;

import Utils.waits;
import lahebo.web_elements.ForgetPasswordElements;
import lahebo.web_elements.NewUserSubscriptionPlanElements;
import org.openqa.selenium.WebDriver;

public class NewUserSubscriptionPlanPage {
    NewUserSubscriptionPlanElements newUserSubscriptionPlanElements;
    WebDriver driver;
//    SingInPageElements singInPageElements = new SingInPageElements(driver);

    public NewUserSubscriptionPlanPage(WebDriver driver) {
        this.driver = driver;
        newUserSubscriptionPlanElements = new NewUserSubscriptionPlanElements(driver);

    }

    public void userSignOut() {
        waits.waitForElements(driver, newUserSubscriptionPlanElements.singOutBtn, 5);
        newUserSubscriptionPlanElements.singOutBtn.click();
    }

}


