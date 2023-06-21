package webTestCase;

import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class verifyJobFunctionST extends BaseTest {

    String jobFunctionName;
    String department;
    String EditJobFunctionName;

    //Open Browser and Hit the Base URL
    @Test(priority = 0)
    public void AddNewJobFunction() throws IOException, InterruptedException {
//        signInPage.signInUser();
        landingPage.waitForPageLoading();
        landingPage.goToJobFunctionsST();
        landingPage.waitForPageLoading();
         jobFunctionName = PropertiesHelpers.getValue("JOB_FUNCTION_NAME", "dataset");
        department = PropertiesHelpers.getValue("DEPARTMENT_NAME", "dataset");
        jobFunctionsPage.addNewJobFunction(jobFunctionName,department);
        landingPage.waitForPageLoading();
        Assert.assertTrue(jobFunctionsPage.verifyJobFunction(jobFunctionName),"Department Not Added" );

    }
    @Test(priority = 1)
    public void editJobFunction() throws IOException, InterruptedException {
        EditJobFunctionName = PropertiesHelpers.getValue("EDIT_JOB_FUNCTION_NAME", "dataset");
        jobFunctionsPage.editJobFunction(EditJobFunctionName,department , jobFunctionName);
        landingPage.waitForPageLoading();
        Assert.assertTrue(jobFunctionsPage.verifyJobFunction(EditJobFunctionName),"Job Function Not Edited" );

    }

    @Test(priority = 2)
    public void verifyDeleteJobFunction() throws IOException, InterruptedException {
        jobFunctionsPage.deleteJobFunction(EditJobFunctionName);
        landingPage.waitForPageLoading();
        Assert.assertFalse(jobFunctionsPage.verifyJobFunction(EditJobFunctionName),"Department Not Deleted" );
    }

}
