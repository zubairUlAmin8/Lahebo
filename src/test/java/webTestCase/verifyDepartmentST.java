package webTestCase;

import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;

public class verifyDepartmentST extends BaseTest {

    //Open Browser and Hit the Base URL
    @Test()
    public void AddNewDepartment() throws IOException, InterruptedException {
        signInPage.signInUser();
        landingPage.goToDepartmentST();
        landingPage.waitForPageLoading();
        departmentsPage.addNewDepartment();
        landingPage.waitForPageLoading();
        Assert.assertTrue(departmentsPage.verifyDepartment("DEPART45MENT_NAME"),"Department Not Added" );
//
    }
    @Test()
    public void verifyDeleteDepartment() throws IOException, InterruptedException {
        departmentsPage.deleteDepartment();
        landingPage.waitForPageLoading();
        Assert.assertFalse(departmentsPage.verifyDepartment("DEPART45MENT_NAME"),"Department Not Added" );

    }


}
