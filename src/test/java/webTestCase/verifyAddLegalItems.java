package webTestCase;

import base_test.BaseTest;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import web_pages.LandingPage;

import java.io.IOException;

public class verifyAddLegalItems extends BaseTest {

    //Open Browser and Hit the Base URL
    @Test
    public void AddLegalItems() throws IOException, InterruptedException {
        landingPage.goToTab();
    }

}
