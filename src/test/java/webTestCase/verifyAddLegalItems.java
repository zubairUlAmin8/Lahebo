package webTestCase;

import org.testng.annotations.Test;

import java.io.IOException;

public class verifyAddLegalItems extends BaseTest {

    //Open Browser and Hit the Base URL
    @Test
    public void AddLegalItems() throws IOException, InterruptedException {
        landingPage.goToTab();
    }

}
