package webTestCase;

import base_test.BaseTest;
import dataprovider.DataProviderManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class verifyForgetPassword extends BaseTest {

    @Test(priority = 1)
    public void goToForgetPage() throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        String userName = PropertiesHelpers.getValue("New_User_UserName");
        basePage.loadPage(driver, url_Address);
        signInPage.goToForgetPasswordPage();
    }
    @Test(priority = 2)
    public void submitRequestForPasswordChange() throws InterruptedException {
        String userName = PropertiesHelpers.getValue("New_User_UserName");
        System.out.println(userName);
        forgetPasswordPage.submitRequest(userName);
    }
}