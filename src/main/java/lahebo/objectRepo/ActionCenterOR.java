package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface ActionCenterOR {
      By addActionBtn =By.cssSelector("#addActionBtn");
      By descriptionTextArea =By.cssSelector("#descField");
      By locationList =By.cssSelector("#locField");
      By locationListElements =By.cssSelector("#locField>ul>li");
      By departmentList =By.cssSelector("#deptField");
      By departmentListElements =By.cssSelector("#deptField>ul>li");
      By sourceList =By.cssSelector("#sourceField");
      By sourceListElements =By.cssSelector("#sourceField>ul>li");
      By riskList =By.cssSelector("#riskField");
      By riskListElements =By.cssSelector("#riskField>ul>li");
      By isoStandardList =By.cssSelector("#isoField");
      By isoStandardListElements =By.cssSelector("#isoField>ul>li");
      By remarksTextArea =By.cssSelector("#remarksField");
      By markAsActionCB =By.cssSelector("#action-item");

      By assigneeList =By.cssSelector("#assigneeField");
      By fileUploadSpinner =By.cssSelector("#fileUploadSpinner");
      By assigneeListElements =By.cssSelector("#assigneeField>ul>li");
      By uploadFileIF =By.cssSelector("#browseFiles+input");

      By addBtn =By.cssSelector("#doAction");

      By totalItemsCount =By.cssSelector("div.text-gray-500");
      By itemOptionBtn =By.cssSelector("tr:first-of-type>td:last-of-type>div>button");
      By deleteRiskOption =By.cssSelector("tr:first-of-type>td:last-of-type>div>div>div:last-of-type>button:last-of-type");
      By deleteRiskBtn =By.cssSelector("#confirmDelBtn");


}
