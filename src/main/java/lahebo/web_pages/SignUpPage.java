package lahebo.web_pages;

import Utils.LogUtils;
import Utils.WebUI;
import Utils.fakerUtils;
import Utils.waits;
import constants.FrameworkConstants;
import driver.DriverManager;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import models.SignInModel;
import models.SignUpModel;
import org.openqa.selenium.WebDriver;

import lahebo.web_elements.SignUpPageElements;

import java.util.Hashtable;

public class SignUpPage extends BasePage{
    SignUpPageElements signUpPageElements;
    ExcelHelpers excelHelpers = new ExcelHelpers();
    WebDriver driver;
    String expectedTitle;

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

    public boolean verifyExpectedResult() {
        if (expectedTitle.equals("Pass")) {
            return true;
        } else {
            return false;

        }
    }
    public boolean fillSignUpForm(Hashtable<String, String> data) {
        excelHelpers.setExcelFile(FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignUp");
        String firstName=data.get(SignUpModel.getFirstName());
        String LastName=data.get(SignUpModel.getLastName());
        String orgName=data.get(SignUpModel.getOrganizationName());
        String email=data.get(SignUpModel.getEmail());
        String userName=data.get(SignUpModel.getUsername());
        String phoneNumber = data.get(SignUpModel.getPhoneNumber());
        String password=data.get(SignUpModel.getPassword());
        String confirmPassword=data.get(SignUpModel.getConfirmPassword());
        waits.waitForElements(DriverManager.getDriver(),signUpPageElements.firstName,5000);
        expectedTitle = data.get(SignUpModel.getExpectedTitle());
        String verifyPass=data.get(SignUpModel.getExpectedTitle());

        if (verifyPass.equals("Pass")) {
            phoneNumber = fakerUtils.generateRandomAustralianPhoneNumber();
        }
        signUpPageElements.firstName.sendKeys(firstName);
        signUpPageElements.LastName.sendKeys(LastName);
        signUpPageElements.orgName.sendKeys(orgName);
        signUpPageElements.email.sendKeys(email);
        signUpPageElements.userName.sendKeys(userName);
        signUpPageElements.phoneNumber.sendKeys(phoneNumber);
        signUpPageElements.password.sendKeys(password);
        signUpPageElements.confirmPassword.sendKeys(confirmPassword);
        signUpPageElements.singUpBtn.click();
        if (WebUI.verifyValidationErrorMsg(driver, signUpPageElements.valueRequired, 1)) {
            LogUtils.info(signUpPageElements.valueRequired.getText());
            System.out.println(" Validation error:"+ signUpPageElements.valueRequired.getText());
            return false;
        }
        else{
            if (WebUI.verifyErrorPopup(driver, signUpPageElements.popUpError, 10)) {
                LogUtils.info(signUpPageElements.popUpError.getText());
                System.out.println("popup error"+signUpPageElements.popUpError.getText());
                return false;
            } else {
                System.out.println("no error");
                return true;
            }
        }

    }
}
