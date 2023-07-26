package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import constants.FrameworkConstants;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import keywords.WebUI;
import lahebo.objectRepo.LandingPageOR;
import models.SignInModel;
import models.SignUpModel;
import org.openqa.selenium.WebDriver;
import lahebo.web_elements.SignInPageElements;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

import java.util.Hashtable;

public class SignInPage extends BasePage {
        SignInPageElements singInPageElements;
        WebDriver driver;
//    SingInPageElements singInPageElements = new SingInPageElements(driver);

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        singInPageElements = new SignInPageElements(driver);
    }

    ExcelHelpers excelHelpers = new ExcelHelpers();
    String expectedTitle;
    public static String testName;

    public boolean signIn(Hashtable<String, String> data) {
        excelHelpers.setExcelFile(FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignIn");
        String userName = data.get(SignInModel.getUserName());
        String password = data.get(SignUpModel.getPassword());
        expectedTitle = data.get(SignInModel.getExpectedTitle());
        testName = data.get(SignInModel.getTestCaseID()) + ": " + data.get(SignInModel.getTestCaseName());
        waits.waitForElements(driver, singInPageElements.userName, 5000);
        System.out.println(singInPageElements.userName.getAttribute("placeholder"));

        if (userName.equals("") || password.equals("")) {

            singInPageElements.userName.sendKeys(userName);
            singInPageElements.password.sendKeys(password);
            singInPageElements.signInBtn.click();
            waits.waitForVisibilityOfItem(driver, singInPageElements.validationErrorMsg, 5);
            System.out.println(singInPageElements.validationErrorMsg.getText());

            if (WebUI.verifyValidationErrorMsg(driver, singInPageElements.validationErrorMsg,5) && expectedTitle.equals("fail")) {
                return false;
            } else {

                return false;
            }
        } else {
            singInPageElements.userName.sendKeys(userName);
            singInPageElements.password.sendKeys(password);
            singInPageElements.signInBtn.click();

            if (expectedTitle.equals("pass")) {
                LogUtils.info("Username and password are correct");
                return true;

            } else {

                if (WebUI.verifyErrorPopup(driver,singInPageElements.errorAlert,10)) {
                    LogUtils.info("getting erorr but expected condition satisfied");
                    return false;
                } else {
                    return false;
                }
            }

        }

    }
    public boolean  signIn(String userName, String password) {


        waits.waitForElements(driver, singInPageElements.userName, 5000);
        System.out.println(singInPageElements.userName.getAttribute("placeholder"));
        expectedTitle = "pass";

        if (userName.equals("") || password.equals("")) {

            singInPageElements.userName.sendKeys(userName);
            singInPageElements.password.sendKeys(password);
            singInPageElements.signInBtn.click();
            waits.waitForVisibilityOfItem(driver, singInPageElements.validationErrorMsg, 5);
            System.out.println(singInPageElements.validationErrorMsg.getText());

            if (WebUI.verifyValidationErrorMsg(driver, singInPageElements.validationErrorMsg,5)) {
                return false;
            } else {

                return false;
            }
        } else {
            singInPageElements.userName.sendKeys(userName);
            singInPageElements.password.sendKeys(password);
            singInPageElements.signInBtn.click();

            if (expectedTitle.equals("pass")) {
                LogUtils.info("Username and password are correct");

                return true;

            } else {

                if (WebUI.verifyErrorPopup(driver,singInPageElements.errorAlert,10)) {
                    System.out.println("getting erorr but expected condition satisfied");
                    return false;
                } else {
                    return false;
                }
            }

        }

    }




    public boolean verifyExpectedResult() {
        if (expectedTitle.equals("pass")) {
            return true;
        } else {
            return false;

        }
    }

    public void goToForgetPasswordPage() {
        waits.waitForElements(driver, singInPageElements.forgetPasswordLinkTxt, 5);
        singInPageElements.forgetPasswordLinkTxt.click();
    }

    public static String getTestName() {
        return testName;
    }

    public boolean verifyLoginPage() {
    waits.waitForElements(driver, singInPageElements.logInText, 10);
        String checkSTR = singInPageElements.logInText.getText();
        System.out.println("check"+checkSTR);
        if (checkSTR.equalsIgnoreCase("Sign In")) {
            return true;
        } else {
            return false;
        }
    }

    public void signInExistingUser() throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        String userName = PropertiesHelpers.getValue("Existing_User_UserName");
        String password = PropertiesHelpers.getValue("Existing_User_Password");
        String secretKey = PropertiesHelpers.getValue("Existing_User_SecretKey");
        TwoFactorAuthPage twoFactorAuthPage = new TwoFactorAuthPage(driver);
        LandingPage landingPage = new LandingPage(driver);
        System.out.println(secretKey);
        try {
            driver.get(url_Address);
        } catch (WebDriverException e) {
            LogUtils.error("Driver could not Installed");
            Assert.fail("Driver could not Installed");
        }
        if (signIn(userName, password)) {
            Assert.assertTrue(true, "Password or Username is  correct");
            Assert.assertTrue(twoFactorAuthPage.authenticationWithSecretKey(secretKey), "2FA Authentication Failed");
            landingPage.waitForPageLoading();
            Assert.assertTrue(landingPage.verifyLandingPage(), "landing page could not displayed");
        } else {
            System.out.println("im here into 2");
            boolean expResult = verifyExpectedResult();
            Assert.assertFalse(expResult, "Password or Username is not correct");
        }
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, 10);

    }
    public void signInUserBypass2FA() throws InterruptedException {
        String url_Address = PropertiesHelpers.getValue("URL_RAHEBO");
        String userName = PropertiesHelpers.getValue("Existing_User_UserName");
        String password = PropertiesHelpers.getValue("Existing_User_Password");
        String secretKey = PropertiesHelpers.getValue("Existing_User_SecretKey");
        System.out.println(secretKey);
        try {
            driver.get(url_Address);
        } catch (WebDriverException e) {
            LogUtils.error("Driver could not Installed");
            Assert.fail("Driver could not Installed");
        }
        if (signIn(userName, password)) {
            Assert.assertTrue(true, "Password or Username is  correct");
        }
        WebUI.waitForElementToBeGone(LandingPageOR.spinnerLoader, 10);

    }
}
