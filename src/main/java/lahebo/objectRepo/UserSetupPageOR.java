package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface UserSetupPageOR {
      By addUserBtn =By.cssSelector("#addUserBtn");
      By userFirstNameIF =By.cssSelector("#userFirstName");
      By userLastNameIF =By.cssSelector("#userLastName");
      By userEmailIF =By.cssSelector("#userEmail");
      By userUsernameIF =By.cssSelector("#userUsername");
      By userRoleList =By.cssSelector("#userRole");
      By userRoleListElements =By.cssSelector("#userRole>ul>li");
      By userPhoneNumberIF =By.cssSelector("#userPhoneNumber>input");

      By locationList =By.cssSelector("#userLocs");
      By locationListElements =By.cssSelector("#userLocs>ul>li");
      By departmentList =By.cssSelector("#userDepts");
      By departmentListElements =By.cssSelector("#userDepts>ul>li");
      By addBtn =By.cssSelector("#doAction");
      By userNameFromTable =By.cssSelector("tr[id*=\"userRow\"]>td:nth-of-type(4)");
      By deleteUserBtn =By.cssSelector("div[id*=\"headlessui-menu-items\"]>button:last-of-type");
      By confirmDelBtn =By.cssSelector("#confirmDelBtn");



}
