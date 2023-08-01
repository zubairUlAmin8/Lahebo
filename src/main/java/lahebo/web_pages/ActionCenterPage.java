package lahebo.web_pages;

import Utils.LogUtils;
import Utils.fakerUtils;
import Utils.utility;
import constants.FrameworkConstants;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.objectRepo.ActionCenterOR;
import lahebo.objectRepo.LandingPageOR;
import lahebo.objectRepo.ActionCenterOR;
import lahebo.objectRepo.RiskRegisterOR;
import org.openqa.selenium.WebDriver;

public class ActionCenterPage {
    WebDriver driver;

    public ActionCenterPage(WebDriver driver) {
        this.driver = driver;
    }


    public void addNewAction() throws InterruptedException {

        //Navigation
        WebUI.clickElement(ActionCenterOR.addActionBtn);

        //GENERAL INFORMATION
        WebUI.setText(ActionCenterOR.descriptionTextArea, fakerUtils.generateDummyData(20));


        //RELATED INFORMATION
        WebUI.clickElement(ActionCenterOR.locationList);
        WebUI.selectListOptionRandomly(ActionCenterOR.locationListElements);
        WebUI.clickElement(ActionCenterOR.departmentList);
        WebUI.selectListOptionRandomly(ActionCenterOR.departmentListElements);
        WebUI.clickElement(ActionCenterOR.sourceList);
        WebUI.selectListOptionRandomly(ActionCenterOR.sourceListElements);
        WebUI.clickElement(ActionCenterOR.riskList);
        WebUI.selectListOptionRandomly(ActionCenterOR.riskListElements);

        //MITIGATION
        WebUI.clickElement(ActionCenterOR.assigneeList);
        WebUI.selectListOptionRandomly(ActionCenterOR.assigneeListElements);

        WebUI.clickElement(ActionCenterOR.isoStandardList);
        WebUI.selectListOptionByIndex(ActionCenterOR.isoStandardListElements, 2);
        DriverManager.getDriver().findElement(ActionCenterOR.uploadFileIF).sendKeys("D:\\Zapta\\Automation\\Lahebo\\ExtentReports\\ExtentReports.html");
        WebUI.waitForElementToBeGone(ActionCenterOR.fileUploadSpinner, FrameworkConstants.WAIT_EXPLICIT);


//        WebUI.setText(ActionCenterOR.uploadFileIF, "ExtentReports/ExtentReports.html");
        //OTHERS
        WebUI.setText(ActionCenterOR.remarksTextArea, fakerUtils.generateDummyData(20));
        WebUI.clickElement(ActionCenterOR.addBtn);

        //WAIT FOR LOADING
        WebUI.waitForElementToBeGone(LandingPageOR.appModel, 5);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, 5);
    }


    public void deleteRisk() throws InterruptedException {
//        utility.handleZoomInZoomOut(75);
//
//        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, 10);
//        WebUI.scrollToElementToBottom(ActionCenterOR.totalItemsCount);
//        String countText = WebUI.getTextElement(ActionCenterOR.totalItemsCount);
//        LogUtils.info("Total Risk Items: " + countText);
//        LogUtils.info("Total Risk Items: " + utility.extractIntegerFromString(countText) + " length: " + countText.length());
//        int totalItems = utility.extractIntegerFromString(countText);
//        LogUtils.info("Total Risk Items Int: " + totalItems);
//        if (totalItems == 0) {
//            utility.handleZoomInZoomOut(30);
//            countText = WebUI.getTextElement(ActionCenterOR.totalItemsCount);
//            LogUtils.info("Total Risk Items: " + countText);
//            LogUtils.info("Total Risk Items: " + utility.extractIntegerFromString(countText) + " length: " + countText.length());
//            totalItems = utility.extractIntegerFromString(countText);
//            LogUtils.info("Total Risk Items Int: " + totalItems);
//        }
//
//        for (int i = totalItems; i > 0; i--) {
//            WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);
//            WebUI.moveToElement(ActionCenterOR.itemOptionBtn);
//            WebUI.clickElement(ActionCenterOR.itemOptionBtn);
//            WebUI.clickElement(ActionCenterOR.deleteRiskOption);
//            WebUI.clickElement(ActionCenterOR.deleteRiskBtn);
//            WebUI.waitForElementToBeGone(LandingPageOR.confirmModel, FrameworkConstants.WAIT_EXPLICIT);
//        }

    }


    }
