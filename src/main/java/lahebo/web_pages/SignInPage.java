package lahebo.web_pages;

import Utils.WebUI;
import Utils.waits;
import constants.FrameworkConstants;
import driver.DriverManager;
import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.SignInModel;
import models.SignUpModel;
import org.openqa.selenium.WebDriver;
import lahebo.web_elements.SingInPageElements;

import java.util.Hashtable;

public class SignInPage extends BasePage {
        SingInPageElements singInPageElements;
    WebDriver driver;
//    SingInPageElements singInPageElements = new SingInPageElements(driver);

    public SignInPage(WebDriver driver) {
        System.out.println("singINPage Constructor");
        this.driver = driver;
        singInPageElements = new SingInPageElements(driver);

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
        System.out.println("basepage" + driver);
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
                System.out.println("Username and password are correct");
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

    public static String getTestName() {
        return testName;
    }
}
