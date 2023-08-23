package lahebo.web_pages;

import Utils.waits;
import lahebo.web_elements.ForgetPasswordElements;
import lahebo.web_elements.ResetPasswordElements;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    ResetPasswordElements resetPasswordElements;
    WebDriver driver;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        resetPasswordElements = new ResetPasswordElements(driver);

    }
    public void fillResetPasswordForm(String optCode, String newPassword) {
        waits.waitForElements(driver, resetPasswordElements.OTPCodeInputField, 5);
        resetPasswordElements.OTPCodeInputField.sendKeys(optCode);
        resetPasswordElements.newPWDInputField.sendKeys(newPassword);
        resetPasswordElements.ConfirmNewPWDInputField.sendKeys(newPassword);
        resetPasswordElements.resetSubmitBtn.click();
    }
    public void fillNewUserResetPasswordForm(String oldPassword, String newPassword) {
        waits.waitForElements(driver, resetPasswordElements.OTPCodeInputField, 5);
        resetPasswordElements.oldPasswordInputField.sendKeys(oldPassword);
        resetPasswordElements.newPWDInputField.sendKeys(newPassword);
        resetPasswordElements.ConfirmNewPWDInputField.sendKeys(newPassword);
        resetPasswordElements.resetSubmitBtn.click();
    }
}


