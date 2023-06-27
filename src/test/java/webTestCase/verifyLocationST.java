package webTestCase;

import Utils.LogUtils;
import Utils.fakerUtils;
import com.github.javafaker.PhoneNumber;
import constants.FrameworkConstants;
import dataprovider.DataProviderManager;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import models.DepartmentModel;
import models.LocationModel;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Hashtable;

public class verifyLocationST extends BaseTest {
    String locationName;
    String keyContact;
    String locationAddress;
    String phoneNumberForLocation;
    String EditLocationName;
    ExcelHelpers excelHelpers = new ExcelHelpers();

    //Open Browser and Hit the Base URL
    @Test(priority = 0)
    public void AddNewLocation() throws IOException, InterruptedException {
//        signInPage.signInUser();
        landingPage.waitForPageLoading();
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
    @Test(priority = 1, enabled = true)
    public void verifyEditLocation() throws IOException, InterruptedException {
        EditLocationName = PropertiesHelpers.getValue("EDIT_LOCATION_NAME", "dataset");
        keyContact = "Sarah Johnson";
        locationsPage.editLocation(EditLocationName,keyContact,locationName );
        landingPage.waitForPageLoading();
        Assert.assertTrue(locationsPage.verifyLocation(EditLocationName),"Location Not Edited" );

    }
    @Test(priority = 2, enabled = true)
    public void verifyDeleteLocation() throws IOException, InterruptedException {
        locationsPage.deleteLocation(EditLocationName);
        landingPage.waitForPageLoading();
        Assert.assertFalse(locationsPage.verifyLocation(EditLocationName),"Location Not Deleted" );
    }
    @Test(priority = 2,groups = {"dataDrivenTestCases"},dataProvider = "getLocationDataHashTable", dataProviderClass = DataProviderManager.class)
    public void AddNewLocationDataDriven(ITestContext testContext, Hashtable<String, String> data) throws IOException, InterruptedException {
        excelHelpers.setExcelFile(FrameworkConstants.EXCEL_DATA_FILE_PATH, "Locations");
        landingPage.waitForPageLoading();
        landingPage.goToLocationST();
        landingPage.waitForPageLoading();
        locationName =  data.get(LocationModel.getLocationName());
        locationAddress = PropertiesHelpers.getValue("LOCATION_ADDRESS", "dataset");
        phoneNumberForLocation = fakerUtils.generateRandomAustralianPhoneNumber();
        PropertiesHelpers.setValue("LOCATION_PHONE_NUMBER", phoneNumberForLocation, "dataset");
        keyContact = PropertiesHelpers.getValue("MANAGER_NAME", "dataset");
        locationsPage.addNewLocation(locationName,locationAddress, phoneNumberForLocation,keyContact);
        landingPage.waitForPageLoading();
        Assert.assertTrue(locationsPage.verifyLocation(locationName),"Location "+locationName+" Not Added" );
    }

}
