package lahebo.web_elements;

import lahebo.path_repo.ForgetPasswordPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgetPasswordElements {
    @FindBy(css = ForgetPasswordPR.userName)
    public WebElement userName;
    @FindBy(css = ForgetPasswordPR.submitBtn)
    public WebElement submitBtn;

    WebDriver driver;

    public ForgetPasswordElements(WebDriver driver) {
        this.driver = driver;
        System.out.println("sign in elements repo Constructor"+driver);
        PageFactory.initElements(driver, this);

    }
}
