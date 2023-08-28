package lahebo.web_pages;

import Utils.LogUtils;
import Utils.fakerUtils;
import Utils.utility;
import constants.FrameworkConstants;
import keywords.WebUI;
import lahebo.objectRepo.LandingPageOR;
import lahebo.objectRepo.LegislationLibraryOR;
import lahebo.objectRepo.RiskRegisterOR;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RiskRegisterPage {
    WebDriver driver;

    public RiskRegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    public void addNewRisk() throws InterruptedException {
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);

        //Navigation
        WebUI.clickElement(RiskRegisterOR.addRiskBtn);
        WebUI.clickElement(RiskRegisterOR.addRiskChildBtn);

        //GENERAL INFORMATION
        WebUI.setText(RiskRegisterOR.descriptionTextArea, fakerUtils.generateDummyData(20));
        WebUI.clickElement(RiskRegisterOR.isoStandardList);

        WebUI.selectListOptionByIndex(RiskRegisterOR.isoStandardListElements, 2);
        int hazardCheck = 2;

        //RELATED INFORMATION
        WebUI.clickElement(RiskRegisterOR.locationList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.locationListElements);
        WebUI.clickElement(RiskRegisterOR.departmentList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.departmentListElements);
        WebUI.clickElement(RiskRegisterOR.sourceList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.sourceListElements);

        //HAZARD INFORMATION
        if (hazardCheck == 2) {
            WebUI.clickElement(RiskRegisterOR.typeOfHazardList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.typeOfHazardListElements);
            WebUI.clickElement(RiskRegisterOR.hazardList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.hazardListElements);
            WebUI.setText(RiskRegisterOR.hazardDescriptionIF, fakerUtils.generateDummyData(10));
            WebUI.setText(RiskRegisterOR.potentialHazardIF, fakerUtils.generateDummyData(10));
            WebUI.clickElement(RiskRegisterOR.JobFunctionList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.JobFunctionListElements);
            WebUI.setText(RiskRegisterOR.peopleAtRiskIF, Integer.toString(fakerUtils.generateRandomInt(100)));
        }

        //RISK RATING
        WebUI.clickElement(RiskRegisterOR.likelihoodList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.likelihoodListElements);
        WebUI.clickElement(RiskRegisterOR.consequenceList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.consequenceListElements);

        //MITIGATION
        WebUI.clickElement(RiskRegisterOR.addMitigationBtn);
        WebUI.clickElement(RiskRegisterOR.markAsActionCB);
        WebUI.clickElement(RiskRegisterOR.assigneeList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.assigneeListElements);
        WebUI.setText(RiskRegisterOR.mitigationContentTextArea, fakerUtils.generateDummyData(30));
        WebUI.clickElement(RiskRegisterOR.addBtn);


        //RISK RATING AFTER MITIGATION
        WebUI.clickElement(RiskRegisterOR.afterLikelihoodList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.afterLikelihoodListElements);

        WebUI.clickElement(RiskRegisterOR.afterConsequenceList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.afterConsequenceListElements);

        //OTHERS
        WebUI.clickElement(RiskRegisterOR.ownerList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.ownerListElements);
        WebUI.setText(RiskRegisterOR.remarksTextArea, fakerUtils.generateDummyData(20));
        WebUI.clickElement(RiskRegisterOR.submitBtn);

        //WAIT FOR LOADING
        WebUI.waitForElementToBeGone(LandingPageOR.appModel, 5);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, 5);
    }

    public void raiseNewRisk() throws InterruptedException {
        //Navigation
        WebUI.clickElement(RiskRegisterOR.addRiskBtn);
        WebUI.clickElement(RiskRegisterOR.raiseRiskChildBtn);

        //GENERAL INFORMATION
        WebUI.setText(RiskRegisterOR.descriptionTextArea, fakerUtils.generateDummyData(20));

        //RELATED INFORMATION
        WebUI.clickElement(RiskRegisterOR.locationList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.locationListElements);
        WebUI.clickElement(RiskRegisterOR.departmentList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.departmentListElements);
        WebUI.clickElement(RiskRegisterOR.sourceList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.sourceListElements);


        //OTHERS
        WebUI.clickElement(RiskRegisterOR.ownerList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.ownerListElements);
        WebUI.setText(RiskRegisterOR.remarksTextArea, fakerUtils.generateDummyData(20));
        WebUI.clickElement(RiskRegisterOR.submitBtn);

        //WAIT FOR LOADING
        WebUI.waitForElementToBeGone(LandingPageOR.appModel, 5);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, 5);

    }
    public void deleteRisk() throws InterruptedException {

        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, 10);
        WebUI.scrollToElementToBottom(RiskRegisterOR.totalItemsCount);
        String countText = WebUI.getTextElement(RiskRegisterOR.totalItemsCount);
        LogUtils.info("Total Risk Items: " + countText);
        LogUtils.info("Total Risk Items: " + utility.extractIntegerFromString(countText) + " length: " + countText.length());
        int totalItems = utility.extractIntegerFromString(countText);
        LogUtils.info("Total Risk Items Int: " + totalItems);
        if (totalItems == 0) {
            utility.handleZoomInZoomOut(30);
            countText = WebUI.getTextElement(RiskRegisterOR.totalItemsCount);
            LogUtils.info("Total Risk Items: " + countText);
            LogUtils.info("Total Risk Items: " + utility.extractIntegerFromString(countText) + " length: " + countText.length());
            totalItems = utility.extractIntegerFromString(countText);
            LogUtils.info("Total Risk Items Int: " + totalItems);
        }

        for (int i = totalItems; i > 0; i--) {
            WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);
            WebUI.moveToElement(RiskRegisterOR.itemOptionBtn);
            WebUI.clickElement(RiskRegisterOR.itemOptionBtn);
            WebUI.clickElement(RiskRegisterOR.deleteRiskOption);
            WebUI.clickElement(RiskRegisterOR.deleteRiskBtn);
            WebUI.waitForElementToBeGone(LandingPageOR.confirmModel, FrameworkConstants.WAIT_EXPLICIT);
        }

    }
    public void acceptRisk() throws InterruptedException {
        WebUI.moveToElement(RiskRegisterOR.itemOptionBtn);
        WebUI.clickElement(RiskRegisterOR.itemOptionBtn);
        WebUI.clickElement(RiskRegisterOR.acceptRiskOption);
        WebUI.clickElement(RiskRegisterOR.confirmAcceptRiskBtn);
        WebUI.waitForElementToBeGone(LandingPageOR.confirmModel, FrameworkConstants.WAIT_EXPLICIT);
    }
    public void rejectRisk() throws InterruptedException {
        WebUI.moveToElement(RiskRegisterOR.itemOptionBtn);
        WebUI.clickElement(RiskRegisterOR.itemOptionBtn);
        WebUI.clickElement(RiskRegisterOR.rejectRiskOption);
        WebUI.setText(RiskRegisterOR.rejectRiskReasonTextArea, fakerUtils.generateDummyData(10));
        WebUI.clickElement(RiskRegisterOR.deleteRiskBtn);
        WebUI.waitForElementToBeGone(LandingPageOR.confirmModel, FrameworkConstants.WAIT_EXPLICIT);

    }
    public void editRisk(boolean isItSWOTRisk) throws InterruptedException {

        if (isItSWOTRisk) {
            //RELATED INFORMATION
            WebUI.clickElement(RiskRegisterOR.locationList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.locationListElements);
            WebUI.clickElement(RiskRegisterOR.departmentList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.departmentListElements);
            //OTHERS
            WebUI.clickElement(RiskRegisterOR.ownerList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.ownerListElements);
            WebUI.setText(RiskRegisterOR.remarksTextArea, fakerUtils.generateDummyData(20));
            WebUI.clickElement(RiskRegisterOR.submitBtn);
        }

        WebUI.clickElement(RiskRegisterOR.isoStandardList);

        WebUI.selectListOptionByIndex(RiskRegisterOR.isoStandardListElements, 2);
        int hazardCheck = 2;

        //HAZARD INFORMATION
        if (hazardCheck == 2) {
            WebUI.clickElement(RiskRegisterOR.typeOfHazardList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.typeOfHazardListElements);
            WebUI.clickElement(RiskRegisterOR.hazardList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.hazardListElements);
            WebUI.setText(RiskRegisterOR.hazardDescriptionIF, fakerUtils.generateDummyData(10));
            WebUI.setText(RiskRegisterOR.potentialHazardIF, fakerUtils.generateDummyData(10));
            WebUI.clickElement(RiskRegisterOR.JobFunctionList);
            WebUI.selectListOptionRandomly(RiskRegisterOR.JobFunctionListElements);
            WebUI.setText(RiskRegisterOR.peopleAtRiskIF, Integer.toString(fakerUtils.generateRandomInt(100)));
        }

        //RISK RATING
        WebUI.clickElement(RiskRegisterOR.likelihoodList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.likelihoodListElements);
        WebUI.clickElement(RiskRegisterOR.consequenceList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.consequenceListElements);

        //MITIGATION
        WebUI.clickElement(RiskRegisterOR.addMitigationBtn);
        WebUI.clickElement(RiskRegisterOR.markAsActionCB);
        WebUI.clickElement(RiskRegisterOR.assigneeList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.assigneeListElements);
        WebUI.setText(RiskRegisterOR.mitigationContentTextArea, fakerUtils.generateDummyData(30));
        WebUI.clickElement(RiskRegisterOR.addBtn);


        //RISK RATING AFTER MITIGATION
        WebUI.clickElement(RiskRegisterOR.afterLikelihoodList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.afterLikelihoodListElements);

        WebUI.clickElement(RiskRegisterOR.afterConsequenceList);
        WebUI.selectListOptionRandomly(RiskRegisterOR.afterConsequenceListElements);

        //OTHERS
        WebUI.clickElement(RiskRegisterOR.submitBtn);

        //WAIT FOR LOADING
        WebUI.waitForElementToBeGone(LandingPageOR.appModel, FrameworkConstants.WAIT_EXPLICIT);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);
    }
    public void addRiskBySWOT() throws InterruptedException {

        for (int boardNumber = 1; boardNumber < 2; boardNumber++) {
            addStrength(boardNumber);
            createRiskForItem(boardNumber);

        }

    }


    public void backToRiskRegisterFromSWOT() throws InterruptedException {
        WebUI.clickElement(RiskRegisterOR.backToRiskBtn);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);
    }
    public void viewSWOT() throws InterruptedException {
        WebUI.clickElement(RiskRegisterOR.viewSWOTBtn);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);
    }
    public void deleteAllStrength() throws InterruptedException  {
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);
        int count=0;
        List<WebElement> allStrength = WebUI.getWebElements(RiskRegisterOR.allStrength);
        LogUtils.info("Strength Total Count: "+allStrength.size());
        for(WebElement element:allStrength){
            count++;
            try {
                LogUtils.info("Strength Name: " + element.getText());
                WebUI.clickElement(By.cssSelector("#StrengthsCard>ul>li:nth-of-type(" + count + ")>div:last-of-type"));
                WebUI.clickElement(By.cssSelector("#StrengthsCard>ul>li:nth-of-type(" + count + ")>div:last-of-type>div>div:last-of-type"));
                WebUI.clickElement(RiskRegisterOR.deleteRiskBtn);
                WebUI.waitForElementToBeGone(LandingPageOR.confirmModel, FrameworkConstants.WAIT_EXPLICIT);
            } catch (Exception StaleElementReferenceException) {

            }


        }
    }


    public void createRiskForItem(int boardNumber) throws InterruptedException {
        switch(boardNumber) {
            case 2:
                WebUI.clickElement(RiskRegisterOR.weaknessesOptions);
                WebUI.clickElement(RiskRegisterOR.createRiskForWeaknesses);


                break;
            case 3:
                WebUI.clickElement(RiskRegisterOR.opportunitiesOptions);
                WebUI.clickElement(RiskRegisterOR.createRiskForOpportunities);

                break;
            case 4:
                WebUI.clickElement(RiskRegisterOR.threatsOptions);
                WebUI.clickElement(RiskRegisterOR.createRiskForThreats);
                break;
            default:
                WebUI.clickElement(RiskRegisterOR.strengthOptions);
                WebUI.clickElement(RiskRegisterOR.createRiskForStrengths);

        }
        WebUI.clickElement(RiskRegisterOR.confirmAcceptRiskBtn);
        WebUI.waitForElementToBeGone(LandingPageOR.appModel, FrameworkConstants.WAIT_EXPLICIT);


    }
    public void addStrength(int boardNumber) throws InterruptedException {
        switch(boardNumber) {
            case 2:
                WebUI.clickElement(RiskRegisterOR.addWeaknessesBtn);

                break;
            case 3:
                WebUI.clickElement(RiskRegisterOR.addOpportunitiesBtn);

                break;
            case 4:
                WebUI.clickElement(RiskRegisterOR.addThreatsBtn);
                break;
            default:
                WebUI.clickElement(RiskRegisterOR.addStrengthsBtn);

        }
        WebUI.setText(RiskRegisterOR.swotDescriptionTextArea, fakerUtils.generateDummyData(10));
        WebUI.clickElement(RiskRegisterOR.submitDoActionBtn);
        //WAIT FOR LOADING
        WebUI.waitForElementToBeGone(LandingPageOR.appModel, FrameworkConstants.WAIT_EXPLICIT);
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, FrameworkConstants.WAIT_EXPLICIT);

    }
    public void verifyDepartmentFilter() throws InterruptedException {
        WebUI.clickElement(RiskRegisterOR.departmentFilterList);
        WebUI.selectCheckBoxOptionByIndex(RiskRegisterOR.departmentFilterListElements, 3);
        Thread.sleep(5000);
        String departmentNameSelected = WebUI.getTextElement(RiskRegisterOR.departmentFilterListElementsText);
        String departmentNameResults = WebUI.getTextElement(RiskRegisterOR.verifyDepartmentName);
        System.out.println(departmentNameSelected+departmentNameResults);
    }


    }
