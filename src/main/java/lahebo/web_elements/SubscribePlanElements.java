package lahebo.web_elements;

import lahebo.path_repo.SubscriptionPlanPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubscribePlanElements {
    @FindBy(css = SubscriptionPlanPR.liteSubScribeBtn)
    public WebElement liteSubScribeBtn;
    @FindBy(css = SubscriptionPlanPR.noOfUserList)
    public WebElement noOfUserList;
    @FindBy(css = SubscriptionPlanPR.buyNowBtn)
    public WebElement buyNowBtn;
    @FindBy(css = SubscriptionPlanPR.confirmAmount)
    public WebElement confirmAmount;
    @FindBy(css = SubscriptionPlanPR.buyNowConfirmBtn)
    public WebElement buyNowConfirmBtn;
    WebDriver driver;

    public SubscribePlanElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
