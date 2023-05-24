package webTestCase;

import Utils.utility;
import base_test.BaseTest;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import web_pages.Dashboard;
import web_pages.SignInPage;
import web_pages.TwoFactorAuthPage;

import java.io.IOException;

public class verifyUserSignIn extends BaseTest {
    SignInPage signInPage = new SignInPage();
    Dashboard dashboard = new Dashboard();
    TwoFactorAuthPage twoFactorAuthPage = new TwoFactorAuthPage();
    @Test
    public void superAdminSignIn() throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        basePage.loadPage(driver,url_Address);
        signInPage.signIn();
        twoFactorAuthPage.authentication();
        dashboard.verifyDashboardDisplay();
    }
}
