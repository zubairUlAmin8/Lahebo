package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        if (driver == null) {
            System.out.println("Driver is null in wait for elements");
        } else {
            System.out.println("driver is not null for waits");
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForTextToBePresentInElement(WebDriver driver, WebElement element, String value, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBePresentInElement(element, value));
    }

}
