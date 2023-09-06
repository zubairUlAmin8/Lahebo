package lahebo.web_pages;

import Utils.LogUtils;
import Utils.fakerUtils;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.objectRepo.DepartmentPageOR;
import lahebo.objectRepo.LandingPageOR;
import lahebo.objectRepo.UserSetupPageOR;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class UserSetupPage {
    WebDriver driver;

    public UserSetupPage(WebDriver driver) {
        this.driver=driver;
    }
    public String addNewManagerUser() throws InterruptedException {
        WebUI.clickElement(UserSetupPageOR.addUserBtn);
        String firstName = fakerUtils.generateFirstName();
        String lastName = fakerUtils.generateLastName();
        int randomNumber = fakerUtils.generateRandomInt(10000,50000);
        String userNameForManager = firstName + randomNumber;

        String phoneNumber = fakerUtils.generateRandomAustralianPhoneNumber();

        WebUI.setText(UserSetupPageOR.userFirstNameIF, firstName);
        WebUI.setText(UserSetupPageOR.userLastNameIF, lastName);
        WebUI.setText(UserSetupPageOR.userUsernameIF, userNameForManager);
        WebUI.setText(UserSetupPageOR.userEmailIF, "zubairulamin8+"+ randomNumber+ "@gmail.com");

        WebUI.setText(UserSetupPageOR.userPhoneNumberIF, phoneNumber);
        WebUI.clickElement(UserSetupPageOR.userRoleList);
        WebUI.selectListOptionByIndex(UserSetupPageOR.userRoleListElements, 2);

        WebUI.clickElement(UserSetupPageOR.locationList);
        WebUI.selectListOptionRandomly(UserSetupPageOR.locationListElements);

        WebUI.clickElement(UserSetupPageOR.departmentList);
        WebUI.selectListOptionRandomly(UserSetupPageOR.departmentListElements);
        WebUI.clickElement(UserSetupPageOR.addBtn);
        return userNameForManager;
    }
    public String addNewNormalUser() throws InterruptedException {
        WebUI.clickElement(UserSetupPageOR.addUserBtn);
        String firstName = fakerUtils.generateFirstName();
        String lastName = fakerUtils.generateLastName();
        int randomNumber = fakerUtils.generateRandomInt(10000,50000);
        String userNameForManager = firstName + randomNumber;

        String phoneNumber = fakerUtils.generateRandomAustralianPhoneNumber();

        WebUI.setText(UserSetupPageOR.userFirstNameIF, firstName);
        WebUI.setText(UserSetupPageOR.userLastNameIF, lastName);
        WebUI.setText(UserSetupPageOR.userUsernameIF, userNameForManager);
        WebUI.setText(UserSetupPageOR.userEmailIF, "zubairulamin8+"+ randomNumber+ "@gmail.com");

        WebUI.setText(UserSetupPageOR.userPhoneNumberIF, phoneNumber);
        WebUI.clickElement(UserSetupPageOR.userRoleList);
        WebUI.selectListOptionByIndex(UserSetupPageOR.userRoleListElements, 3);

        WebUI.clickElement(UserSetupPageOR.locationList);
        WebUI.selectListOptionRandomly(UserSetupPageOR.locationListElements);

        WebUI.clickElement(UserSetupPageOR.departmentList);
        WebUI.selectListOptionRandomly(UserSetupPageOR.departmentListElements);
        WebUI.clickElement(UserSetupPageOR.addBtn);
        return userNameForManager;
    }


    public boolean deleteManager(String userName) throws InterruptedException {
        List<WebElement> allUserName = WebUI.getWebElements(UserSetupPageOR.userNameFromTable);
        int count=0;
        LogUtils.info(allUserName.size());
        for (WebElement element : allUserName) {
            count++;
            if (element.getText().equalsIgnoreCase(userName)) {
                LogUtils.info(element.getText()+ count);
                WebUI.clickElement(By.cssSelector("tr[id*=\"userRow\"]:nth-of-type("+count+")>td:nth-of-type(8)"));
                WebUI.clickElement(UserSetupPageOR.deleteUserBtn);
                WebUI.clickElement(UserSetupPageOR.confirmDelBtn);
                return true;
            }
        }
        return false;
    }



}
