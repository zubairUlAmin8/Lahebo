package webTestCase;

import Utils.fakerUtils;
import com.github.javafaker.PhoneNumber;
import helpers.PropertiesHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class verifyLocationST extends BaseTest {
    String locationName;
    String keyContact;
    String locationAddress;
    String phoneNumberForLocation;
    String EditLocationName;

    //Open Browser and Hit the Base URL
    @Test(priority = 0)
    public void AddNewLocation() throws IOException, InterruptedException {
        signInPage.signInUser();
        landingPage.goToLocationST();
        landingPage.waitForPageLoading();
        locationName = PropertiesHelpers.getValue("LOCATION_NAME", "dataset");
        locationAddress = PropertiesHelpers.getValue("LOCATION_ADDRESS", "dataset");
        phoneNumberForLocation = fakerUtils.generateRandomAustralianPhoneNumber();
        PropertiesHelpers.setValue("LOCATION_PHONE_NUMBER", phoneNumberForLocation, "dataset");
        keyContact = PropertiesHelpers.getValue("MANAGER_NAME", "dataset");
        locationsPage.addNewLocation(locationName,locationAddress, phoneNumberForLocation,keyContact);
        landingPage.waitForPageLoading();
        Assert.assertTrue(locationsPage.verifyLocation(locationName),"Location "+locationName+" Not Added" );

    }
//    @Test(priority = 1, enabled = false)
//    public void verifyEditDepartment() throws IOException, InterruptedException {
//         EditDepartmentName = PropertiesHelpers.getValue("EDIT_DEPARTMENT_NAME", "dataset");
//         managerName = "Sarah Johnson";
//        departmentsPage.editDepartment(EditDepartmentName,managerName,departmentName );
//        landingPage.waitForPageLoading();
//        Assert.assertTrue(departmentsPage.verifyDepartment(EditDepartmentName),"Department Not Edited" );
//
//    }
//
//    @Test(priority = 2, enabled = false)
//    public void verifyDeleteDepartment() throws IOException, InterruptedException {
//        departmentsPage.deleteDepartment(EditDepartmentName);
//        landingPage.waitForPageLoading();
//        Assert.assertFalse(departmentsPage.verifyDepartment(EditDepartmentName),"Department Not Deleted" );
//    }

}
