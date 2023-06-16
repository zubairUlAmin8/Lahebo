package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import constants.TestDataConstants;
import driver.DriverManager;
import helpers.PropertiesHelpers;
import keywords.WebUI;
import lahebo.objectRepo.DepartmentPageOR;
import lahebo.objectRepo.LandingPageOR;
import lahebo.path_repo.LandingPagePR;
import lahebo.web_elements.LandingPageElements;
import lombok.extern.java.Log;
import messages.SuccessMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class DepartmentsPage {
    WebDriver driver;
    String departmentName;
    String departmentNameEdit;
    public DepartmentsPage(WebDriver driver) {
        this.driver=driver;
    }
    public void addNewDepartment(String dptName, String mngrName) throws InterruptedException {
        departmentName = dptName;
        WebUI.clickElement(DepartmentPageOR.addDepartmentBtn);
        WebUI.setText(DepartmentPageOR.departmentNameIF, departmentName);
        WebUI.clickElement(DepartmentPageOR.managerList);
        WebUI.waitForElementVisible(DepartmentPageOR.managerListElements,20);
        boolean managerSelection = WebUI.selectOptionDynamic(DepartmentPageOR.managerListElements, mngrName);
        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(DepartmentPageOR.addButton);
        WebUI.waitForElementVisible(DepartmentPageOR.allDepartments,20);
    }

    public void deleteDepartment(String dptName) throws InterruptedException {
        Assert.assertTrue(verifyDepartment(dptName),"Department Not Exist" );
        int index=getIndexOfDepartment(dptName);
        String departmentOptionPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.departmentOptionBtn;
        String deleteBtnPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.deleteDepartment;
        WebUI.waitForPageLoaded();
        WebUI.clickElement(WebUI.getByObjStringPath(departmentOptionPath));
        WebUI.clickElement(WebUI.getByObjStringPath(deleteBtnPath));
        WebUI.clickElement(DepartmentPageOR.confirmDeleteBtn);
        WebUI.waitForElementVisible(DepartmentPageOR.allDepartments,20);
    }

    public void editDepartment(String dptEditName, String mngrName, String existingDptName) {
        departmentNameEdit = dptEditName;
        Assert.assertTrue(verifyDepartment(existingDptName),"Department Not Exist" );
        int index=getIndexOfDepartment(existingDptName);
        String departmentOptionPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.departmentOptionBtn;
        String editBtnPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.editDepartment;
        WebUI.clickElement(WebUI.getByObjStringPath(departmentOptionPath));
        WebUI.clickElement(WebUI.getByObjStringPath(editBtnPath));
        WebUI.clearText(DepartmentPageOR.departmentNameIF);
        WebUI.setText(DepartmentPageOR.departmentNameIF, departmentNameEdit);
        WebUI.clickElement(DepartmentPageOR.managerList);
        WebUI.waitForElementVisible(DepartmentPageOR.managerListElements,20);
        boolean managerSelection = WebUI.selectOptionDynamic(DepartmentPageOR.managerListElements, mngrName);
        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(DepartmentPageOR.addButton);

    }

    public boolean verifyDepartment(String departmentName) {
        LogUtils.info("verification of Department "+departmentName+ "has been started");
        List<WebElement> elements = DriverManager.getDriver().findElements(DepartmentPageOR.allDepartments);
        for (WebElement ele : elements) {
            LogUtils.info("Department Found:  "+ele.getText());

            if (ele.getText().equals(departmentName)) {
                LogUtils.info("Department "+departmentName+ "has been verified");
                return true;
            }

        }
        LogUtils.info("Department "+departmentName+ " Not exist");
        return false;
    }
    public int getIndexOfDepartment(String departmentName) {
        LogUtils.info("Department Index is being searched");
        int index = 0;
        List<WebElement> elements = DriverManager.getDriver().findElements(DepartmentPageOR.allDepartments);
        for (WebElement ele : elements) {
            if (ele.getText().equals(departmentName)) {
                break;
            }
            ++index;
        }

        LogUtils.info("Department Index is: "+index);
        return index;
    }

}
