package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import keywords.WebUI;
import lahebo.objectRepo.DepartmentPageOR;
import lahebo.objectRepo.LandingPageOR;
import lahebo.path_repo.LandingPagePR;
import lahebo.web_elements.LandingPageElements;
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
        WebUI.setText(DepartmentPageOR.departmentNameIF, "hod department");
        WebUI.clickElement(DepartmentPageOR.managerList);
        WebUI.waitForElementVisible(DepartmentPageOR.managerListElements,20);
        boolean managerSelection = WebUI.selectOptionDynamic(DepartmentPageOR.managerListElements, "Sarah Johnson");
        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(DepartmentPageOR.addButton);
        Assert.assertTrue(WebUI.verifyPopup(LandingPageOR.popUpAlert,SuccessMessage.successMsgDepartmentAdded,10));

    }
}
