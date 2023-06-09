package lahebo.web_pages;

import Utils.waits;
import lahebo.web_elements.ForgetPasswordElements;
import org.openqa.selenium.WebDriver;

public class ForgetPasswordPage {
    ForgetPasswordElements forgetPasswordElements;
    WebDriver driver;
//    SingInPageElements singInPageElements = new SingInPageElements(driver);

    public ForgetPasswordPage(WebDriver driver) {
        this.driver = driver;
        forgetPasswordElements = new ForgetPasswordElements(driver);

    }

    public void submitRequest(String userName) throws InterruptedException {
        waits.waitForElements(driver,forgetPasswordElements.userName, 10);
        Thread.sleep(1000);
        forgetPasswordElements.userName.sendKeys(userName);
        forgetPasswordElements.submitBtn.click();
    }
}


