package lahebo.objectRepo;

import org.openqa.selenium.By;

public interface LocationPageOR {
      By addLocationBtn =By.cssSelector("#addLocBtn");
      By locationNameIF =By.cssSelector("#locNameField");
      By locationAddressIF =By.cssSelector("#locAddressField");
      By locationPhoneNumberIF =By.cssSelector("#locNumberField>input");
      By managerIF =By.cssSelector("#vs1__combobox > div.vs__selected-options > input");
      By keyContactManagerList =By.cssSelector("#locContactField");
      By keyContactManagerListElements =By.cssSelector("div#locContactField>ul");
      By addButton =By.cssSelector("#doAction");
      By confirmDeleteBtn =By.cssSelector("#confirmDelBtn");
      By allLocations =By.cssSelector("span[id^=\"locName-\"]");





      String LocationCard = "#locCard-";             //LocationCard+0
      String LocationName = "#deptName-";
      String LocationManager = "#deptManager-";
      String LocationOptionBtn = ">div>div>button";   //LocationCard+index+LocationOptionBtn
      String editLocation = ">div>div>div>button:nth-of-type(2)";
      String deleteLocation = ">div>div>div>button:last-of-type";


}
