package lahebo.web_pages;

import Utils.LogUtils;
import Utils.fakerUtils;
import constants.TestDataConstants;
import driver.DriverManager;
import helpers.PropertiesHelpers;
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

    public void editProfile(String firstName, String lastName, String phoneNumber) {
        WebUI.clickElement(ProfileDetailsPageOR.editBtn);
        WebUI.waitForElementVisible(ProfileDetailsPageOR.firstNameIF);
        WebUI.clearText(ProfileDetailsPageOR.firstNameIF);
        WebUI.setText(ProfileDetailsPageOR.firstNameIF, firstName);
        WebUI.clearText(ProfileDetailsPageOR.lastNameIF);
        WebUI.setText(ProfileDetailsPageOR.lastNameIF, lastName);
        WebUI.clearText(ProfileDetailsPageOR.phoneNumberIF);
        WebUI.setText(ProfileDetailsPageOR.phoneNumberIF, phoneNumber);
        WebUI.clickElement(ProfileDetailsPageOR.submitBtn);
    }
    public void changePassword(String oldPassword, String newPassword ) {
        WebUI.clickElement(ProfileDetailsPageOR.changePasswordBtn);
        WebUI.setText(ProfileDetailsPageOR.currentPasswordIF, oldPassword);
        WebUI.setText(ProfileDetailsPageOR.newPasswordIF, newPassword);
        WebUI.setText(ProfileDetailsPageOR.confirmNewPasswordIF, newPassword);
        WebUI.clickElement(ProfileDetailsPageOR.ConfirmChangePasswordBtn);
        WebUI.waitForPageLoaded();

    }

}
