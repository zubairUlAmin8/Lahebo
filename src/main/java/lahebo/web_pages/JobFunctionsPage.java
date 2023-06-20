package lahebo.web_pages;

import Utils.LogUtils;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.objectRepo.JobFunctionPageOR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class JobFunctionsPage {
    WebDriver driver;
    String departmentNameEdit;
    public JobFunctionsPage(WebDriver driver) {
        this.driver=driver;
    }
    public void addNewJobFunction(String jobFunctionName, String departmentName) throws InterruptedException {
        
        WebUI.clickElement(JobFunctionPageOR.addJobFunctionBtn);
        WebUI.setText(JobFunctionPageOR.jobFunctionNameIF, jobFunctionName);
        WebUI.clickElement(JobFunctionPageOR.departmentList);
        WebUI.waitForElementVisible(JobFunctionPageOR.departmentListElements,20);
        boolean managerSelection = WebUI.selectMultpleCheckBoxDynamic(JobFunctionPageOR.departmentListElements,JobFunctionPageOR.departmentListElementsCheckBox, departmentName);
        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(JobFunctionPageOR.departmentList);
        WebUI.clickElement(JobFunctionPageOR.addButton);
        WebUI.waitForElementVisible(JobFunctionPageOR.allJobFunctions,20);
    }

//    public void deleteDepartment(String dptName) throws InterruptedException {
//        Assert.assertTrue(verifyDepartment(dptName),"Department Not Exist" );
//        int index=getIndexOfDepartment(dptName);
//        String departmentOptionPath=JobFunctionPageOR.departmentCard+index+JobFunctionPageOR.departmentOptionBtn;
//        String deleteBtnPath=JobFunctionPageOR.departmentCard+index+JobFunctionPageOR.deleteDepartment;
//        WebUI.waitForPageLoaded();
//        WebUI.clickElement(WebUI.getByObjStringPath(departmentOptionPath));
//        WebUI.clickElement(WebUI.getByObjStringPath(deleteBtnPath));
//        WebUI.clickElement(JobFunctionPageOR.confirmDeleteBtn);
//        WebUI.waitForElementVisible(JobFunctionPageOR.allDepartments,20);
//    }
//
//    public void editDepartment(String dptEditName, String mngrName, String existingDptName) {
//        departmentNameEdit = dptEditName;
//        Assert.assertTrue(verifyDepartment(existingDptName),existingDptName+": Department Not Exist" );
//        int index=getIndexOfDepartment(existingDptName);
//        String departmentOptionPath=JobFunctionPageOR.departmentCard+index+JobFunctionPageOR.departmentOptionBtn;
//        String editBtnPath=JobFunctionPageOR.departmentCard+index+JobFunctionPageOR.editDepartment;
//        WebUI.clickElement(WebUI.getByObjStringPath(departmentOptionPath));
//        WebUI.clickElement(WebUI.getByObjStringPath(editBtnPath));
//        WebUI.clearText(JobFunctionPageOR.departmentNameIF);
//        WebUI.setText(JobFunctionPageOR.departmentNameIF, departmentNameEdit);
//        WebUI.clickElement(JobFunctionPageOR.managerList);
//        WebUI.waitForElementVisible(JobFunctionPageOR.managerListElements,20);
//        boolean managerSelection = WebUI.selectOptionDynamic(JobFunctionPageOR.managerListElements, mngrName);
//        Assert.assertTrue(managerSelection, "Manager is not selected");
//        WebUI.clickElement(JobFunctionPageOR.addButton);
//
//    }
//
    public boolean verifyJobFunction(String jobFunctionName) {
        LogUtils.info("verification of Job Function "+jobFunctionName+ "has been started");
        List<WebElement> elements = DriverManager.getDriver().findElements(JobFunctionPageOR.allJobFunctions);
        for (WebElement ele : elements) {
            LogUtils.info("Job Function Found:  "+ele.getText());

            if (ele.getText().equals(jobFunctionName)) {
                LogUtils.info("Job Function "+jobFunctionName+ "has been verified");
                return true;
            }

        }
        LogUtils.info("Job Functions "+jobFunctionName+ " Not exist");
        return false;
    }
//    public int getIndexOfDepartment(String departmentName) {
//        LogUtils.info("Department Index is being searched");
//        int index = 0;
//        List<WebElement> elements = DriverManager.getDriver().findElements(JobFunctionPageOR.allDepartments);
//        for (WebElement ele : elements) {
//            if (ele.getText().equals(departmentName)) {
//                break;
//            }
//            ++index;
//        }
//
//        LogUtils.info("Department Index is: "+index);
//        return index;
//    }

}
