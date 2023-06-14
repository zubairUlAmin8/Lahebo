package webTestCase;

import org.testng.annotations.Test;

import java.io.IOException;

public class verifyDepartmentST extends BaseTest {

    //Open Browser and Hit the Base URL
    @Test
    public void AddNewDepartment() throws IOException, InterruptedException {
        signInPage.signInUser();
        landingPage.goToDepartmentST();
        landingPage.waitForPageLoading();
        departmentsPage.addNewDepartment();

    }

}
