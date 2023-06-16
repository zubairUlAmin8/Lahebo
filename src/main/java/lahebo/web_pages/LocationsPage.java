package lahebo.web_pages;

import Utils.LogUtils;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.objectRepo.DepartmentPageOR;
import lahebo.objectRepo.LocationPageOR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class LocationsPage {
    WebDriver driver;

    public LocationsPage(WebDriver driver) {
        this.driver=driver;
    }
    public void addNewLocation(String locationName, String locationAddress, String phoneNumberForLocation,String keyContactManager) throws InterruptedException {
        WebUI.clickElement(LocationPageOR.addLocationBtn);
        WebUI.setText(LocationPageOR.locationNameIF, locationName);
        WebUI.setText(LocationPageOR.locationAddressIF, locationAddress);
        WebUI.setText(LocationPageOR.locationPhoneNumberIF, phoneNumberForLocation);
        WebUI.clickElement(LocationPageOR.keyContactmanagerList);
        WebUI.waitForElementVisible(LocationPageOR.keyContactmanagerListElements,20);
        boolean managerSelection = WebUI.selectOptionDynamic(LocationPageOR.keyContactmanagerListElements, keyContactManager);
        Assert.assertTrue(managerSelection, "Key contact Manager is not selected");
        WebUI.clickElement(LocationPageOR.addButton);
        WebUI.waitForElementVisible(LocationPageOR.allLocations,20);
    }
//
//    public void deleteDepartment(String dptName) throws InterruptedException {
//        Assert.assertTrue(verifyDepartment(dptName),"Department Not Exist" );
//        int index=getIndexOfDepartment(dptName);
//        String departmentOptionPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.departmentOptionBtn;
//        String deleteBtnPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.deleteDepartment;
//        WebUI.waitForPageLoaded();
//        WebUI.clickElement(WebUI.getByObjStringPath(departmentOptionPath));
//        WebUI.clickElement(WebUI.getByObjStringPath(deleteBtnPath));
//        WebUI.clickElement(DepartmentPageOR.confirmDeleteBtn);
//        WebUI.waitForElementVisible(DepartmentPageOR.allDepartments,20);
//    }
//
//    public void editDepartment(String dptEditName, String mngrName, String existingDptName) {
//        departmentNameEdit = dptEditName;
//        Assert.assertTrue(verifyDepartment(existingDptName),"Department Not Exist" );
//        int index=getIndexOfDepartment(existingDptName);
//        String departmentOptionPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.departmentOptionBtn;
//        String editBtnPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.editDepartment;
//        WebUI.clickElement(WebUI.getByObjStringPath(departmentOptionPath));
//        WebUI.clickElement(WebUI.getByObjStringPath(editBtnPath));
//        WebUI.clearText(DepartmentPageOR.departmentNameIF);
//        WebUI.setText(DepartmentPageOR.departmentNameIF, departmentNameEdit);
//        WebUI.clickElement(DepartmentPageOR.managerList);
//        WebUI.waitForElementVisible(DepartmentPageOR.managerListElements,20);
//        boolean managerSelection = WebUI.selectOptionDynamic(DepartmentPageOR.managerListElements, mngrName);
//        Assert.assertTrue(managerSelection, "Manager is not selected");
//        WebUI.clickElement(DepartmentPageOR.addButton);
//
//    }
//
    public boolean verifyLocation(String locationName) {
        LogUtils.info("verification of Location "+locationName+ "has been started");
        List<WebElement> elements = DriverManager.getDriver().findElements(LocationPageOR.allLocations);
        for (WebElement ele : elements) {
            LogUtils.info("Location Found:  "+ele.getText());

            if (ele.getText().equals(locationName)) {
                LogUtils.info("Location "+locationName+ "has been verified");
                return true;
            }

        }
        LogUtils.info("Location "+locationName+ " Not exist");
        return false;
    }
    public int getIndexOfDepartment(String locationName) {
        LogUtils.info("Location Index is being searched");
        int index = 0;
        List<WebElement> elements = DriverManager.getDriver().findElements(LocationPageOR.allLocations);
        for (WebElement ele : elements) {
            if (ele.getText().equals(locationName)) {
                break;
            }
            ++index;
        }

        LogUtils.info("Location Index is: "+index);
        return index;
    }

}
