package Utils;

import keywords.WebUI;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class utility {
    public static String getValue (String key) throws IOException {
        Properties prop= new Properties();
        FileReader reader= new FileReader("src/test/resources/config/config.properties");
        prop.load(reader);

        String value=prop.getProperty(key);
        return value;
    }
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
    public static void handleZoomInZoomOut(int size) {
        //driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.ADD));
        //driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
        WebUI.getJsExecutor().executeScript("document.body.style.zoom = '"+size+"%';");
//        WebUI.sleep(1);
    }
    public static int extractIntegerFromString(String input) {
        String regex = "\\d+"; // Regular expression to match one or more digits
        String extractedNumber = input.replaceAll("[^\\d]", ""); // Remove non-digit characters

        int integerValue = 0;
        try {
            integerValue = Integer.parseInt(extractedNumber);
        } catch (NumberFormatException e) {
            // Handle the case where no integer value is found in the string
            System.out.println("No integer value found in the string.");
        }

        return integerValue;
    }
}
