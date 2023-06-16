package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface DepartmentPageOR {
      By addDepartmentBtn =By.cssSelector("#addDeptBtn");
      By departmentNameIF =By.cssSelector("#deptNameField");
      By managerIF =By.cssSelector("#vs1__combobox > div.vs__selected-options > input");
      By managerList =By.cssSelector("#deptManagerField");
      By managerListElements =By.cssSelector("div#deptManagerField>ul");
      By addButton =By.cssSelector("#doAction");
      By confirmDeleteBtn =By.cssSelector("#confirmDelBtn");
      By allDepartments =By.cssSelector("span[id^=\"deptName-\"]");




      String departmentCard = "#deptCard-";             //departmentCard+0
      String departmentName = "#deptName-";
      String departmentManager = "#deptManager-";
      String departmentOptionBtn = ">div>div>button";   //departmentCard+index+departmentOptionBtn
      String editDepartment = ">div>div>div>button:first-of-type";
      String deleteDepartment = ">div>div>div>button:last-of-type";


}
