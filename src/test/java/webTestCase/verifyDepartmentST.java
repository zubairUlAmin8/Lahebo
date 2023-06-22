package webTestCase;

import Utils.LogUtils;
import Utils.fakerUtils;
import driver.DriverManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;

public class verifyDepartmentST extends BaseTest {
    String departmentName;
    String randomDepartmentName;
    String managerName;
    String EditDepartmentName;

    //Open Browser and Hit the Base URL
    @Test(priority = 0, invocationCount = 2)
    public void AddNewDepartment(ITestContext testContext) throws IOException, InterruptedException {
//        signInPage.signInUser();
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


}
