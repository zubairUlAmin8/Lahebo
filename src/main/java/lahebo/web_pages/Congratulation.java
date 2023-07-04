package lahebo.web_pages;

import Utils.LogUtils;
import Utils.waits;
import lahebo.web_elements.CongratulationPageElements;
import lahebo.web_elements.DashboardElements;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;


public class Congratulation extends BasePage{
    CongratulationPageElements congratulationPageElements;
    WebDriver driver;
    public Congratulation(WebDriver driver) {
        this.driver = driver;
        congratulationPageElements = new CongratulationPageElements(driver);
    }
    public boolean verifyCongratulation() {
        try {
            waits.waitForElements(driver, congratulationPageElements.continueBtn, 500);
            Thread.sleep(5000);
//            congratulationPageElements.continueBtn.click();
            return true;
        } catch (TimeoutException exception) {
            LogUtils.error(exception.getMessage());
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
