package lahebo.web_pages;

import Utils.LogUtils;
import Utils.Two2FActorAuthentication;
import Utils.utility;
import Utils.waits;
import constants.FrameworkConstants;
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
    String secretKeyForTryAgain;
    Two2FActorAuthentication two2FActorAuthentication;
    public TwoFactorAuthPage(WebDriver driver) {
        this.driver = driver;
        twoFactorAuthElements = new TwoFactorAuthElements(driver);
        two2FActorAuthentication = new Two2FActorAuthentication();


    }
    public boolean authenticationWithoutSecretKey() throws InterruptedException {
        String secretKey = PropertiesHelpers.getValue("SECRET_KEY");
        waits.waitForElements(driver, twoFactorAuthElements.otpCode,  5000);
        String code = two2FActorAuthentication.getOptCode(secretKey);
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
        secretKeyForTryAgain = secretKey;
        waits.waitForElements(driver, twoFactorAuthElements.otpCode,  500);
        String code = two2FActorAuthentication.getOptCode(secretKey);
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
    public boolean authenticationWithSecretKeyWithScanCode(String secretKey, String withoutScanCode) throws InterruptedException {
        String code=two2FActorAuthentication.getOptCode(secretKey);
        secretKeyForTryAgain = secretKey;
        if (withoutScanCode.equals("no")) {
            waits.waitForElements(driver, twoFactorAuthElements.otpCode,  5000);
            twoFactorAuthElements.otpCode.sendKeys(code);
        } else if (withoutScanCode.equals("yes")) {
            waits.waitForElements(driver, twoFactorAuthElements.otpCodeInputWithSecretKey,  5000);
            twoFactorAuthElements.otpCodeInputWithSecretKey.sendKeys(code);
        }

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
                LogUtils.info("Message from OTP api: "+successMSg);
                System.out.println(successMSg);
                if (successMSg.equals("Account confirmed successfully.")) {
                    LogUtils.info("Account confirmed successfully");
                    return false;
                }
                else if (successMSg.equals("Account confirmed .") ){
                    return false;
                } else if (successMSg.contains("already been used")) {
                    LogUtils.info("code is already used so we are waiting for 30 second for new code");
                    Thread.sleep(30000);
                    LogUtils.info("wait is over so, again try");
                    String code=two2FActorAuthentication.getOptCode(secretKeyForTryAgain);
                    twoFactorAuthElements.otpCode.clear();
                    waits.waitForElements(driver, twoFactorAuthElements.otpCode,  5000);
                    twoFactorAuthElements.otpCode.sendKeys(code);
                    twoFactorAuthElements.submitBtn.click();
                    return false;
                }

                return true;
            }

        } catch (TimeoutException id) {
            System.out.println("exception  is  here" + id);
            return false;
        } catch (NoSuchElementException exception) {
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
        String userName = FrameworkConstants.USER_EMAIL_ADDRESS;
        String password = FrameworkConstants.USER_EMAIL_ADDRESS;

        GMailHelper gmailHelper = new GMailHelper(userName, password);

        /*
         * Mail search code
         */
        String keyword = "Welcome to Lahebo, your verification code";
        Date afterDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // Example: Retrieve messages from the last 24 hours

        System.out.println("Mail send time : " + afterDate.toString());

        List<Message> inboxMessageList = gmailHelper.searchEmail(0, "INBOX", keyword, afterDate);
        LogUtils.info("size of list of message: "+ inboxMessageList.size());
        int listSize = inboxMessageList.size();
        Message firstMessage = inboxMessageList.get(listSize-1);
        String body = gmailHelper.getText(true, firstMessage);

        System.out.println("Message Subject : " + firstMessage.getSubject());

//        System.out.println("Message Content : " + body);
        if (body.contains("Please find the OTP :")) {
            LogUtils.info("Yes found it Message has been found ");
        } else {
            LogUtils.info("Not found Message");
        }
        String input = body;
        String searchString = "Please find the OTP";
        int n =10;

        String charactersAfterString = gmailHelper.getCharactersAfterString(input, searchString, n);
        String refineNumber = gmailHelper.removeWhitespaceDotColon(charactersAfterString);

        if (charactersAfterString != null) {

            LogUtils.info("Before Refining OTP Code(Gmail Inbox): "+ charactersAfterString);
            LogUtils.info("After Refining OTP Code(Gmail Inbox): "+ refineNumber);


        } else {
            LogUtils.info("String not found or not enough characters after the string");

        }

        return refineNumber;
    }
    public String getGmailInboxPasswordForNewUser() throws InterruptedException, MessagingException, IOException, MessagingException {
        String userName = FrameworkConstants.USER_EMAIL_ADDRESS;
        String password = FrameworkConstants.USER_EMAIL_PASSWORD;
        LogUtils.info("UserName: "+userName+" ----Password: "+password);

        GMailHelper gmailHelper = new GMailHelper(userName, password);

        /*
         * Mail search code
         */
        String keyword = "Your temporary password";
        Date afterDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // Example: Retrieve messages from the last 24 hours

        System.out.println("Mail send time : " + afterDate.toString());

        List<Message> inboxMessageList = gmailHelper.searchEmail(0, "INBOX", keyword, afterDate);
        LogUtils.info("size of list of message: "+ inboxMessageList.size());
        int listSize = inboxMessageList.size();
        Message firstMessage = inboxMessageList.get(listSize-1);
        String body = gmailHelper.getText(true, firstMessage);

        System.out.println("Message Subject : " + firstMessage.getSubject());

//        System.out.println("Message Content : " + body);
        if (body.contains("An admin has invited you to Lahebo")) {
            LogUtils.info("Yes found it Message has been found ");
        } else {
            LogUtils.info("Not found Message");
        }
        String input = body;
        String searchString = "Password";
        int n =25;

        String charactersAfterString = gmailHelper.getCharactersAfterString(input, searchString, n);
        String refineNumber = gmailHelper.removeWhitespaceDotColon(charactersAfterString);

        if (charactersAfterString != null) {

            LogUtils.info("Before Refining OTP Code(Gmail Inbox): "+ charactersAfterString);
            LogUtils.info("After Refining OTP Code(Gmail Inbox): "+ refineNumber);


        } else {
            LogUtils.info("String not found or not enough characters after the string");

        }

        return utility.extractTextBetweenTags(refineNumber, ">", "<");
    }

    public String getSecretKeyNewUser() {
        try {
            waits.waitForElements(driver,twoFactorAuthElements.secretKey, 10);
            secretKeyNewUser = twoFactorAuthElements.secretKey.getText();
        } catch (NoSuchElementException exception) {
        }
        return secretKeyNewUser;
    }
}
