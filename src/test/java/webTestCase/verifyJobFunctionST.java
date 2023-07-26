package webTestCase;

import Utils.LogUtils;
import constants.FrameworkConstants;
import dataprovider.DataProviderManager;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import models.DepartmentModel;
import models.JobFunctionModel;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Hashtable;

public class verifyJobFunctionST extends BaseTest {

    String jobFunctionName;
    String department;
    String EditJobFunctionName;
    ExcelHelpers excelHelpers = new ExcelHelpers();

    //Open Browser and Hit the Base URL
    @Test(priority = 0)
    public void AddNewJobFunction() throws IOException, InterruptedException {
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

    @Test(priority = 2,groups = {"dataDrivenTestCases"},dataProvider = "getJobFunctionDataHashTable", dataProviderClass = DataProviderManager.class)
    public void AddNewJobFunction(ITestContext testContext, Hashtable<String, String> data) throws IOException, InterruptedException {
        excelHelpers.setExcelFile(FrameworkConstants.EXCEL_DATA_FILE_PATH, "JobFunction");
        landingPage.waitForPageLoading();
        landingPage.goToJobFunctionsST();
        landingPage.waitForPageLoading();
        jobFunctionName =  data.get(JobFunctionModel.getJobFunctionName());
        department = PropertiesHelpers.getValue("DEPARTMENT_NAME", "dataset");
        jobFunctionsPage.addNewJobFunction(jobFunctionName,department);
        landingPage.waitForPageLoading();
        Assert.assertTrue(jobFunctionsPage.verifyJobFunction(jobFunctionName),"Job Function Not Added" );
    }
}
