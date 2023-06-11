package Utils;

import driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class waits {
    static WebDriverWait wait;

    public static void sendKeys(WebDriver driver, WebElement element, String value, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    public static void clickButton(WebDriver driver, WebElement element, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public static void waitForElements(WebDriver driver, WebElement element, int timeout) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException exception) {
            LogUtils.error(exception.getMessage());
        } catch ( StaleElementReferenceException staleElementReferenceException) {
            driver.navigate().refresh();
            LogUtils.error(staleElementReferenceException.getMessage());
        }

    }

    public static void waitForInvisibilityOfItem(WebDriver driver, String path, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(path)));
    }
    public static void waitForVisibilityOfItem(WebDriver driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException exception) {
            LogUtils.info(exception.getMessage());
        }

    }
    public static void waitForTextToBePresentInElement(WebDriver driver, WebElement element, String value, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBePresentInElement(element, value));
    }


}
