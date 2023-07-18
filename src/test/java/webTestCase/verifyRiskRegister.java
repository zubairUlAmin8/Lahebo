package webTestCase;

import keywords.WebUI;
import lahebo.objectRepo.LandingPageOR;
import org.testng.annotations.Test;

public class verifyRiskRegister extends BaseTest {
    @Test(priority = 0, enabled = true)
    public void login() throws InterruptedException {
//        signInPage.signInUser();
    }
    @Test(priority = 1, invocationCount = 10, enabled = true)
    public void verifyAddNewRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.addNewRisk();
    }
    @Test(priority = 1, invocationCount = 1, enabled = true)
    public void verifyDeleteAllRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.deleteRisk();
    }



}
