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
        WebUI.clickElement(JobFunctionPageOR.jobFunctionList);
        WebUI.waitForElementVisible(JobFunctionPageOR.jobFunctionListElements,20);
        boolean managerSelection = WebUI.selectMultpleCheckBoxDynamic(JobFunctionPageOR.jobFunctionListElements,JobFunctionPageOR.jobFunctionListElementsCheckBox, departmentName);
        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(JobFunctionPageOR.jobFunctionList);
        WebUI.clickElement(JobFunctionPageOR.addButton);
        WebUI.waitForElementVisible(JobFunctionPageOR.allJobFunctions,20);
    }

    public void deleteJobFunction(String jobFunctionName) throws InterruptedException {
        Assert.assertTrue(verifyJobFunction(jobFunctionName),"Department Not Exist" );
        int index=getIndexOfJobFunction(jobFunctionName);
        String jobFunctionOptionPath=JobFunctionPageOR.jobFunctionCard+index+JobFunctionPageOR.jobFunctionOptionBtn;
        String deleteBtnPath=JobFunctionPageOR.jobFunctionCard+index+JobFunctionPageOR.deletejobFunction;
        WebUI.waitForPageLoaded();
        WebUI.clickElement(WebUI.getByObjStringPath(jobFunctionOptionPath));
        WebUI.clickElement(WebUI.getByObjStringPath(deleteBtnPath));
        WebUI.clickElement(JobFunctionPageOR.confirmDeleteBtn);
        WebUI.waitForElementVisible(JobFunctionPageOR.allJobFunctions,20);
    }

    public void editJobFunction(String EditJobFunctionName, String department, String existingJobFunction) {
        Assert.assertTrue(verifyJobFunction(existingJobFunction),existingJobFunction+": Job Function Not Exist" );
        int index=getIndexOfJobFunction(existingJobFunction);
        String jobFunctionOptionPath=JobFunctionPageOR.jobFunctionCard+index+JobFunctionPageOR.jobFunctionOptionBtn;
        String editBtnPath=JobFunctionPageOR.jobFunctionCard+index+JobFunctionPageOR.editjobFunction;
        WebUI.clickElement(WebUI.getByObjStringPath(jobFunctionOptionPath));
        WebUI.clickElement(WebUI.getByObjStringPath(editBtnPath));
        WebUI.clearText(JobFunctionPageOR.jobFunctionNameIF);
        WebUI.setText(JobFunctionPageOR.jobFunctionNameIF, EditJobFunctionName);
//        WebUI.clickElement(JobFunctionPageOR.managerList);
//        WebUI.waitForElementVisible(JobFunctionPageOR.managerListElements,20);
//        boolean managerSelection = WebUI.selectOptionDynamic(JobFunctionPageOR.managerListElements, mngrName);
//        Assert.assertTrue(managerSelection, "Manager is not selected");
        WebUI.clickElement(JobFunctionPageOR.addButton);

    }

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
    public int getIndexOfJobFunction(String jobFunctionName) {
        LogUtils.info("Department Index is being searched");
        int index = 0;
        List<WebElement> elements = DriverManager.getDriver().findElements(JobFunctionPageOR.allJobFunctions);
        for (WebElement ele : elements) {
            if (ele.getText().equals(jobFunctionName)) {
                break;
            }
            ++index;
        }

        LogUtils.info("Department Index is: "+index);
        return index;
    }

}
