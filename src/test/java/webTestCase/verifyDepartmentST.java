package webTestCase;

import Utils.LogUtils;
import driver.DriverManager;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;

public class verifyDepartmentST extends BaseTest {
    String departmentName;
    String managerName;
    String EditDepartmentName;

    //Open Browser and Hit the Base URL
    @Test(priority = 0)
    public void AddNewDepartment() throws IOException, InterruptedException {
        signInPage.signInUser();
        landingPage.goToDepartmentST();
        landingPage.waitForPageLoading();
        departmentName = PropertiesHelpers.getValue("DEPARTMENT_NAME", "dataset");
        managerName = "Sarah Johnson";
        departmentsPage.addNewDepartment(departmentName, managerName);
        landingPage.waitForPageLoading();
        Assert.assertTrue(departmentsPage.verifyDepartment(departmentName), "Department Not Added");
//
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
