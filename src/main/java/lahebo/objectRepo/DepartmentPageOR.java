package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface DepartmentPageOR {
      By addDepartmentBtn =By.cssSelector("#addDeptBtn");
      By departmentNameIF =By.cssSelector("#deptNameField");
      By managerList =By.cssSelector("#deptManagerField");
      By managerListElements =By.cssSelector("div#deptManagerField>ul");
      By addButton =By.cssSelector("#doAction");

}
