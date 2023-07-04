package lahebo.web_pages;

import Utils.LogUtils;
import driver.DriverManager;
import keywords.WebUI;
import lahebo.objectRepo.DepartmentPageOR;
import lahebo.objectRepo.ProfileDetailsPageOR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProfileDetailsPage {
    WebDriver driver;
    public ProfileDetailsPage(WebDriver driver) {
        this.driver=driver;
    }

    public void editProfile() {
        WebUI.clickElement(ProfileDetailsPageOR.editBtn);
        WebUI.waitForElementVisible(ProfileDetailsPageOR.firstNameIF);
        WebUI.clearText(ProfileDetailsPageOR.firstNameIF);
        WebUI.clearText(ProfileDetailsPageOR.lastNameIF);
        WebUI.clearText(ProfileDetailsPageOR.phoneNumberIF);
        WebUI.clickElement(ProfileDetailsPageOR.submitBtn);
    }
    public void changePassword() {
        WebUI.clickElement(ProfileDetailsPageOR.changePasswordBtn);
        WebUI.setText(ProfileDetailsPageOR.currentPasswordIF, "old pasword");
        WebUI.setText(ProfileDetailsPageOR.newPasswordIF, "old pasword");
        WebUI.setText(ProfileDetailsPageOR.confirmNewPasswordIF, "old pasword");
        WebUI.clickElement(ProfileDetailsPageOR.ConfirmChangePasswordBtn);



    }

}
