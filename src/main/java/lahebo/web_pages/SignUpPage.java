package lahebo.web_pages;

import Utils.fakerUtils;
import Utils.waits;
import constants.FrameworkConstants;
import driver.DriverManager;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import models.SignUpModel;
import org.openqa.selenium.WebDriver;

import lahebo.web_elements.SignUpPageElements;

import java.util.Hashtable;

public class SignUpPage extends BasePage{
    SignUpPageElements signUpPageElements;
    ExcelHelpers excelHelpers = new ExcelHelpers();
    WebDriver driver;
    public SignUpPage(WebDriver driver) {

        this.driver = driver;
        signUpPageElements = new SignUpPageElements(DriverManager.getDriver());

    }

    public void goToSignUpPage() {
        if (DriverManager.getDriver() == null) {
            System.out.println("Driver is null");
        }
        DriverManager.getDriver().get(PropertiesHelpers.getValue("URL_RAHEBO_SIGNUP"));
    }

    public boolean fillSignUpForm(Hashtable<String, String> data) {
        excelHelpers.setExcelFile(FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignUp");
        String firstName=data.get(SignUpModel.getFirstName());
        String LastName=data.get(SignUpModel.getLastName());
        String orgName=data.get(SignUpModel.getOrganizationName());
        String email=data.get(SignUpModel.getEmail());
        String userName=data.get(SignUpModel.getUsername());
        String phoneNumber = fakerUtils.generateRandomAustralianPhoneNumber();
        String password=data.get(SignUpModel.getPassword());
        String confirmPassword=data.get(SignUpModel.getConfirmPassword());
        waits.waitForElements(DriverManager.getDriver(),signUpPageElements.firstName,5000);
        signUpPageElements.firstName.sendKeys(firstName);
        signUpPageElements.LastName.sendKeys(LastName);
        signUpPageElements.orgName.sendKeys(orgName);
        signUpPageElements.email.sendKeys(email);
        signUpPageElements.userName.sendKeys(userName);
        signUpPageElements.phoneNumber.sendKeys(phoneNumber);
        signUpPageElements.password.sendKeys(password);
        signUpPageElements.confirmPassword.sendKeys(confirmPassword);
        signUpPageElements.singUpBtn.click();
        return true;
    }
}
