package webTestCase;

import base_test.BaseTest;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import report.ExtentReportManager;

import java.io.IOException;

public class verifyLaunchingWebsite extends BaseTest {

    //Open Browser and Hit the Base URL
    @Test
    public void LaunchingWebsite() throws IOException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        basePage.loadPage(driver,url_Address);
        String title=driver.getTitle();
        Assert.assertEquals(title, PropertiesHelpers.getValue("WEBSITE_TITLE"));
    }

}
