package web_pages;

import Utils.waits;
import helpers.PropertiesHelpers;
import web_elements.SingInPageElements;

public class SignInPage extends BasePage{
    SingInPageElements singInPageElements;

    public boolean signIn() {
        singInPageElements = new SingInPageElements(driver);
        String userName = PropertiesHelpers.getValue("SIGNIN_USERNAME");
        String password = PropertiesHelpers.getValue("SIGNIN_PASSWORD");
        if (driver == null) {
            System.out.println("Driver is null in pages");
        } else {
            System.out.println("driver is not in pages");
        }

        waits.waitForElements(driver,singInPageElements.userName,5000);
        singInPageElements.userName.sendKeys(userName);
        singInPageElements.password.sendKeys(password);
        singInPageElements.signInBtn.click();
        return true;
    }
}
