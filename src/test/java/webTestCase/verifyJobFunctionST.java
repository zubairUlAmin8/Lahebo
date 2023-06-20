package webTestCase;

import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class verifyJobFunctionST extends BaseTest {

    String jobFunctionName;
    String department;
    String EditDepartmentName;

    //Open Browser and Hit the Base URL
    @Test(priority = 0)
    public void AddNewJobFunction() throws IOException, InterruptedException {
        signInPage.signInUser();
        landingPage.goToJobFunctionsST();
        landingPage.waitForPageLoading();
         jobFunctionName = PropertiesHelpers.getValue("JOB_FUNCTION_NAME", "dataset");
        department = PropertiesHelpers.getValue("DEPARTMENT_NAME", "dataset");
        jobFunctionsPage.addNewJobFunction(jobFunctionName,department);
        landingPage.waitForPageLoading();
        Assert.assertTrue(jobFunctionsPage.verifyJobFunction(jobFunctionName),"Department Not Added" );
//
    }
//    @Test(priority = 1)
//    public void verifyEditDepartment() throws IOException, InterruptedException {
//         EditDepartmentName = PropertiesHelpers.getValue("EDIT_DEPARTMENT_NAME", "dataset");
//         managerName = "Sarah Johnson";
//        departmentsPage.editDepartment(EditDepartmentName,managerName,departmentName );
//        landingPage.waitForPageLoading();
//        Assert.assertTrue(departmentsPage.verifyDepartment(EditDepartmentName),"Department Not Edited" );
//
//    }
//
//    @Test(priority = 2)
//    public void verifyDeleteDepartment() throws IOException, InterruptedException {
//        departmentsPage.deleteDepartment(EditDepartmentName);
//        landingPage.waitForPageLoading();
//        Assert.assertFalse(departmentsPage.verifyDepartment(EditDepartmentName),"Department Not Deleted" );
//    }

}
