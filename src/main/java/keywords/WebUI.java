/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package keywords;


import Utils.*;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import constants.FrameworkConstants;
import driver.DriverManager;
import enums.FailureHandling;
import helpers.Helpers;
import io.qameta.allure.Step;
import lahebo.path_repo.LandingPagePR;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v112.network.Network;
import org.openqa.selenium.devtools.v112.network.model.Headers;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import report.AllureManager;
import report.ExtentReportManager;
import report.ExtentTestManager;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.*;

import static constants.FrameworkConstants.*;



/**
 The WebUI keyword is a common class that serves as a library for pre-built functions with various customizations using Selenium and Java.
 It returns a Class containing static functions. It can be invoked by using the class name followed by the function name (WebUI.method).
 */

public class WebUI {

    private static SoftAssert softAssert = new SoftAssert();
    private static int tryCheck=0;


    public static void stopSoftAssertAll() {
        softAssert.assertAll();
    }

    public static void smartWait() {
        if (ACTIVE_PAGE_LOADED.trim().toLowerCase().equals("true")) {
            waitForPageLoaded();
        }
        sleep(WAIT_SLEEP_STEP);
    }
    public static boolean verifyValidationErrorMsg(WebDriver driver, WebElement element, int timeout) {
        waits.waitForVisibilityOfItem(driver, element, timeout);
        try {
            if (element.isDisplayed()) {
                LogUtils.info(element.getText());
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
    public static boolean verifyErrorPopup(WebDriver driver, WebElement element, int timeout) {
        waits.waitForVisibilityOfItem(driver, element, timeout);
        if (element.isDisplayed()) {
            LogUtils.info(element.getText());
            return true;
        } else {
            return false;
        }
    }
    public static boolean verifyPopup(By by,String message, int timeout) throws InterruptedException {
        System.out.println("my message"+message);
        WebElement element = waitForElementVisible(by,timeout);
        if (element.isDisplayed()) {
            if (element.getText().equalsIgnoreCase(message)) {
                System.out.println("result message"+element.getText());
                LogUtils.info(element.getText());
                return true;
            }

        } else {
            return false;
        }
        LogUtils.info(element.getText());
        return false;


    }
    public static void addScreenshotToReport(String screenshotName) {
        if (SCREENSHOT_ALL_STEPS.equals(YES)) {
            if (ExtentTestManager.getExtentTest() != null) {

                ExtentReportManager.addScreenShot(DriverManager.getDriver(),Helpers.makeSlug(screenshotName));
            }
            //CaptureHelpers.captureScreenshot(DriverManager.getDriver(), Helpers.makeSlug(screenshotName));
            AllureManager.takeScreenshotStep();
        }
    }

    public static String getPathDownloadDirectory() {
        String path = "";
        String machine_name = System.getProperty("user.home");
        path = machine_name + File.separator + "Downloads";
        return path;
    }

    public static int countFilesInDownloadDirectory() {
        String pathFolderDownload = getPathDownloadDirectory();
        File file = new File(pathFolderDownload);
        int i = 0;
        for (File listOfFiles : file.listFiles()) {
            if (listOfFiles.isFile()) {
                i++;
            }
        }
        return i;
    }

    public static boolean verifyFileContainsInDownloadDirectory(String fileName) {
        boolean flag = false;
        try {
            String pathFolderDownload = getPathDownloadDirectory();
            File dir = new File(pathFolderDownload);
            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                flag = false;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(fileName)) {
                    flag = true;
                }
            }
            return flag;
        } catch (Exception e) {
            e.getMessage();
            return flag;
        }
    }

    public static boolean verifyFileEqualsInDownloadDirectory(String fileName) {
        boolean flag = false;
        try {
            String pathFolderDownload = getPathDownloadDirectory();
            File dir = new File(pathFolderDownload);
            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                flag = false;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().equals(fileName)) {
                    flag = true;
                }
            }
            return flag;
        } catch (Exception e) {
            e.getMessage();
            return flag;
        }
    }

    public static Boolean verifyDownloadFileContainsNameCompletedWaitTimeout(String fileName, int timeoutSeconds) {
        boolean check = false;
        int i = 0;
        while (i < timeoutSeconds) {
            boolean exist = verifyFileContainsInDownloadDirectory(fileName);
            if (exist == true) {
                i = timeoutSeconds;
                return check = true;
            }
            sleep(1);
            i++;
        }
        return check;
    }


    public static Boolean verifyDownloadFileEqualsNameCompletedWaitTimeout(String fileName, int timeoutSeconds) {
        boolean check = false;
        int i = 0;
        while (i < timeoutSeconds) {
            boolean exist = verifyFileEqualsInDownloadDirectory(fileName);
            if (exist == true) {
                i = timeoutSeconds;
                return check = true;
            }
            sleep(1);
            i++;
        }
        return check;
    }

    public static void deleteAllFileInDownloadDirectory() {
        try {
            String pathFolderDownload = getPathDownloadDirectory();
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void deleteAllFileInDirectory(String pathDirectory) {
        try {
            File file = new File(pathDirectory);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Step("Verify File Downloaded With JS [Equals]: {0}")
    public static Boolean verifyFileDownloadedWithJS_Equals(String fileName) {
        getURL("chrome://downloads");
        sleep(3);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        String element = (String) js.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#show').getAttribute('title')");
        File file = new File(element);
        LogUtils.info(element);
        LogUtils.info(file.getName());
        if (file.exists() && file.getName().trim().equals(fileName)) {
            return true;
        } else {
            return false;
        }
    }

    @Step("Verify File Downloaded With JS [Contains]: {0}")
    public static Boolean verifyFileDownloadedWithJS_Contains(String fileName) {
        getURL("chrome://downloads");
        sleep(3);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        String element = (String) js.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#show').getAttribute('title')");
        File file = new File(element);
        LogUtils.info(element);
        LogUtils.info(file.getName());
        if (file.exists() && file.getName().trim().contains(fileName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Login as Authentication on URL
     *
     * @param url
     * @param username
     * @param password
     */
    @Step("Get to URL {0} with authentication")
    public static void getToUrlAuthentication(String url, String username, String password) {
        // Get the devtools from the running driver and create a session
        DevTools devTools = ((HasDevTools) DriverManager.getDriver()).getDevTools();
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.of(100000), Optional.of(100000), Optional.of(100000)));
        String auth = username + ":" + password;

        // Encoding the username and password using Base64 (java.util)
        String encodeToString = Base64.getEncoder().encodeToString(auth.getBytes());

        // Pass the network header -> Authorization : Basic <encoded String>
        Map<String, Object> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + encodeToString);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        LogUtils.info("getToUrlAuthentication with URL: " + url);
        LogUtils.info("getToUrlAuthentication with Username: " + username);
        LogUtils.info("getToUrlAuthentication with Password: " + password);
        // Load the application url
        getURL(url);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
    }

    /**

     Retrieve the textual content of a QR Code image.
     @param by an object of type By representing the element
     @return the text content of the QR Code
     */
    @Step("Get QR code from image {0}")
    public static String getQRCodeFromImage(By by) {
        String qrCodeURL = WebUI.getAttributeElement(by, "src");
        //Create an object of URL Class
        URL url = null;
        try {
            url = new URL(qrCodeURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        //Pass the URL class object to store the file as image
        BufferedImage bufferedimage = null;
        try {
            bufferedimage = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Process the image
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedimage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        //To Capture details of QR code
        Result result = null;
        try {
            result = new MultiFormatReader().decode(binaryBitmap);
        } catch (com.google.zxing.NotFoundException e) {
            throw new RuntimeException(e);
        }
        return result.getText();
    }

    //Handle HTML5 validation message and valid value

    /**
     Check if a field is required to be filled or not.
     @param by an element of type By
     @return true/false corresponding to the required status
     */

    @Step("Verify HTML5 message of element {0} required field")
    public static Boolean verifyHTML5RequiredField(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Boolean verifyRequired = (Boolean) js.executeScript("return arguments[0].required;", getWebElement(by));
        return verifyRequired;
    }
    /**

     Check if the value entered in a field is valid or not.
     @param by an element of type By
     @return true/false corresponding to the validity status
     */
    @Step("Verify HTML5 message of element {0} valid")
    public static Boolean verifyHTML5ValidValueField(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        Boolean verifyValid = (Boolean) js.executeScript("return arguments[0].validity.valid;", getWebElement(by));
        return verifyValid;
    }


    /**

     Retrieve the validation message from the HTML5 attribute of a field.
     @param by an element of type By
     @return the text value of the validation message (String)
     */
    @Step("Get HTML5 message of element {0}")
    public static String getHTML5MessageField(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        String message = (String) js.executeScript("return arguments[0].validationMessage;", getWebElement(by));
        return message;
    }


    /**
     * Khôi phục cửa sổ và đặt kích thước cửa sổ.
     *
     * @param widthPixel  is Width with Pixel
     * @param heightPixel is Height with Pixel
     */
    public static void setWindowSize(int widthPixel, int heightPixel) {
        DriverManager.getDriver().manage().window().setSize(new Dimension(widthPixel, heightPixel));
    }

    /**
     * Di chuyển cửa sổ đến vị trí đã chọn X, Y tính từ 0 gốc trái trên cùng
     *
     * @param X (int) - ngang
     * @param Y (int) - dọc
     */
    public static void setWindowPosition(int X, int Y) {
        DriverManager.getDriver().manage().window().setPosition(new Point(X, Y));
    }

    public static void maximizeWindow() {
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void minimizeWindow() {
        DriverManager.getDriver().manage().window().minimize();
    }

    /**
     * Chụp ảnh màn hình tại vị trí element. Không chụp hết cả màn hình.
     *
     * @param by          là element thuộc kiểu By
     * @param elementName để đặt tên file ảnh .png
     */
    public static void screenshotElement(By by, String elementName) {
        File scrFile = getWebElement(by).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + elementName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * In trang hiện tại trong trình duyệt.
     * Note: Chỉ hoạt động ở chế độ headless
     *
     * @param endPage là tổng số trang cần in ra. Tính từ 1.
     * @return is content of page form PDF file
     */
    public static String printPage(int endPage) {
        PrintOptions printOptions = new PrintOptions();
        //From page 1 to end page
        printOptions.setPageRanges("1-" + endPage);

        Pdf pdf = ((PrintsPage) DriverManager.getDriver()).print(printOptions);
        return pdf.getContent();
    }

    public static JavascriptExecutor getJsExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js;
    }

    /**
     * Chuyển đổi đối tượng dạng By sang WebElement
     * Để tìm kiếm một element
     *
     * @param by là element thuộc kiểu By
     * @return Trả về là một đối tượng WebElement
     */
    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    /**
     Convert a By object to a WebElement
     to find multiple elements.
     @param by is an element of type By
     @return Returns a List of WebElement objects
     */
    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    /**
     * In ra câu message trong Console log
     *
     * @param object truyền vào object bất kỳ
     */
    public static void logConsole(@Nullable Object object) {
        System.out.println(object);
    }

    /**
     * Chờ đợi ép buộc với đơn vị là Giây
     *
     * @param second là số nguyên dương tương ứng số Giây
     */
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allow popup notifications của browser trên website
     *
     * @return giá trị đã setup Allow - thuộc đối tượng ChromeOptions
     */
    public static ChromeOptions notificationsAllow() {
        // Tạo Map để lưu trữ các tùy chọn
        Map<String, Object> prefs = new HashMap<String, Object>();

        // Thêm khóa và giá trị vào Map như sau để tắt thông báo của trình duyệt
        // Truyền đối số 1 để CHO PHÉP và 2 để CHẶN
        prefs.put("profile.default_content_setting_values.notifications", 1);

        // Tạo một phiên ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Dùng hàm setExperimentalOption thực thi giá trị thông qua đối tượng prefs trên
        options.setExperimentalOption("prefs", prefs);

        //Trả về giá trị đã setup thuộc đối tượng ChromeOptions
        return options;
    }

    /**
     * Block popup notifications của browser trên website
     *
     * @return giá trị đã setup Block - thuộc đối tượng ChromeOptions
     */
    public static ChromeOptions notificationsBlock() {
        // Tạo Map để lưu trữ các tùy chọn
        Map<String, Object> prefs = new HashMap<String, Object>();

        // Thêm khóa và giá trị vào Map như sau để tắt thông báo của trình duyệt
        // Truyền đối số 1 để CHO PHÉP và 2 để CHẶN
        prefs.put("profile.default_content_setting_values.notifications", 2);

        // Tạo một phiên ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Dùng hàm setExperimentalOption thực thi giá trị thông qua đối tượng prefs trên
        options.setExperimentalOption("prefs", prefs);

        //Trả về giá trị đã setup thuộc đối tượng ChromeOptions
        return options;
    }

    /**
     * Upload file kiểu click hiện form chọn file local trong máy tính của bạn
     *
     * @param by       là element thuộc kiểu By
     * @param filePath đường dẫn tuyệt đối đến file trên máy tính của bạn
     */
    @Step("Upload File With Local Form")
    public static void uploadFileWithLocalForm(By by, String filePath) {
        smartWait();

        Actions action = new Actions(DriverManager.getDriver());
        //Click để mở form upload
        action.moveToElement(getWebElement(by)).click().perform();
        sleep(3);

        // Khởi tạo Robot class
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        //Check OS for Windows
        if (BrowserInfoUtils.isWindows()) {
            // Nhấn Control+V để dán
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            // Xác nhận Control V trên
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(2000);
            // Nhấn Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        //Check OS for MAC
        if (BrowserInfoUtils.isMac()) {
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(1000);

            //Open goto MAC
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);

            //Paste the clipboard value
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(1000);

            //Press Enter key to close the Goto MAC and Upload on MAC
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }

        LogUtils.info("Upload File With Local Form: " + filePath);
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.info("Upload File With Local Form: " + filePath);
        }
        AllureManager.saveTextLog("Upload File With Local Form: " + filePath);

    }

    /**
     * Upload file kiểu kéo link trực tiếp vào ô input
     *
     * @param by       truyền vào an element of object type By
     * @param filePath đường dẫn tuyệt đối đến file
     */
    @Step("Upload File with SendKeys")
    public static void uploadFileWithSendKeys(By by, String filePath) {
        smartWait();

        waitForElementVisible(by).sendKeys(filePath);

        LogUtils.info("Upload File with SendKeys");
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.info("Upload File with SendKeys");
        }
        AllureManager.saveTextLog("Upload File with SendKeys");

    }

    @Step("Get Current URL")
    public static String getCurrentUrl() {
        smartWait();
        LogUtils.info("Get Current URL: " + DriverManager.getDriver().getCurrentUrl());
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.info("Get Current URL: " + DriverManager.getDriver().getCurrentUrl());
        }
        AllureManager.saveTextLog("Get Current URL: " + DriverManager.getDriver().getCurrentUrl());
        return DriverManager.getDriver().getCurrentUrl();
    }

    @Step("Get Page Title")
    public static String getPageTitle() {
        smartWait();
        String title = DriverManager.getDriver().getTitle();
        LogUtils.info("Get Page Title: " + DriverManager.getDriver().getTitle());
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.info("Get Page Title: " + DriverManager.getDriver().getTitle());
        }
        AllureManager.saveTextLog("Get Page Title: " + DriverManager.getDriver().getTitle());
        return title;
    }

    public static boolean getPageTitle(String pageTitle) {
        smartWait();
        return getPageTitle().equals(pageTitle);
    }

    public static boolean verifyPageContainsText(String text) {
        smartWait();
        return DriverManager.getDriver().getPageSource().contains(text);
    }

    //Handle checkbox and radio button

    public static boolean verifyElementChecked(By by) {
        smartWait();

        boolean checked = getWebElement(by).isSelected();

        if (checked) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyElementChecked(By by, String message) {
        smartWait();

        waitForElementVisible(by);

        boolean checked = getWebElement(by).isSelected();

        if (checked) {
            return true;
        } else {
            Assert.fail(message);
            return false;
        }
    }
    //Get element with for dynamic path
    public static WebElement getWebElementByStringPath(String path) {
        smartWait();
        return DriverManager.getDriver().findElement(By.cssSelector(path));

    }
    public static By getByObjStringPath(String path) {
        return By.cssSelector(path);

    }

    //Handle dropdown

    /**
     Select a value in the dropdown with dynamic format (not pure Select Option)
     @param objectListItem is the locator of the list item in the form of By object
     @param text the value to select in the form of item's Text
     @return click to select a specified item with the Text value
     */
    public static boolean selectOptionDynamic(By objectListItem, String text) {
        smartWait();
        // For dynamic dropdowns (div, li, span, etc., not select options)

        try {
            List<WebElement> elements = getWebElements(objectListItem);
            LogUtils.info("size of list: "+elements.size());
            WebElement ele = elements.get(0);

            if (ele.getText().equalsIgnoreCase("Sorry, no matching options.")) {
                Thread.sleep(5000);
                elements = getWebElements(objectListItem);
            }

            for (WebElement element : elements) {
                LogUtils.info(element.getText());
                if (element.getText().equalsIgnoreCase(text)) {
                    element.click();
                    return true;
                }

            }
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            e.getMessage();
        }
        return false;

    }
    public static boolean selectListOptionRandomly(By objectListItem) {
        smartWait();
        int randomOption=0;
        int iteration = 1;
        int wait = 0;
        // For dynamic dropdowns (div, li, span, etc., not select options)

        try {
            List<WebElement> elements = getWebElements(objectListItem);

            WebElement ele = elements.get(0);

            if (elements.size() == 1) {
                LogUtils.info("waiting for List Items to Load");
            }
        try {
            while (ele.getText().equalsIgnoreCase("Sorry, no matching options.")) {

                elements = getWebElements(objectListItem);
                ele = elements.get(0);
                LogUtils.info("Still Waiting...");
                wait++;
                if (wait == 1000) {
                    LogUtils.info("could not load in expected time");
                    break;
                }
            }
        }catch (StaleElementReferenceException e) {
                    // Handle the StaleElementReferenceException here
                    // You can log the exception or perform any other desired action
                    LogUtils.info("here in catch ");
                    elements=DriverManager.getDriver().findElements(objectListItem);
            selectListOptionRandomly(objectListItem);

                }

            LogUtils.info("Wait Over!! size of list: "+elements.size());
            randomOption=fakerUtils.generateRandomInt(elements.size());
            LogUtils.info("random number generated to select list is: " + randomOption);
//            if (ele.getText().equalsIgnoreCase("Sorry, no matching options.")) {
//                Thread.sleep(5000);
//                elements = getWebElements(objectListItem);
//                LogUtils.info("size of list: "+elements.size());
//                randomOption=fakerUtils.generateRandomInt(elements.size());
//            }

            try{
            for (WebElement element : elements) {

                if (randomOption==iteration) {
                    LogUtils.info("random number: "+randomOption+" iteration: "+iteration);
                    element.click();
                    LogUtils.info(iteration+" :"+element.getText()+" Selected ");
                    return true;
                }
                iteration++;
            }
        }catch (StaleElementReferenceException e) {
            // Handle the StaleElementReferenceException here
            // You can log the exception or perform any other desired action
            LogUtils.info("here in catch ");

            elements=DriverManager.getDriver().findElements(objectListItem);
                selectListOptionRandomly(objectListItem);

        }
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            e.getMessage();
        }
        return false;

    }
    public static boolean selectCheckBoxOptionRandomly(By objectListItem) {
        smartWait();
        int randomOption=0;
        int iteration = 1;
        // For dynamic dropdowns (div, li, span, etc., not select options)

        try {
            List<WebElement> elements = getWebElements(objectListItem);

            WebElement ele = elements.get(0);

            if (elements.size() == 1) {
                LogUtils.info("waiting for List Items to Load");
            }
            try {
                while (ele.getText().equalsIgnoreCase("Sorry, no matching options.")) {

                    elements = getWebElements(objectListItem);
                    ele = elements.get(0);
                    LogUtils.info("Still Waiting...");
                }
            }catch (StaleElementReferenceException e) {
                // Handle the StaleElementReferenceException here
                // You can log the exception or perform any other desired action
                LogUtils.info("here in catch ");
                elements=DriverManager.getDriver().findElements(objectListItem);
                selectCheckBoxOptionRandomly(objectListItem);

            }

            LogUtils.info("Wait Over!! size of list: "+elements.size());
            randomOption=fakerUtils.generateRandomInt(elements.size());

//            if (ele.getText().equalsIgnoreCase("Sorry, no matching options.")) {
//                Thread.sleep(5000);
//                elements = getWebElements(objectListItem);
//                LogUtils.info("size of list: "+elements.size());
//                randomOption=fakerUtils.generateRandomInt(elements.size());
//            }

            try{
                for (WebElement element : elements) {

                    if (randomOption==iteration) {
                        element.click();
                        LogUtils.info(iteration+" :"+element.getText()+" Selected ");
                        return true;
                    }
                    iteration++;
                }
            }catch (StaleElementReferenceException e) {
                // Handle the StaleElementReferenceException here
                // You can log the exception or perform any other desired action
                LogUtils.info("here in catch ");

                elements=DriverManager.getDriver().findElements(objectListItem);
                selectCheckBoxOptionRandomly(objectListItem);

            }
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            e.getMessage();
        }
        return false;

    }
    public static boolean selectMultpleCheckBoxDynamic(By objectListItem,By objectCheckBox, String text) {
        smartWait();
        // For dynamic dropdowns (div, li, span, etc., not select options)

        try {
            List<WebElement> elements = getWebElements(objectListItem);
            LogUtils.info("size of list: "+elements.size());
            WebElement ele = elements.get(0);

            if (ele.getText().equalsIgnoreCase("Sorry, no matching options.")) {
                Thread.sleep(5000);
                LogUtils.info("waiting for List to load");
                elements = getWebElements(objectListItem);
            }
            else {
                LogUtils.info("List is loaded");
                clickElement(objectCheckBox);
                return true;

            }
//            for (WebElement element : elements) {
//                LogUtils.info(element.getText());
//                if (element.getText().equalsIgnoreCase(text)) {
//                    clickElement(objectCheckBox);
////                    element.click();
//                    return true;
//                }
//
//            }
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            e.getMessage();
        }
        return true;

    }


    public static boolean verifyOptionDynamicExist(By objectListItem, String text) {
        smartWait();

        try {
            List<WebElement> elements = getWebElements(objectListItem);

            for (WebElement element : elements) {
                LogUtils.info(element.getText());
                if (element.getText().toLowerCase().trim().contains(text.toLowerCase().trim())) {
                    return true;
                }
            }
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            e.getMessage();
        }
        return false;
    }

    public static int getOptionDynamicTotal(By objectListItem) {
        smartWait();

        try {
            List<WebElement> elements = getWebElements(objectListItem);
            return elements.size();
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            e.getMessage();
        }
        return 0;
    }

    //Dropdown (Select Option)
    public static void selectOptionByText(By by, String text) {
        smartWait();
        Select select = new Select(getWebElement(by));
        select.selectByVisibleText(text);
    }

    public static void selectOptionByValue(By by, String value) {
        smartWait();

        Select select = new Select(getWebElement(by));
        select.selectByValue(value);
    }

    public static void selectOptionByIndex(By by, int index) {
        smartWait();

        Select select = new Select(getWebElement(by));
        select.selectByIndex(index);
    }

    public static void verifyOptionTotal(By element, int total) {
        smartWait();

        Select select = new Select(getWebElement(element));
        Assert.assertEquals(total, select.getOptions().size());
    }

    public static boolean verifySelectedByText(By by, String text) {
        sleep(WAIT_SLEEP_STEP);

        Select select = new Select(getWebElement(by));
        LogUtils.info("Option Selected by text: " + select.getFirstSelectedOption().getText());
        return select.getFirstSelectedOption().getText().equals(text);
    }

    public static boolean verifySelectedByValue(By by, String optionValue) {
        sleep(WAIT_SLEEP_STEP);

        Select select = new Select(getWebElement(by));
        LogUtils.info("Option Selected by value: " + select.getFirstSelectedOption().getAttribute("value"));
        return select.getFirstSelectedOption().getAttribute("value").equals(optionValue);
    }

    public static boolean verifySelectedByIndex(By by, int index) {
        sleep(WAIT_SLEEP_STEP);

        boolean res = false;
        Select select = new Select(getWebElement(by));
        int indexFirstOption = select.getOptions().indexOf(select.getFirstSelectedOption());
        LogUtils.info("The First Option selected by index: " + indexFirstOption);
        LogUtils.info("Expected index: " + index);
        if (indexFirstOption == index) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    //Handle frame iframe

    public static void switchToFrameByIndex(int index) {
        sleep(WAIT_SLEEP_STEP);

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        //DriverManager.getDriver().switchTo().frame(Index);
    }

    public static void switchToFrameByIdOrName(String IdOrName) {
        sleep(WAIT_SLEEP_STEP);

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IdOrName));
    }

    public static void switchToFrameByElement(By by) {
        sleep(WAIT_SLEEP_STEP);

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    public static void switchToDefaultContent() {
        sleep(WAIT_SLEEP_STEP);

        DriverManager.getDriver().switchTo().defaultContent();
    }


    public static void switchToWindowOrTabByPosition(int position) {
        sleep(WAIT_SLEEP_STEP);

        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandles().toArray()[position].toString());
    }

    public static void switchToWindowOrTabByTitle(String title) {
        sleep(WAIT_SLEEP_STEP);

        //Store the ID of the original window
        String originalWindow = DriverManager.getDriver().getWindowHandle();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        //Wait for the new window or tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //Loop through until we find a new window handle
        for (String windowHandle : DriverManager.getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                DriverManager.getDriver().switchTo().window(windowHandle);
                if (DriverManager.getDriver().getTitle().equals(title)) {
                    break;
                }
            }
        }

    }

    public static void switchToWindowOrTabByUrl(String url) {
        sleep(WAIT_SLEEP_STEP);

        //Store the ID of the original window
        String originalWindow = DriverManager.getDriver().getWindowHandle();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        //Wait for the new window or tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //Loop through until we find a new window handle
        for (String windowHandle : DriverManager.getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                DriverManager.getDriver().switchTo().window(windowHandle);
                if (DriverManager.getDriver().getCurrentUrl().equals(url)) {
                    break;
                }
            }
        }

    }

    public static void closeCurrentWindow() {
        DriverManager.getDriver().close();
    }

    public static boolean verifyNumberOfWindowsOrTab(int number) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT)).until(ExpectedConditions.numberOfWindowsToBe(number));
    }

    public static void openNewTab() {
        sleep(WAIT_SLEEP_STEP);

        // Opens a new tab and switches to new tab
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    public static void openNewWindow() {
        sleep(WAIT_SLEEP_STEP);
        // Opens a new window and switches to new window
        DriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
    }

    public static void switchToMainWindow() {
        sleep(WAIT_SLEEP_STEP);
        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandles().toArray()[0].toString());
    }

    public static void switchToMainWindow(String originalWindow) {
        sleep(WAIT_SLEEP_STEP);
        DriverManager.getDriver().switchTo().window(originalWindow);
    }

    public static void switchToLastWindow() {
        smartWait();

        Set<String> windowHandles = DriverManager.getDriver().getWindowHandles();
        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandles().toArray()[windowHandles.size() - 1].toString());
    }

    /*
        ========== Handle Alert ==================
     */

    public static void alertAccept() {
        smartWait();

        DriverManager.getDriver().switchTo().alert().accept();
    }

    public static void alertDismiss() {
        smartWait();

        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    public static void alertGetText() {
        smartWait();

        DriverManager.getDriver().switchTo().alert().getText();
    }

    public static void alertSetText(String text) {
        smartWait();

        DriverManager.getDriver().switchTo().alert().sendKeys(text);
    }

    public static boolean verifyAlertPresent(int timeOut) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Throwable error) {
            Assert.fail("Not found Alert.");
            return false;
        }
    }

    //Handle Elements

    public static List<String> getListElementsText(By by) {
        smartWait();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        List<WebElement> listElement = getWebElements(by);
        List<String> listText = new ArrayList<>();

        for (WebElement e : listElement) {
            listText.add(e.getText());
        }

        return listText;
    }

    public static boolean verifyElementExists(By by) {
        smartWait();

        boolean res;
        List<WebElement> elementList = getWebElements(by);
        if (elementList.size() > 0) {
            res = true;
            LogUtils.info("Element existing");
        } else {
            res = false;
            LogUtils.error("Element not exists");

        }
        return res;
    }

    @Step("Verify Equals: {0} ---AND--- {1}")
    public static boolean verifyEquals(Object value1, Object value2) {
        boolean result = value1.equals(value2);
        if (result == true) {
            LogUtils.info("Verify Equals: " + value1 + " = " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.pass("Verify Equals: " + value1 + " = " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " = " + value2);
        } else {
            LogUtils.info("Verify Equals: " + value1 + " != " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.fail("Verify Equals: " + value1 + " != " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " != " + value2);
            Assert.assertEquals(value1, value2, value1 + " != " + value2);
        }
        return result;
    }

    @Step("Verify Equals: {0} ---AND--- {1}")
    public static boolean verifyEquals(Object value1, Object value2, String message) {
        boolean result = value1.equals(value2);
        if (result == true) {
            LogUtils.info("Verify Equals: " + value1 + " = " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.pass("Verify Equals: " + value1 + " = " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " = " + value2);
        } else {
            LogUtils.info("Verify Equals: " + value1 + " != " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.fail("Verify Equals: " + value1 + " != " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " != " + value2);
            Assert.assertEquals(value1, value2, message);
        }
        return result;
    }

    @Step("Verify Contains: {0} ---AND--- {1}")
    public static boolean verifyContains(String value1, String value2) {
        boolean result = value1.contains(value2);
        if (result == true) {
            LogUtils.info("Verify Equals: " + value1 + " CONTAINS " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.pass("Verify Contains: " + value1 + " CONTAINS " + value2);
            }
            AllureManager.saveTextLog("Verify Contains: " + value1 + "CONTAINS" + value2);
        } else {
            LogUtils.info("Verify Contains: " + value1 + " NOT CONTAINS " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.fail("Verify Contains: " + value1 + " NOT CONTAINS " + value2);
            }
            AllureManager.saveTextLog("Verify Contains: " + value1 + " NOT CONTAINS " + value2);

            Assert.assertEquals(value1, value2, value1 + " NOT CONTAINS " + value2);
        }
        return result;
    }

    @Step("Verify Contains: {0} ---AND--- {1}")
    public static boolean verifyContains(String value1, String value2, String message) {
        boolean result = value1.contains(value2);
        if (result == true) {
            LogUtils.info("Verify Equals: " + value1 + " CONTAINS " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.pass("Verify Contains: " + value1 + " CONTAINS " + value2);
            }
            AllureManager.saveTextLog("Verify Contains: " + value1 + "CONTAINS" + value2);
        } else {
            LogUtils.info("Verify Contains: " + value1 + " NOT CONTAINS " + value2);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.fail("Verify Contains: " + value1 + " NOT CONTAINS " + value2);
            }
            AllureManager.saveTextLog("Verify Contains: " + value1 + " NOT CONTAINS " + value2);

            Assert.assertEquals(value1, value2, message);
        }
        return result;
    }

    @Step("Verify TRUE with condition: {0}")
    public static boolean verifyTrue(Boolean condition) {
        if (condition == true) {
            LogUtils.info("Verify TRUE: " + condition);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.pass("Verify TRUE: " + condition);
            }
            AllureManager.saveTextLog("Verify TRUE: " + condition);
        } else {
            LogUtils.info("Verify TRUE: " + condition);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.fail("Verify TRUE: " + condition);
            }
            AllureManager.saveTextLog("Verify TRUE: " + condition);

            Assert.assertTrue(condition, "The condition is FALSE.");
        }
        return condition;
    }

    @Step("Verify TRUE with condition: {0}")
    public static boolean verifyTrue(Boolean condition, String message) {
        if (condition == true) {
            LogUtils.info("Verify TRUE: " + condition);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.pass("Verify TRUE: " + condition);
            }
            AllureManager.saveTextLog("Verify TRUE: " + condition);
        } else {
            LogUtils.info("Verify TRUE: " + condition);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.fail("Verify TRUE: " + condition);
            }
            AllureManager.saveTextLog("Verify TRUE: " + condition);

            Assert.assertTrue(condition, message);
        }
        return condition;
    }

    public static boolean verifyElementText(By by, String text) {
        smartWait();
        waitForElementVisible(by);

        return getTextElement(by).trim().equals(text.trim());
    }

    @Step("Verify text of an element [Equals]")
    public static boolean verifyElementTextEquals(By by, String text, FailureHandling flowControl) {
        smartWait();

        waitForElementVisible(by);

        boolean result = getTextElement(by).trim().equals(text.trim());

        if (result == true) {
            LogUtils.info("Verify text of an element [Equals]: " + result);
        } else {
            LogUtils.warn("Verify text of an element [Equals]: " + result);
        }

        if (flowControl.equals(FailureHandling.STOP_ON_FAILURE)) {
            Assert.assertEquals(getTextElement(by).trim(), text.trim(), "The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");
        }
        if (flowControl.equals(FailureHandling.CONTINUE_ON_FAILURE)) {
            softAssert.assertEquals(getTextElement(by).trim(), text.trim(), "The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");
            if (result == false) {
                ExtentReportManager.fail("The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");
            }
        }
        if (flowControl.equals(FailureHandling.OPTIONAL)) {
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.warning("Verify text of an element [Equals] - " + result);
                ExtentReportManager.warning("The actual text is '" + getTextElement(by).trim() + "' not equals expected text '" + text.trim() + "'");
            }
            AllureManager.saveTextLog("Verify text of an element [Equals] - " + result + ". The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");
        }

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

        return getTextElement(by).trim().equals(text.trim());
    }

    @Step("Verify text of an element [Equals]")
    public static boolean verifyElementTextEquals(By by, String text) {
        smartWait();
        waitForElementVisible(by);

        boolean result = getTextElement(by).trim().equals(text.trim());

        if (result == true) {
            LogUtils.info("Verify text of an element [Equals]: " + result);
        } else {
            LogUtils.warn("Verify text of an element [Equals]: " + result);
        }

        Assert.assertEquals(getTextElement(by).trim(), text.trim(), "The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.warning("Verify text of an element [Equals] : " + result);
            ExtentReportManager.warning("The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");
        }
        AllureManager.saveTextLog("Verify text of an element [Equals] : " + result + ". The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

        return result;
    }

    @Step("Verify text of an element [Contains]")
    public static boolean verifyElementTextContains(By by, String text, FailureHandling flowControl) {
        smartWait();
        waitForElementVisible(by);

        boolean result = getTextElement(by).trim().contains(text.trim());

        if (result == true) {
            LogUtils.info("Verify text of an element [Contains]: " + result);
        } else {
            LogUtils.warn("Verify text of an element [Contains]: " + result);
        }

        if (flowControl.equals(FailureHandling.STOP_ON_FAILURE)) {
            Assert.assertTrue(result, "The actual text is " + getTextElement(by).trim() + " not contains " + text.trim());
        }
        if (flowControl.equals(FailureHandling.CONTINUE_ON_FAILURE)) {
            softAssert.assertTrue(result, "The actual text is " + getTextElement(by).trim() + " not contains " + text.trim());
        }
        if (flowControl.equals(FailureHandling.OPTIONAL)) {
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.warning("Verify text of an element [Contains] - " + result);
            }
            AllureManager.saveTextLog("Verify text of an element [Contains] - " + result);
        }

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

        return getTextElement(by).trim().contains(text.trim());
    }

    @Step("Verify text {1} of element [Contains] {0}")
    public static boolean verifyElementTextContains(By by, String text) {
        smartWait();
        waitForElementVisible(by);

        boolean result = getTextElement(by).trim().contains(text.trim());

        if (result == true) {
            LogUtils.info("Verify text of an element [Contains]: " + result);
        } else {
            LogUtils.warn("Verify text of an element [Contains]: " + result);
        }

        Assert.assertTrue(result, "The actual text is " + getTextElement(by).trim() + " not contains " + text.trim());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.info("Verify text of an element [Contains] : " + result);
        }
        AllureManager.saveTextLog("Verify text of an element [Contains] : " + result);

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

        return result;
    }

    @Step("Verify element Clickable {0}")
    public static boolean verifyElementClickable(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info("Verify element clickable " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element clickable " + by);
            }
            AllureManager.saveTextLog("Verify element clickable " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            Assert.fail("FAILED. Element not clickable " + by);
            return false;
        }
    }

    @Step("Verify element Clickable {0} with timeout {1} second")
    public static boolean verifyElementClickable(By by, int timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info("Verify element clickable " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element clickable " + by);
            }
            AllureManager.saveTextLog("Verify element clickable " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("FAILED. Element not clickable " + by);
            LogUtils.error(e.getMessage());
            Assert.fail("FAILED. Element not clickable " + by);
            return false;
        }
    }

    @Step("Verify element Clickable {0}")
    public static boolean verifyElementClickable(By by, int timeout, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info("Verify element clickable " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element clickable " + by);
            }
            AllureManager.saveTextLog("Verify element clickable " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error(message);
            LogUtils.error(e.getMessage());
            Assert.fail(message + "" + e.getMessage());
            return false;
        }
    }

    @Step("Verify element present {0}")
    public static boolean verifyElementPresent(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            LogUtils.info("The element does NOT present. " + e.getMessage());
            Assert.fail("The element does NOT present. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify element present {0} with timeout {1} second")
    public static boolean verifyElementPresent(By by, int timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            LogUtils.info("The element does NOT present. " + e.getMessage());
            Assert.fail("The element does NOT present. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify element present {0}")
    public static boolean verifyElementPresent(By by, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element does NOT present. " + e.getMessage());
                Assert.fail("The element does NOT present. " + e.getMessage());
            } else {
                LogUtils.error(message);
                Assert.fail(message);
            }

            return false;
        }
    }

    @Step("Verify element present {0} with timeout {1} second")
    public static boolean verifyElementPresent(By by, int timeout, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.info("Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element does NOT present. " + e.getMessage());
                Assert.fail("The element does NOT present. " + e.getMessage());
            } else {
                LogUtils.error(message);
                Assert.fail(message);
            }

            return false;
        }
    }

    @Step("Verify element NOT present {0}")
    public static boolean verifyElementNotPresent(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.error("The element presents. " + by);
            Assert.fail("The element presents. " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT present {0} with timeout {1} second")
    public static boolean verifyElementNotPresent(By by, int timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.error("Element is present " + by);
            Assert.fail("The element presents. " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT present {0}")
    public static boolean verifyElementNotPresent(By by, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element presents. " + by);
                Assert.fail("The element presents. " + by);
            } else {
                LogUtils.error(message);
                Assert.fail(message + " " + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT present {0} with timeout {1} second")
    public static boolean verifyElementNotPresent(By by, int timeout, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element presents. " + by);
                Assert.fail("The element presents. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element visible {0}")
    public static boolean isElementVisible(By by, long timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify element visible {0} with timeout {1} second")
    public static boolean verifyElementVisible(By by, long timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("The element is not visible. " + e.getMessage());
            Assert.fail("The element is NOT visible. " + by);
            return false;
        }
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element is not visible. " + by);
                Assert.fail("The element is NOT visible. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        }
    }

    @Step("Verify element visible {0} with timeout {1} second")
    public static boolean verifyElementVisible(By by, int timeout, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element is not visible. " + by);
                Assert.fail("The element is NOT visible. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        }
    }

    @Step("Verify element NOT visible {0}")
    public static boolean verifyElementNotVisible(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.error("FAILED. The element is visible " + by);
            Assert.fail("FAILED. The element is visible " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT visible {0} with timeout {1} second")
    public static boolean verifyElementNotVisible(By by, int timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.error("FAILED. The element is visible " + by);
            Assert.fail("FAILED. The element is visible " + by);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT visible {0}")
    public static boolean verifyElementNotVisible(By by, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("FAILED. The element is visible " + by);
                Assert.fail("FAILED. The element is visible " + by);
            } else {
                LogUtils.error(message + " " + by);
                Assert.fail(message + " " + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Verify element NOT visible {0} with timeout {1} second")
    public static boolean verifyElementNotVisible(By by, int timeout, String message) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (message.isEmpty() || message == null) {
                LogUtils.error("FAILED. The element is visible " + by);
                Assert.fail("FAILED. The element is visible " + by);
            } else {
                LogUtils.error(message + " " + by);
                Assert.fail(message + " " + by);
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(By by) {
        smartWait();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(By by) {
        smartWait();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(WebElement element) {
        smartWait();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        LogUtils.info("Scroll to element " + element);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(WebElement element) {
        smartWait();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        LogUtils.info("Scroll to element " + element);
    }

    @Step("Scroll to position X={0}, Y={1}")
    public static void scrollToPosition(int X, int Y) {
        smartWait();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
        LogUtils.info("Scroll to position X = " + X + " ; Y = " + Y);
    }

    @Step("Hover on element {0}")
    public static boolean hoverOnElement(By by) {
        smartWait();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            LogUtils.info("Hover on element " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Mouse hover on element {0}")
    public static boolean mouseHover(By by) {
        smartWait();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            LogUtils.info("Mouse hover on element " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Drag from element {0} to element {1}")
    public static boolean dragAndDrop(By fromElement, By toElement) {
        smartWait();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Step("Drag HTML5 from element {0} to element {1}")
    public static boolean dragAndDropHTML5(By fromElement, By toElement) {
        smartWait();

        try {
            Robot robot = new Robot();
            robot.mouseMove(0, 0);

            int X1 = getWebElement(fromElement).getLocation().getX() + (getWebElement(fromElement).getSize().getWidth() / 2);
            int Y1 = getWebElement(fromElement).getLocation().getY() + (getWebElement(fromElement).getSize().getHeight() / 2);
            System.out.println(X1 + " , " + Y1);

            int X2 = getWebElement(toElement).getLocation().getX() + (getWebElement(toElement).getSize().getWidth() / 2);
            int Y2 = getWebElement(toElement).getLocation().getY() + (getWebElement(toElement).getSize().getHeight() / 2);
            System.out.println(X2 + " , " + Y2);

            //Chổ này lấy toạ độ hiện tại cộng thêm 120px là phần header của browser (1920x1080 current window)
            //Header: chrome is being controlled by automated test software
            sleep(1);
            robot.mouseMove(X1, Y1 + 120);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

            sleep(1);
            robot.mouseMove(X2, Y2 + 120);
            sleep(1);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Step("Drag from element {0} to X={1}, Y={2}")
    public static boolean dragAndDropToOffset(By fromElement, int X, int Y) {
        smartWait();

        try {
            Robot robot = new Robot();
            robot.mouseMove(0, 0);
            int X1 = getWebElement(fromElement).getLocation().getX() + (getWebElement(fromElement).getSize().getWidth() / 2);
            int Y1 = getWebElement(fromElement).getLocation().getY() + (getWebElement(fromElement).getSize().getHeight() / 2);
            System.out.println(X1 + " , " + Y1);
            sleep(1);

            //Chổ này lấy toạ độ hiện tại cộng thêm 120px là phần header của browser (1920x1080 current window)
            //Header: chrome is being controlled by automated test software
            robot.mouseMove(X1, Y1 + 120);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

            sleep(1);
            robot.mouseMove(X, Y + 120);
            sleep(1);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Step("Move to element {0}")
    public static boolean moveToElement(By toElement) {
        smartWait();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Step("Move to offset X={0}, Y={1}")
    public static boolean moveToOffset(int X, int Y) {
        smartWait();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Step("Press ENTER keyboard")
    public static boolean pressENTER() {
        smartWait();

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Press ESC keyboard")
    public static boolean pressESC() {
        smartWait();

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Press F5 keyboard")
    public static boolean pressF5() {
        smartWait();

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F5);
            robot.keyRelease(KeyEvent.VK_F5);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Press F11 keyboard")
    public static boolean pressF11() {
        smartWait();

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Reload page")
    public static void reloadPage() {
        smartWait();

        DriverManager.getDriver().navigate().refresh();
        waitForPageLoaded();
        LogUtils.info("Reloaded page " + DriverManager.getDriver().getCurrentUrl());
    }


    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    @Step("Highlight on element")
    public static WebElement highLightElement(By by) {
        smartWait();

        // draw a border around the found element
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", waitForElementVisible(by));
            sleep(1);
            LogUtils.info("Highlight on element " + by);
        }
        return getWebElement(by);
    }

    /**
     * Open website with URL
     *
     * @param URL
     */
    @Step("Open website with URL {0}")
    public static void openURL(String URL) {
        sleep(WAIT_SLEEP_STEP);

        DriverManager.getDriver().get(URL);
        waitForPageLoaded();

        LogUtils.info("Open URL: " + URL);

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Open URL: " + URL);
        }
        AllureManager.saveTextLog("Open URL: " + URL);

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Open website with URL
     *
     * @param URL
     */
    @Step("Open website with URL {0}")
    public static void getURL(String URL) {
        sleep(WAIT_SLEEP_STEP);

        DriverManager.getDriver().get(URL);
        waitForPageLoaded();

        LogUtils.info("Open URL: " + URL);

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Open URL: " + URL);
        }
        AllureManager.saveTextLog("Open URL: " + URL);

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Open website with navigate to URL
     *
     * @param URL
     */
    @Step("Navigate to URL {0}")
    public static void navigateToUrl(String URL) {
        DriverManager.getDriver().navigate().to(URL);
        waitForPageLoaded();

        LogUtils.info("Navigate to URL: " + URL);

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Navigate to URL: " + URL);
        }
        AllureManager.saveTextLog("Navigate to URL: " + URL);

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Điền giá trị vào ô Text
     *
     * @param by    an element of object type By
     * @param value giá trị cần điền vào ô text
     */
    @Step("Set text on textbox")
    public static void setText(By by, String value) {
        waitForElementVisible(by).sendKeys(value);
        LogUtils.info("Set text " + value + " on " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Set text " + value + " on " + by.toString());
        }
        AllureManager.saveTextLog("Set text " + value + " on " + by.toString());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Điền giá trị vào ô Text và nhập Keys
     *
     * @param by    an element of object type By
     * @param value giá trị cần điền vào ô text
     * @param keys  key ở bàn phím cần nhấn
     */
    @Step("Set text on textbox and press key")
    public static void setText(By by, String value, Keys keys) {
        waitForElementVisible(by).sendKeys(value, keys);
        LogUtils.info("Set text " + value + " on " + by + " and press key " + keys.name());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Set text " + value + " on " + by + " and press key " + keys.name());
        }
        AllureManager.saveTextLog("Set text " + value + " on " + by + " and press key " + keys.name());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Thao tác keys dưới bàn phím lên element by
     *
     * @param by   an element of object type By
     * @param keys key ở bàn phím cần nhấn
     */
    @Step("Set text on textbox and press key")
    public static void sendKeys(By by, Keys keys) {
        waitForElementVisible(by).sendKeys(keys);
        LogUtils.info("Press key " + keys.name() + " on element " + by);

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Press key " + keys.name() + " on element " + by);
        }
        AllureManager.saveTextLog("Press key " + keys.name() + " on element " + by);

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
    }

    /**
     * Thao tác keys dưới bàn phím lên element by
     *
     * @paramby   an element of object type By
     * @param keys key ở bàn phím cần nhấn
     */
    @Step("Set text on textbox and press key")
    public static void sendKeys(Keys keys) {
        Actions actions = new Actions(DriverManager.getDriver());
        actions.sendKeys(keys);
        LogUtils.info("Press key " + keys.name() + " on keyboard");

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Press key " + keys.name() + " on keyboard");
        }
        AllureManager.saveTextLog("Press key " + keys.name() + " on keyboard");

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
    }

    /**
     * Xóa giá trị trong ô Text
     *
     * @param by an element of object type By
     */
    @Step("Clear value in textbox")
    public static void clearText(By by) {
        waitForElementVisible(by).clear();
        LogUtils.info("Clear value in textbox " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clear value in textbox " + by.toString());
        }
        AllureManager.saveTextLog("Clear value in textbox");
        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**

     Enter a value into a Text field.
     @param by an element of object type By
     @param value the value to enter into the Text field
     */
    @Step("Clear and Fill text on text box")
    public static void clearAndFillText(By by, String value) {
        waitForElementVisible(by).clear();
        waitForElementVisible(by).sendKeys(value);
        LogUtils.info("Clear and Fill " + value + " on " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clear and Fill " + value + " on " + by.toString());
        }
        AllureManager.saveTextLog("Clear and Fill " + value + " on " + by.toString());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Click on element
     *
     * @param by an element of object type By
     */
    @Step("Click on the element {0}")
    public static void clickElement(By by) {
        try {

            waitForElementVisible(by).click();
            LogUtils.info("Clicked on the element " + by.toString());

            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.pass("Clicked on the element " + by.toString());
            }
        } catch (ElementClickInterceptedException e) {
            waitForElementClickable(by).click();
        }
        AllureManager.saveTextLog("Clicked on the element " + by.toString());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
    }

    /**
     * Click on element with timeout
     *
     * @param by an element of object type By
     */
    @Step("Click on the element {0} with timeout {1}s")
    public static void clickElement(By by, int timeout) {
        waitForElementVisible(by, timeout).click();
        LogUtils.info("Clicked on the element " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clicked on the element " + by.toString());
        }
        AllureManager.saveTextLog("Clicked on the element " + by.toString());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**

     Click on an Element on the web using JavaScript (performing an implicit click without being obstructed).
     @param by an element of object type By
     */
    @Step("Click on the element by Javascript {0}")
    public static void clickElementWithJs(By by) {
        waitForElementPresent(by);
        //Scroll to element với Javascript Executor`
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        //Click with JS
        js.executeScript("arguments[0].click();", getWebElement(by));

        LogUtils.info("Click on element with JS: " + by);
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Click on element with JS: " + by);
        }
        AllureManager.saveTextLog("Click on element with JS: " + by);
        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Click on the link on website with text
     *
     * @param linkText is the visible text of a link
     */
    @Step("Click on the link text {0}")
    public static void clickLinkText(String linkText) {
        smartWait();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
        WebElement elementWaited = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
        elementWaited.click();

        LogUtils.info("Click on link text " + linkText);
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Click on link text " + linkText);
        }
        AllureManager.saveTextLog("Click on link text " + linkText);
        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());
    }

    /**

     Right-click on an Element object on the web.
     @param by an element of object type By
     */
    @Step("Right click on element {0}")
    public static void rightClickElement(By by) {
        Actions action = new Actions(DriverManager.getDriver());
        action.contextClick(waitForElementVisible(by)).build().perform();
        LogUtils.info("Right click on element " + by);
        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Right click on element " + by);
        }
        AllureManager.saveTextLog("Right click on element " + by);
        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Get text of an element
     *
     * @param by an element of object type By
     * @return text of a element
     */
    @Step("Get text of element {0}")
    public static String getTextElement(By by) {
        smartWait();
        AllureManager.saveTextLog("Get text of element " + by.toString());
        AllureManager.saveTextLog("==> The Text is: " + waitForElementVisible(by).getText());
        return waitForElementVisible(by).getText().trim();
    }

    /**

     Get the value from an attribute of an element.
     @param by an element of object type By
     @param attributeName the attribute name
     @return the value of the element's attribute
     */
    @Step("Get attribute {1} of element {0}")
    public static String getAttributeElement(By by, String attributeName) {
        smartWait();
        return waitForElementVisible(by).getAttribute(attributeName);
    }

    public static void waitForInvisibilityOfItem( By by, int time) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(time));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            LogUtils.info("Element"+by.toString()+"Has been Disappeared");
        } catch (ElementClickInterceptedException exception) {

        }
    }

    /**

     Get the CSS value of an element.
     @param by an element of object type By
     @param cssName the CSS attribute name
     @return the value of the CSS attribute
     */
    @Step("Get CSS value {1} of element {0}")
    public static String getCssValueElement(By by, String cssName) {
        smartWait();
        return waitForElementVisible(by).getCssValue(cssName);
    }

    @Step("Get Size of element {0}")
    public static Dimension getSizeElement(By by) {
        smartWait();
        return waitForElementVisible(by).getSize();
    }

    @Step("Get Location of element {0}")
    public static Point getLocationElement(By by) {
        smartWait();
        return waitForElementVisible(by).getLocation();
    }

    @Step("Get Tag Name of element {0}")
    public static String getTagNameElement(By by) {
        smartWait();
        return waitForElementVisible(by).getTagName();
    }

    //Handle Table

    /**
     * Check the values of each column in a table when searching for a condition EQUALS.
     *
     * @param column the position of the column
     * @param value the value to compare
     */
    @Step("Check data by EQUALS type after searching on the Table by Column.")
    public static void checkEqualsValueOnTableByColumn(int column, String value) {
        smartWait();
        sleep(1);
        List<WebElement> totalRows = getWebElements(By.xpath("//tbody/tr"));
        LogUtils.info("Number of results for keywords (" + value + "): " + totalRows.size());

        if (totalRows.size() < 1) {
            LogUtils.info("Not found value: " + value);
        } else {
            for (int i = 1; i <= totalRows.size(); i++) {
                boolean res = false;
                WebElement title = waitForElementVisible(By.xpath("//tbody/tr[" + i + "]/td[" + column + "]"));
                res = title.getText().toUpperCase().equals(value.toUpperCase());
                LogUtils.info("Row " + i + ": " + res + " - " + title.getText());
                Assert.assertTrue(res, "Row " + i + " (" + title.getText() + ")" + " equals no value: " + value);
            }
        }
    }

    /**
     * Check the values of each column in a table when searching for a condition CONTAINS.
     *
     * @param column the position of the column
     * @param value the value to compare
     */
    @Step("Check data by CONTAINS type after searching on the Table by Column.")
    public static void checkContainsValueOnTableByColumn(int column, String value) {
        smartWait();
        sleep(1);
        List<WebElement> totalRows = getWebElements(By.xpath("//tbody/tr"));
        LogUtils.info("Number of results for keywords (" + value + "): " + totalRows.size());

        if (totalRows.size() < 1) {
            LogUtils.info("Not found value: " + value);
        } else {
            for (int i = 1; i <= totalRows.size(); i++) {
                boolean res = false;
                WebElement title = waitForElementVisible(By.xpath("//tbody/tr[" + i + "]/td[" + column + "]"));
                res = title.getText().toUpperCase().contains(value.toUpperCase());
                LogUtils.info("Row " + i + ": " + res + " - " + title.getText());
                Assert.assertTrue(res, "Row " + i + " (" + title.getText() + ")" + " contains no value: " + value);
            }
        }
    }

    /**
     * Check the values of each column in a table when searching for a condition CONTAINING with a custom xpath.
     *
     * @param column the position of the column
     * @param value the value to compare
     * @param xpathToTRtagname the xpath value up to the TR tag
     */
    @Step("Check data by CONTAINS type after searching on the Table by Column.")
    public static void checkContainsValueOnTableByColumn(int column, String value, String xpathToTRtagname) {
        smartWait();

        //xpathToTRtagname is locator from table to "tr" tagname of data section: //tbody/tr, //div[@id='example_wrapper']//tbody/tr, ...
        List<WebElement> totalRows = DriverManager.getDriver().findElements(By.xpath(xpathToTRtagname));
        sleep(1);
        LogUtils.info("Number of results for keywords (" + value + "): " + totalRows.size());

        if (totalRows.size() < 1) {
            LogUtils.info("Not found value: " + value);
        } else {
            for (int i = 1; i <= totalRows.size(); i++) {
                boolean res = false;
                WebElement title = waitForElementVisible(By.xpath(xpathToTRtagname + "[" + i + "]/td[" + column + "]"));
                res = title.getText().toUpperCase().contains(value.toUpperCase());
                LogUtils.info("Row " + i + ": " + res + " - " + title.getText());
                Assert.assertTrue(res, "Row " + i + " (" + title.getText() + ")" + " contains no value " + value);
            }
        }
    }

    /**
     * Get the values of a column from a table.
     *
     * @param column the position of the column
     * @return an array or list of values in a column
     */
    public static ArrayList getValueTableByColumn(int column) {
        smartWait();

        List<WebElement> totalRows = DriverManager.getDriver().findElements(By.xpath("//tbody/tr"));
        sleep(1);
        LogUtils.info("Number of results for column (" + column + "): " + totalRows.size()); //Không thích ghi log thì xóa nhen

        ArrayList arrayList = new ArrayList<String>();

        if (totalRows.size() < 1) {
            LogUtils.info("Not found value !!");
        } else {
            for (int i = 1; i <= totalRows.size(); i++) {
                boolean res = false;
                WebElement title = DriverManager.getDriver().findElement(By.xpath("//tbody/tr[" + i + "]/td[" + column + "]"));
                arrayList.add(title.getText());
                LogUtils.info("Row " + i + ":" + title.getText()); //Không thích ghi log thì xóa nhen
            }
        }

        return arrayList;
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                 | org.openqa.selenium.StaleElementReferenceException
                 | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    public static void waitForElementToBeGone(By by, int timeout) throws InterruptedException {
        try {
            WebElement element = DriverManager.getDriver().findElement(by);
            if (isElementDisplayed(element)) {
                new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout)).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
                LogUtils.info("Element has been disappear: "+by);
            }else {
                LogUtils.info("Element is still displayed "+by);
            }
        } catch (NoSuchElementException e) {
            tryCheck++;
            if(tryCheck==1){
                Thread.sleep(1000);
                LogUtils.info("we are into NoSuchElementException");
                waitForElementToBeGone(by, timeout);
            }

        } catch (StaleElementReferenceException e) {
            LogUtils.info("we are into StaleElementReferenceException ");
        }
        catch (WebDriverException e) {
            LogUtils.info("we are into WebDriverException ");
        }


    }
    //Wait Element

    /**
     * Wait for the element to be ready for interaction within a specified time limit.
     *
     * @param by      an element of object type By
     * @param timeOut the maximum waiting time
     * @return a WebElement object that is ready for interaction
     */
    public static void waitSpinner(By by, long timeOut) {
        smartWait();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(100));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.error(" Element Visible. " + by.toString());

        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
        }

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            LogUtils.info("Element"+by.toString()+"Has been Disappeared");
        } catch (ElementClickInterceptedException exception) {
            LogUtils.error("Element"+by.toString()+"Has Not been Disappeared");
        }


    }
    public static WebElement waitForElementVisible(By by, long timeOut) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(5000));

            boolean check = verifyElementVisible(by, timeOut);
            if (check == true) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElementToTop(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    /**
     * Chờ đợi element sẵn sàng hiển thị để thao tác
     *
     * @param by an element of object type By
     * @return một đối tượng WebElement đã sẵn sàng để thao tác
     */
    public static WebElement waitForElementVisible(By by) {
        smartWait();
        waitForElementPresent(by);

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            boolean check = isElementVisible(by, 1);
            if (check == true) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElementToBottom(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error ) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    /**
     * Chờ đợi element sẵn sàng hiển thị để CLICK theo thời gian tuỳ ý
     *
     * @param by      an element of object type By
     * @param timeOut thời gian chờ tối đa
     * @return một đối tượng WebElement đã sẵn sàng để CLICK
     */
    public static WebElement waitForElementClickable(By by, long timeOut) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            return wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
        }
        return null;
    }

    /**
     * Chờ đợi element sẵn sàng hiển thị để CLICK
     *
     * @param by an element of object type By
     * @return một đối tượng WebElement đã sẵn sàng để CLICK
     */
    public static WebElement waitForElementClickable(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            return wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
        }
        return null;
    }

    /**

     Wait for an element to exist in the DOM within a specified time limit.
     @param by an element of object type By
     @param timeOut the maximum waiting time
     @return a WebElement object that exists
     */
    public static WebElement waitForElementPresent(By by, long timeOut) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element to exist. " + by.toString());
            Assert.fail("Timeout waiting for the element to exist. " + by.toString());
        }

        return null;
    }

    /**
     * Chờ đợi element sẵn sàng tồn tại trong DOM theo thời gian tuỳ ý
     *
     * @param by an element of object type By
     * @return một đối tượng WebElement đã tồn tại
     */
    public static WebElement waitForElementPresent(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
        return null;
    }

    /**
     * Chờ đợi thuộc tính của một element tồn tại
     *
     * @param by        an element of object type By
     * @param attribute tên thuộc tính
     * @return true/false
     */
    public static boolean waitForElementHasAttribute(By by, String attribute) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            return wait.until(ExpectedConditions.attributeToBeNotEmpty(waitForElementPresent(by), attribute));
        } catch (Throwable error) {
            LogUtils.error("Timeout for element " + by.toString() + " to exist attribute: " + attribute);
            Assert.fail("Timeout for element " + by.toString() + " to exist attribute: " + attribute);
        }
        return false;
    }


    /**

     Check if the value of an attribute of an element is correct or not.
     @param by an element of object type By
     @param attribute the attribute name
     @param value the value to check
     @return true/false
     */
    @Step("Verify element {0} with attribute {1} has value is {2}")
    public static boolean verifyElementAttributeValue(By by, String attribute, String value) {
        smartWait();

        waitForElementVisible(by);
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
            return true;
        } catch (Throwable error) {
            LogUtils.error("Object: " + by.toString() + ". Not found value: " + value + " with attribute type: " + attribute + ". Actual get Attribute value is: " + getAttributeElement(by, attribute));
            Assert.fail("Object: " + by.toString() + ". Not found value: " + value + " with attribute type: " + attribute + ". Actual get Attribute value is: " + getAttributeElement(by, attribute));
            return false;
        }
    }


    /**

     Wait for the attribute of an element to exist with a custom timeout.
     @param by an element of object type By
     @param attribute the attribute name
     @param timeOut the maximum waiting time
     @return true/false
     */
    @Step("Verify element {0} has attribute {1} with timeout {2} second")
    public static boolean verifyElementHasAttribute(By by, String attribute, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.attributeToBeNotEmpty(waitForElementPresent(by), attribute));
            return true;
        } catch (Throwable error) {
            LogUtils.error("Not found Attribute " + attribute + " of element " + by.toString());
            Assert.fail("Not found Attribute " + attribute + " of element " + by.toString());
            return false;
        }
    }

    // Wait Page loaded

    /**
     * Chờ đợi trang tải xong (Javascript) với thời gian mặc định từ config
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_PAGE_LOADED), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + WAIT_PAGE_LOADED + "s)");
            }
        }
    }


    /**

     Wait for jQuery to finish loading with the default timeout from the configuration.
     */
    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_PAGE_LOADED), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            LogUtils.info("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + WAIT_PAGE_LOADED + "s)");
            }
        }
    }

    //Wait for Angular Load

    /**

     Wait for Angular to finish loading with the default timeout from the configuration.
     */
    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_PAGE_LOADED), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());
        };

        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            LogUtils.warn("Angular is NOT Ready!");
            //Wait for Angular to load
            try {
                //Wait for jQuery to load
                wait.until(angularLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for Angular load. (" + WAIT_PAGE_LOADED + "s)");
            }
        }

    }

}