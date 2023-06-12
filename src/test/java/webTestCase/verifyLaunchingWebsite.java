package webTestCase;

import constants.FrameworkConstants;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class verifyLaunchingWebsite extends BaseTest {

    //Open Browser and Hit the Base URL
    @Test
    public void LaunchingWebsite() throws IOException {
//        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        basePage.loadPage(driver, FrameworkConstants.URL_RAHEBO);
        String title=driver.getTitle();
        Assert.assertEquals(title, FrameworkConstants.WEBSITE_TITLE);
    }
}
