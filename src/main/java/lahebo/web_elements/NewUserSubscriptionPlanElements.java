package lahebo.web_elements;

import lahebo.path_repo.ForgetPasswordPR;
import lahebo.path_repo.NewUserSubscriptionPlanPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewUserSubscriptionPlanElements {
    @FindBy(css = NewUserSubscriptionPlanPR.singOutBtn)
    public WebElement singOutBtn;


    WebDriver driver;

    public NewUserSubscriptionPlanElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
