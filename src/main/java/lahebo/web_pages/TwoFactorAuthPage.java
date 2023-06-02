package lahebo.web_pages;

import Utils.Two2FActorAuthentication;
import Utils.waits;
import helpers.PropertiesHelpers;
import lahebo.web_elements.TwoFactorAuthElements;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class TwoFactorAuthPage extends BasePage{
    TwoFactorAuthElements twoFactorAuthElements;
    WebDriver driver;
    public TwoFactorAuthPage(WebDriver driver) {
        this.driver = driver;
        twoFactorAuthElements = new TwoFactorAuthElements(driver);


    }
    public boolean authentication() throws InterruptedException {
        String secretKey = PropertiesHelpers.getValue("SECRET_KEY");
        waits.waitForElements(driver, twoFactorAuthElements.otpCode,  5000);
        String code = Two2FActorAuthentication.getOptCode(secretKey);
        twoFactorAuthElements.otpCode.sendKeys(code);
        twoFactorAuthElements.submitBtn.click();
        if (code.length() < 6) {
            if (verifyCodeLength()) {
                return false;
            } else {
                return true;
            }
        } else {
            if (verifyInvalidOTP()) {
                return false;
            } else {
                return true;
            }

        }
    }

    public boolean verifyInvalidOTP() {
        try {
            waits.waitForVisibilityOfItem(driver, twoFactorAuthElements.errorPopUp, 5);
            if (twoFactorAuthElements.errorPopUp.isDisplayed()) {
                return true;
            }

        } catch (TimeoutException id) {
            System.out.println("exception  is  here"+id);
            return false;

        }

        return false;

    } public boolean verifyCodeLength() {
        waits.waitForVisibilityOfItem(driver,twoFactorAuthElements.validationErrorMsg,5);
        if (twoFactorAuthElements.validationErrorMsg.isDisplayed()) {
            return true;
        }
        return false;
    }
}
