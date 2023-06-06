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
    ExcelHelpers excelHelpersForSetValue = new ExcelHelpers();
    WebDriver driver;
    public static String expectedTitle;
    public static String  randomUsername;
    public static String randomEmail;
    public static String randomPhone;
    public static String password;



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
        password=data.get(SignUpModel.getPassword());
        String confirmPassword=data.get(SignUpModel.getConfirmPassword());
        waits.waitForElements(DriverManager.getDriver(),signUpPageElements.firstName,5000);
        expectedTitle = data.get(SignUpModel.getExpectedTitle());
        String verifyPass=data.get(SignUpModel.getExpectedTitle());

        if (verifyPass.equals("Pass")) {
            LogUtils.info("We got the pass condition for sighup");
            phoneNumber = fakerUtils.generateRandomAustralianPhoneNumber();
            LogUtils.info("Random phone number: "+phoneNumber);
            randomPhone = phoneNumber;

            int randomInt = fakerUtils.generateRandomInt();
            String fileEmail=data.get(SignUpModel.getEmail());

            email=fileEmail+"+"+randomInt+"@gmail.com";
            LogUtils.info("Random Email :"+email);
            randomEmail = email;

            String fileUserName=data.get(SignUpModel.getUsername());
            userName=fileUserName+randomInt;
            LogUtils.info("Random Gmail number"+userName);
            randomUsername = userName;


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
                String popUpMessage = signUpPageElements.popUpError.getText();
                System.out.println("popup error"+signUpPageElements.popUpError.getText());
                if (popUpMessage.equals("Register successfully!")) {
                    LogUtils.info("All Condition have been Passed and now moving to Email OTP Verification");
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("no error");
                return true;
            }
        }

    }

    public void saveDataIntoFile() {
        PropertiesHelpers.setValue("New_User_UserName", randomUsername);
        PropertiesHelpers.setValue("New_User_Password", password);

    }
}
