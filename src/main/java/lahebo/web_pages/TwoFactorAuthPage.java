package lahebo.web_pages;

import Utils.Two2FActorAuthentication;
import Utils.waits;
import helpers.PropertiesHelpers;
import lahebo.web_elements.TwoFactorAuthElements;

public class TwoFactorAuthPage extends BasePage{
    TwoFactorAuthElements twoFactorAuthElements;
    public boolean authentication() {
        twoFactorAuthElements = new TwoFactorAuthElements(driver);
        String secretKey = PropertiesHelpers.getValue("SECRET_KEY");
        waits.waitForElements(driver, twoFactorAuthElements.otpCode,  5000);
        twoFactorAuthElements.otpCode.sendKeys(Two2FActorAuthentication.getOptCode(secretKey));
        twoFactorAuthElements.submitBtn.click();
        return true;
    }
}
