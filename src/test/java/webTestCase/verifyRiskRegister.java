package webTestCase;

import keywords.WebUI;
import lahebo.objectRepo.LandingPageOR;
import org.testng.annotations.Test;

public class verifyRiskRegister extends BaseTest {
    @Test(priority = 1, invocationCount = 1, enabled = true)
    public void verifyAddNewRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.addNewRisk();
    }
    @Test(priority = 2, invocationCount = 1, enabled = true)
    public void verifyRaiseNewRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.raiseNewRisk();
    }
    @Test(priority = 3, invocationCount = 1, enabled = true)
    public void verifyAcceptRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.raiseNewRisk();
        riskRegisterPage.acceptRisk();
    }
    @Test(priority = 4, invocationCount = 1, enabled = true)
    public void verifyEditNewRisk() throws InterruptedException {
        riskRegisterPage.editRisk();
    }
    @Test(priority = 5, invocationCount = 1, enabled = true)
    public void verifyRejectRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.raiseNewRisk();
        riskRegisterPage.rejectRisk();
    }
    @Test(priority = 6, invocationCount = 1, enabled = true)
    public void verifyRaiseRiskWithSWOT() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.addRiskBySWOT();
    }
    @Test(priority = 7, invocationCount = 1, enabled = true)
    public void verifyDeleteAllRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.deleteRisk();
    }
}
