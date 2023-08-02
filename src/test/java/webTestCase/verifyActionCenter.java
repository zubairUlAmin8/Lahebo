package webTestCase;

import org.testng.annotations.Test;

public class verifyActionCenter extends BaseTest {
    @Test(priority = 1, invocationCount = 5, enabled = true)
    public void verifyAddNewAction() throws InterruptedException {
        landingPage.goToActionCenter();
        actionCenterPage.addNewAction();
    }
    @Test(priority = 2, invocationCount = 1, enabled = true)
    public void verifyDeleteAllAction() throws InterruptedException {
        landingPage.goToActionCenter();
        actionCenterPage.deleteAllAction();
    }
}
