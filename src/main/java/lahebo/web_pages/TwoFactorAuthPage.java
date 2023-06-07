package lahebo.web_pages;

import Utils.LogUtils;
import Utils.Two2FActorAuthentication;
import Utils.waits;
import helpers.PropertiesHelpers;
import lahebo.web_elements.TwoFactorAuthElements;
import lombok.extern.java.Log;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import helpers.GMailHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TwoFactorAuthPage extends BasePage{
    TwoFactorAuthElements twoFactorAuthElements;
    String secretKeyNewUser;
    WebDriver driver;
    public TwoFactorAuthPage(WebDriver driver) {
        this.driver = driver;
        twoFactorAuthElements = new TwoFactorAuthElements(driver);


    }
    public boolean authenticationWithSecretKey() throws InterruptedException {
        String secretKey = PropertiesHelpers.getValue("SECRET_KEY");
        waits.waitForElements(driver, twoFactorAuthElements.otpCode,  5000);
        String code = Two2FActorAuthentication.getOptCode(secretKey);
        twoFactorAuthElements.otpCode.sendKeys(code);
        twoFactorAuthElements.submitBtn.click();
        if (code.length() < 6) {
            if (verifyCodeLength()) {
                return false;
            } else {
                return true;
            }
        } else {
            if (verifyInvalidOTP()) {
                return false;
            } else {
                return true;
            }

        }
    }
    public boolean authenticationWithSecretKey(String secretKey) throws InterruptedException {
        waits.waitForElements(driver, twoFactorAuthElements.otpCodeInputWithSecretKey,  5000);
        String code = Two2FActorAuthentication.getOptCode(secretKey);
        twoFactorAuthElements.otpCodeInputWithSecretKey.sendKeys(code);
        twoFactorAuthElements.submitBtn.click();
        if (code.length() < 6) {
            if (verifyCodeLength()) {
                return false;
            } else {
                return true;
            }
        } else {
            if (verifyInvalidOTP()) {
                return false;
            } else {
                return true;
            }

        }
    }
    public boolean authenticationWithSecretKeyWithScanCode(String secretKey, String withoutScanCode) throws InterruptedException {
        String code=Two2FActorAuthentication.getOptCode(secretKey);
        if (withoutScanCode.equals("no")) {
            waits.waitForElements(driver, twoFactorAuthElements.otpCode,  5000);
            twoFactorAuthElements.otpCode.sendKeys(code);
            twoFactorAuthElements.submitBtn.click();
        }
        if (code.length() < 6) {
            if (verifyCodeLength()) {
                return false;
            } else {
                return true;
            }
        } else {
            if (verifyInvalidOTP()) {
                return false;
            } else {
                return true;
            }

        }
    }
    public boolean authentication(String otpCode) throws InterruptedException {
        twoFactorAuthElements.otpCodeInputForInbox.sendKeys(otpCode);
        twoFactorAuthElements.submitBtn.click();
        if (otpCode.length() < 6) {
            if (verifyCodeLength()) {
                return false;
            } else {
                return true;
            }
        } else {
            if (verifyInvalidOTP()) {
                return false;
            } else {
                return true;
            }

        }
    }

    public boolean verifyInvalidOTP() {
        try {
            waits.waitForVisibilityOfItem(driver, twoFactorAuthElements.errorPopUp, 5);
            if (twoFactorAuthElements.errorPopUp.isDisplayed()) {
                String successMSg = twoFactorAuthElements.errorPopUp.getText();
                System.out.println(successMSg);
                if (successMSg.equals("Account confirmed successfully.")) {
                    LogUtils.info("Account confirmed successfully");
                    return false;
                }
                else if (successMSg.equals("Account confirmed .") ){
                    return false;
                }
                return true;
            }

        } catch (TimeoutException id) {
            System.out.println("exception  is  here" + id);
            return false;
        } catch (NoSuchElementException exception) {
            return false;
        }


        return false;

    } public boolean verifyCodeLength() {
        waits.waitForVisibilityOfItem(driver,twoFactorAuthElements.validationErrorMsg,5);
        if (twoFactorAuthElements.validationErrorMsg.isDisplayed()) {
            return true;
        }
        return false;
    }
    public String getGmailInboxOTP() throws InterruptedException, MessagingException, IOException, MessagingException {
        String userName = "zubairulamin8@gmail.com";
        String password = "ixrvngupvrvtkuqk";

        GMailHelper gmailHelper = new GMailHelper(userName, password);

        /*
         * Mail search code
         */
        String keyword = "Welcome to Lahebo, your verification code";
        Date afterDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // Example: Retrieve messages from the last 24 hours
        System.out.println("Mail send time : " + afterDate.toString());

        List<Message> inboxMessageList = gmailHelper.searchEmail(0, "INBOX", keyword, afterDate);
        System.out.println("size of list: "+ inboxMessageList.size());
        int listSize = inboxMessageList.size();
        Message firstMessage = inboxMessageList.get(listSize-1);
        String body = gmailHelper.getText(true, firstMessage);

        System.out.println("Message Subject : " + firstMessage.getSubject());
//        System.out.println("Message Content : " + body);
        if (body.contains("Please find the OTP :")) {
            System.out.println("Yes found it");
        } else {
            System.out.println("not found");
        }
        String input = body;
        String searchString = "Please find the OTP";
        int n =10;

        String charactersAfterString = gmailHelper.getCharactersAfterString(input, searchString, n);
        String refineNumber = gmailHelper.removeWhitespaceDotColon(charactersAfterString);

        if (charactersAfterString != null) {
            System.out.println(charactersAfterString);
            System.out.println(refineNumber);
        } else {
            System.out.println("String not found or not enough characters after the string.");
        }

        return refineNumber;
    }

    public String getSecretKeyNewUser() {
        try {
            waits.waitForElements(driver,twoFactorAuthElements.secretKey, 10);
            secretKeyNewUser = twoFactorAuthElements.secretKey.getText();
        } catch (NoSuchElementException exception) {
        }
        PropertiesHelpers.setValue("New_User_SecretKey", secretKeyNewUser);
        return secretKeyNewUser;
    }
}
