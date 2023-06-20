package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface JobFunctionPageOR {
      By addJobFunctionBtn =By.cssSelector("#addJobFuncBtn");
      By jobFunctionNameIF =By.cssSelector("#jobFuncNameField");
      By managerIF =By.cssSelector("#vs1__combobox > div.vs__selected-options > input");
      By departmentList =By.cssSelector("#jobFuncDeptField");
      By departmentListElements =By.cssSelector("#jobFuncDeptField>div>div>div");
      By departmentListElementsCheckBox =By.cssSelector("#jobFuncDeptField>div>div:nth-of-type(2)>input");
      By addButton =By.cssSelector("#doAction");
      By confirmDeleteBtn =By.cssSelector("#confirmDelBtn");
      By allJobFunctions =By.cssSelector("span[id^=\"jobFuncName-\"]");




      String departmentCard = "#deptCard-";             //departmentCard+0
      String departmentName = "#deptName-";
      String departmentManager = "#deptManager-";
      String departmentOptionBtn = ">div>div>button";   //departmentCard+index+departmentOptionBtn
      String editDepartment = ">div>div>div>button:first-of-type";
      String deleteDepartment = ">div>div>div>button:last-of-type";


}
