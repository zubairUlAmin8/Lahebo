package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface RiskRegisterOR {
      //Legal List
      By addRiskBtn =By.cssSelector("#addRaiseRiskBtn");
      By addRiskChildBtn =By.cssSelector("#addRiskBtn");
      By descriptionTextArea =By.cssSelector("#descField");
      By isoStandardList =By.cssSelector("#isoField");
      By isoStandardListElements =By.cssSelector("#isoField>ul>li");
      By locationList =By.cssSelector("#locField");
      By locationListElements =By.cssSelector("#locField>ul>li");
      By departmentList =By.cssSelector("#deptField");
      By departmentListElements =By.cssSelector("#deptField>ul>li");
      By sourceList =By.cssSelector("#sourceField");
      By sourceListElements =By.cssSelector("#sourceField>ul>li");
      By likelihoodList =By.cssSelector("#likelihoodField");
      By likelihoodListElements =By.cssSelector("#likelihoodField>ul>li");
      By addMitigationBtn =By.cssSelector("#addMitigationBtn");
      By afterLikelihoodList =By.cssSelector("#afterLikelihoodField");
      By afterLikelihoodListElements =By.cssSelector("#afterLikelihoodField>ul>li");
      By consequenceList =By.cssSelector("#consequenceField");
      By consequenceListElements =By.cssSelector("#consequenceField>ul>li");
      By afterConsequenceList =By.cssSelector("#afterConsequenceField");
      By afterConsequenceListElements =By.cssSelector("#afterConsequenceField>ul>li");
      By ownerList =By.cssSelector("#ownerField");
      By ownerListElements =By.cssSelector("#ownerField>ul>li");
      By remarksTextArea =By.cssSelector("#remarksField");
      By markAsActionCB =By.cssSelector("#action-item");

      By assigneeList =By.cssSelector("#assigneeField");
      By assigneeListElements =By.cssSelector("#assigneeField>ul>li");
      By mitigationContentTextArea =By.cssSelector("#contentField");
      By addBtn =By.xpath("//button[text()='Add']");
      By submitBtn =By.xpath("//button[text()='Submit']");


      By typeOfHazardList =By.cssSelector("#hazardTypeField");
      By typeOfHazardListElements =By.cssSelector("#hazardTypeField>ul>li");
      By JobFunctionList =By.cssSelector("#jobFunctionField");
      By JobFunctionListElements =By.cssSelector("#jobFunctionField>ul>li");
      By hazardList =By.cssSelector("#hazardField");
      By hazardListElements =By.cssSelector("#hazardField>ul>li");
      By hazardDescriptionIF =By.cssSelector("#potentialHazardField");
      By potentialHazardIF =By.cssSelector("#potentialHazardField");
      By peopleAtRiskIF =By.cssSelector("#peopleAtRiskField");
      By totalItemsCount =By.cssSelector("div.text-gray-500");
      By itemOptionBtn =By.cssSelector("tr:first-of-type>td:last-of-type>div>button");
      By deleteRiskOption =By.cssSelector("tr:first-of-type>td:last-of-type>div>div>div:last-of-type>button:last-of-type");
      By deleteRiskBtn =By.cssSelector("#confirmDelBtn");






}
