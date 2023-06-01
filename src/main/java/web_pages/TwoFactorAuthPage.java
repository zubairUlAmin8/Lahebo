package web_pages;

import Utils.Two2FActorAuthentication;
import Utils.waits;
import driver.DriverManager;
import helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import web_elements.SingInPageElements;
import web_elements.TwoFactorAuthElements;

public class TwoFactorAuthPage extends BasePage{
    TwoFactorAuthElements twoFactorAuthElements;
    WebDriver driver;
    public TwoFactorAuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean authentication() {
        twoFactorAuthElements = new TwoFactorAuthElements(DriverManager.getDriver());
        String secretKey = PropertiesHelpers.getValue("SECRET_KEY");
        System.out.println(DriverManager.getDriver());

        waits.waitForElements(DriverManager.getDriver(), twoFactorAuthElements.otpCode,  5000);
        twoFactorAuthElements.otpCode.sendKeys(Two2FActorAuthentication.getOptCode(secretKey));
        twoFactorAuthElements.submitBtn.click();
        return true;
    }
}
