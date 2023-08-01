package webTestCase;

import Utils.LogUtils;
import Utils.fakerUtils;
import constants.FrameworkConstants;
import dataprovider.DataProviderManager;
import driver.DriverManager;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import models.DepartmentModel;
import models.SignInModel;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;
import java.util.Hashtable;

public class verifyDepartmentST extends BaseTest {
    String departmentName;
    String randomDepartmentName;
    String managerName;
    String EditDepartmentName;
    ExcelHelpers excelHelpers = new ExcelHelpers();

    //Open Browser and Hit the Base URL
    @Test(priority = 0, invocationCount = 1)
    public void AddNewDepartment(ITestContext testContext) throws IOException, InterruptedException {
        landingPage.goToDepartmentST();
        landingPage.waitForPageLoading();
        departmentName = PropertiesHelpers.getValue("DEPARTMENT_NAME", "dataset");
        managerName = "Sarah Johnson";
        int currentCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();
        LogUtils.info("AddNewDepartment Invocation"+currentCount);
        if (currentCount > 1) {
            randomDepartmentName = fakerUtils.generateRandomDepartment();
            departmentsPage.addNewDepartment(randomDepartmentName, managerName);
            landingPage.waitForPageLoading();
            Assert.assertTrue(departmentsPage.verifyDepartment(randomDepartmentName), "Department Not Added");
            LogUtils.info("Department "+randomDepartmentName+" is added");

        } else {
            departmentsPage.addNewDepartment(departmentName, managerName);
            landingPage.waitForPageLoading();
            Assert.assertTrue(departmentsPage.verifyDepartment(departmentName), "Department Not Added");
            LogUtils.info("Department "+randomDepartmentName+" is added");

        }
    }

    @Test(priority = 1)
    public void verifyEditDepartment() throws IOException, InterruptedException {
        EditDepartmentName = PropertiesHelpers.getValue("EDIT_DEPARTMENT_NAME", "dataset");
        managerName = "Sarah Johnson";
        departmentsPage.editDepartment(EditDepartmentName, managerName, departmentName);
        landingPage.waitForPageLoading();
        Assert.assertTrue(departmentsPage.verifyDepartment(EditDepartmentName), "Department Not Edited");

    }

    @Test(priority = 2)
    public void verifyDeleteDepartment() throws IOException, InterruptedException {
        departmentsPage.deleteDepartment(EditDepartmentName);
        landingPage.waitForPageLoading();
        Assert.assertFalse(departmentsPage.verifyDepartment(EditDepartmentName), "Department Not Deleted");
    }


    @Test(priority = 2,groups = {"dataDrivenTestCases"},dataProvider = "getDepartmentDataHashTable", dataProviderClass = DataProviderManager.class)
    public void AddNewDepartmentDataDriven(ITestContext testContext, Hashtable<String, String> data) throws IOException, InterruptedException {
        excelHelpers.setExcelFile(FrameworkConstants.EXCEL_DATA_FILE_PATH, "Departments");
        landingPage.goToDepartmentST();
        landingPage.waitForPageLoading();
        departmentName =  data.get(DepartmentModel.getDepartmentName());
        managerName = "Sarah Johnson";
        departmentsPage.addNewDepartment(departmentName, managerName);
        landingPage.waitForPageLoading();
        Assert.assertTrue(departmentsPage.verifyDepartment(departmentName), "Department Not Added");
        LogUtils.info("Department "+departmentName+" is added");
    }

}
