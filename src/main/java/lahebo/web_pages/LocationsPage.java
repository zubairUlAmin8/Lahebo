package lahebo.web_pages;

import Utils.LogUtils;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.objectRepo.LocationPageOR;
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
        WebUI.clickElement(LocationPageOR.keyContactManagerList);
        WebUI.waitForElementVisible(LocationPageOR.keyContactManagerListElements,20);
        boolean managerSelection = WebUI.selectOptionDynamic(LocationPageOR.keyContactManagerListElements, keyContactManager);
        Assert.assertTrue(managerSelection, "Key contact Manager is not selected");
        WebUI.clickElement(LocationPageOR.addButton);
        WebUI.waitForElementVisible(LocationPageOR.allLocations,20);
    }

    public void deleteLocation(String locationName) throws InterruptedException {
        Assert.assertTrue(verifyLocation(locationName),"Department Not Exist" );
        int index=getIndexOfLocation(locationName);
        String locationOptionPath=LocationPageOR.LocationCard+index+LocationPageOR.LocationOptionBtn;
        String deleteBtnPath=LocationPageOR.LocationCard+index+LocationPageOR.deleteLocation;
        WebUI.waitForPageLoaded();
        WebUI.clickElement(WebUI.getByObjStringPath(locationOptionPath));
        WebUI.clickElement(WebUI.getByObjStringPath(deleteBtnPath));
        WebUI.clickElement(LocationPageOR.confirmDeleteBtn);
        WebUI.waitForElementVisible(LocationPageOR.allLocations,20);
    }

    public void editLocation(String LocationEditName, String keyContactManager, String existingLocationName) {
        Assert.assertTrue(verifyLocation(existingLocationName),"Department Not Exist" );
        int index=getIndexOfLocation(existingLocationName);
        String locationOptionPath=LocationPageOR.LocationCard+index+LocationPageOR.LocationOptionBtn;
        String editBtnPath=LocationPageOR.LocationCard+index+LocationPageOR.editLocation;
        WebUI.clickElement(WebUI.getByObjStringPath(locationOptionPath));
        WebUI.clickElement(WebUI.getByObjStringPath(editBtnPath));
        WebUI.clearText(LocationPageOR.locationNameIF);
        WebUI.setText(LocationPageOR.locationNameIF, LocationEditName);
        WebUI.clickElement(LocationPageOR.keyContactManagerList);
        WebUI.waitForElementVisible(LocationPageOR.keyContactManagerListElements,20);
        boolean managerSelection = WebUI.selectOptionDynamic(LocationPageOR.keyContactManagerListElements, keyContactManager);
        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(LocationPageOR.addButton);

    }
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
    public int getIndexOfLocation(String locationName) {
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
