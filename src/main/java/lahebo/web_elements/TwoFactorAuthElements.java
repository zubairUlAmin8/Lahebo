package lahebo.web_elements;

import lahebo.path_repo.TwoFactorAuthPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TwoFactorAuthElements {
    @FindBy(css = TwoFactorAuthPR.otpCode)
    public WebElement otpCode;
    @FindBy(css = TwoFactorAuthPR.submitBtn)
    public WebElement submitBtn;
    @FindBy(css = TwoFactorAuthPR.validationErrorMsg)
    public WebElement validationErrorMsg;
    @FindBy(css = TwoFactorAuthPR.errorPopUp)
    public WebElement errorPopUp;



    WebDriver driver;

    public TwoFactorAuthElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
