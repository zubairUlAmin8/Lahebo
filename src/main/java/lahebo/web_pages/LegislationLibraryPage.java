package lahebo.web_pages;

import Utils.LogUtils;
import Utils.fakerUtils;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.objectRepo.DepartmentPageOR;
import lahebo.objectRepo.LegislationLibraryOR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import report.ExtentReportManager;

import java.util.List;

import static keywords.WebUI.clickElement;
import static keywords.WebUI.setText;

public class LegislationLibraryPage {
    WebDriver driver;
    String departmentName;
    String departmentNameEdit;

    public LegislationLibraryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void subscribeItems() {
        WebUI.clickElement(LegislationLibraryOR.LL_LegislationList);
        WebUI.selectListOptionRandomly(LegislationLibraryOR.LL_LegislationListElements);
        WebUI.clickElement(LegislationLibraryOR.LL_DivisionList);
        WebUI.selectListOptionRandomly(LegislationLibraryOR.LL_DivisionListElements);
        WebUI.clickElement(LegislationLibraryOR.LL_SearchBtn);
        WebUI.clickElement(LegislationLibraryOR.LL_SubscribeBtn);
        WebUI.clickElement(LegislationLibraryOR.LL_ConfirmSubscriptionBtn);

    }

    public void addLegalRegister() throws InterruptedException {
        //First Tab
        WebUI.clickElement(LegislationLibraryOR.legalRegisterTab);
        WebUI.clickElement(LegislationLibraryOR.addLegalRegisterBtn);
        WebUI.clickElement(LegislationLibraryOR.LR_DivisionList);
        WebUI.selectListOptionRandomly(LegislationLibraryOR.LR_DivisionListElement);
        WebUI.setText(LegislationLibraryOR.LR_ReferenceNumber, fakerUtils.generateRandomPassword());
        WebUI.clickElement(LegislationLibraryOR.LR_NextBtn);

        //Second Tab
        WebUI.clickElement(LegislationLibraryOR.LR_LocationList);
        WebUI.selectCheckBoxOptionRandomly(LegislationLibraryOR.LR_LocationListListElement);
        WebUI.clickElement(LegislationLibraryOR.LR_DepartmentList);
        WebUI.selectCheckBoxOptionRandomly(LegislationLibraryOR.LR_DepartmentListListElement);
        WebUI.clickElement(LegislationLibraryOR.LR_ISOStandardList);
        WebUI.selectListOptionRandomly(LegislationLibraryOR.LR_ISOStandardListListElement);
        WebUI.clickElement(LegislationLibraryOR.LR_NextBtn);

        //Third Tab
        WebUI.clickElement(LegislationLibraryOR.LR_AddControlBtn);
        WebUI.clickElement(LegislationLibraryOR.LR_MarkAsActionCB);
        WebUI.clickElement(LegislationLibraryOR.LR_AssigneeList);
//        Thread.sleep(5000);
        WebUI.waitForElementPresent(LegislationLibraryOR.LR_AssigneeListElement,5);
        WebUI.selectListOptionRandomly(LegislationLibraryOR.LR_AssigneeListElement);
        WebUI.setText(LegislationLibraryOR.LR_ContentForControlTA, fakerUtils.generateDummyData(10));
        WebUI.clickElement(LegislationLibraryOR.LR_AddBtn);
        WebUI.clickElement(LegislationLibraryOR.LR_NextBtn);

        //Forth Tab
        WebUI.setText(LegislationLibraryOR.LR_StateIF, fakerUtils.generateDummyData(5));
        WebUI.setText(LegislationLibraryOR.LR_RemarksIF, fakerUtils.generateDummyData(20));
        WebUI.clickElement(LegislationLibraryOR.LR_AddFinalBtn);





    }

}
