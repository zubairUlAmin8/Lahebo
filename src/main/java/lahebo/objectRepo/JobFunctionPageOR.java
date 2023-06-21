package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface JobFunctionPageOR {
      By addJobFunctionBtn =By.cssSelector("#addJobFuncBtn");
      By jobFunctionNameIF =By.cssSelector("#jobFuncNameField");
      By managerIF =By.cssSelector("#vs1__combobox > div.vs__selected-options > input");
      By jobFunctionList =By.cssSelector("#jobFuncDeptField");
      By jobFunctionListElements =By.cssSelector("#jobFuncDeptField>div>div>div");
      By jobFunctionListElementsCheckBox =By.cssSelector("#jobFuncDeptField>div>div:nth-of-type(2)>input");
      By addButton =By.cssSelector("#doAction");
      By confirmDeleteBtn =By.cssSelector("#confirmDelBtn");
      By allJobFunctions =By.cssSelector("span[id^=\"jobFuncName-\"]");




      String jobFunctionCard = "#jobFunc-";             //departmentCard+0
      String jobFunctionName = "#deptName-";
      String jobFunctionManager = "#deptManager-";
      String jobFunctionOptionBtn = ">div>div>button";   //departmentCard+index+departmentOptionBtn
      String editjobFunction = ">div>div>div>button:first-of-type";
      String deletejobFunction = ">div>div>div>button:last-of-type";


}
