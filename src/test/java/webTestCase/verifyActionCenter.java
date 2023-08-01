package webTestCase;

import org.testng.annotations.Test;

public class verifyActionCenter extends BaseTest {
    @Test(priority = 1, invocationCount = 1, enabled = true)
    public void verifyAddNewRisk() throws InterruptedException {
        landingPage.goToActionCenter();
        riskRegisterPage.addNewRisk();
    }


}
