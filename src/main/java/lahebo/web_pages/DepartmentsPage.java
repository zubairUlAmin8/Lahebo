package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import driver.DriverManager;
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
    public DepartmentsPage(WebDriver driver) {
        this.driver=driver;
    }
    public void addNewDepartment() throws InterruptedException {

        WebUI.clickElement(DepartmentPageOR.addDepartmentBtn);
        WebUI.setText(DepartmentPageOR.departmentNameIF, "DEPART45MENT_NAME");
        WebUI.clickElement(DepartmentPageOR.managerList);
        WebUI.waitForElementVisible(DepartmentPageOR.managerListElements,20);
        boolean managerSelection = WebUI.selectOptionDynamic(DepartmentPageOR.managerListElements, "Sarah Johnson");
        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(DepartmentPageOR.addButton);
        WebUI.waitForElementVisible(DepartmentPageOR.allDepartments,20);
        WebUI.waitForPageLoaded();
//        Assert.assertTrue(verifyDepartment("DEPART45MENT_NAME"),"Department Not Added" );
    }

    public void deleteDepartment() throws InterruptedException {
        Assert.assertTrue(verifyDepartment("DEPART45MENT_NAME"),"Department Not Exist" );
        int index=getIndexOfDepartment("DEPART45MENT_NAME");
        String departmentOptionPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.departmentOptionBtn;
        String deleteBtnPath=DepartmentPageOR.departmentCard+index+DepartmentPageOR.deleteDepartment;
        WebUI.waitForPageLoaded();
        WebUI.clickElement(WebUI.getByObjStringPath(departmentOptionPath));
        WebUI.clickElement(WebUI.getByObjStringPath(deleteBtnPath));
        WebUI.clickElement(DepartmentPageOR.confirmDeleteBtn);
        WebUI.waitForElementVisible(DepartmentPageOR.allDepartments,20);
//        Assert.assertFalse(verifyDepartment("DEPART45MENT_NAME"),"Department Not Deleted" );
    }

    public boolean verifyDepartment(String departmentName) {
        LogUtils.info("verification of Department "+departmentName+ "has been started");
        List<WebElement> elements = DriverManager.getDriver().findElements(DepartmentPageOR.allDepartments);
        for (WebElement ele : elements) {
            LogUtils.info("Department "+ele.getText());

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
