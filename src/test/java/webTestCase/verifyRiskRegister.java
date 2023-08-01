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
        boolean isItSWOTRisk=false;
        riskRegisterPage.editRisk(isItSWOTRisk);
    }
    @Test(priority = 5, invocationCount = 1, enabled = true)
    public void verifyRejectRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.raiseNewRisk();
        riskRegisterPage.rejectRisk();
        riskRegisterPage.deleteRisk();
    }
    @Test(priority = 6, invocationCount = 1, enabled = true)
    public void verifyRaiseRiskWithSWOT() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.viewSWOT();
        riskRegisterPage.addRiskBySWOT();
        riskRegisterPage.backToRiskRegisterFromSWOT();
    }

    @Test(priority = 7, invocationCount = 1, enabled = true)
    public void verifyAcceptSWOTRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.acceptRisk();
    }
    @Test(priority = 8, invocationCount = 1, enabled = true)
    public void verifyEditSWOTRisk() throws InterruptedException {
        boolean isItSWOTRisk=true;
        riskRegisterPage.editRisk(isItSWOTRisk);
        riskRegisterPage.deleteRisk();
    }
    @Test(priority = 9, invocationCount = 1, enabled = true)
    public void verifyRejectSWOTRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.viewSWOT();
        riskRegisterPage.addRiskBySWOT();
        riskRegisterPage.backToRiskRegisterFromSWOT();
        riskRegisterPage.rejectRisk();
    }
    @Test(priority = 10, invocationCount = 1, enabled = true)
    public void verifyDeleteAllRisk() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.deleteRisk();
    }
    @Test(priority = 11, invocationCount = 10, enabled = false)
    public void addingIssuesForFilters() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.addNewRisk();
    }
    @Test(priority = 12, invocationCount = 1, enabled = false)
    public void verifyDepartmentFilter() throws InterruptedException {
        landingPage.goToRiskRegister();
        riskRegisterPage.verifyDepartmentFilter();
    }

}
