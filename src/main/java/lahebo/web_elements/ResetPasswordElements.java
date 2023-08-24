package lahebo.web_elements;

import lahebo.path_repo.ForgetPasswordPR;
import lahebo.path_repo.ResetPasswordPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordElements {
    @FindBy(css = ResetPasswordPR.OTPCodeInputField)
    public WebElement OTPCodeInputField;
    @FindBy(css = ResetPasswordPR.newPWDInputField)
    public WebElement newPWDInputField;
    @FindBy(css = ResetPasswordPR.oldPasswordInputField)
    public WebElement oldPasswordInputField;
    @FindBy(css = ResetPasswordPR.ConfirmNewPWDInputField)
    public WebElement ConfirmNewPWDInputField;
    @FindBy(css = ResetPasswordPR.resetSubmitBtn)
    public WebElement resetSubmitBtn;

    WebDriver driver;

    public ResetPasswordElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
