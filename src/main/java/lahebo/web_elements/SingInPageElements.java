package lahebo.web_elements;

import lahebo.path_repo.SignInPR;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingInPageElements {
    @FindBy(css = SignInPR.userName)
    public WebElement userName;
    @FindBy(css = SignInPR.password)
    public WebElement password;
    @FindBy(css = SignInPR.signInBtn)
    public WebElement signInBtn;
    @FindBy(css = SignInPR.errorAlert)
    public WebElement errorAlert;
    @FindBy(css = SignInPR.validationErrorMsg)
    public WebElement validationErrorMsg;

    @FindBy(css = SignInPR.forgetPasswordLinkTxt)
    public WebElement forgetPasswordLinkTxt;


    WebDriver driver;

    public SingInPageElements(WebDriver driver) {
        this.driver = driver;
        System.out.println("sign in elements repo Constructor"+driver);
        PageFactory.initElements(driver, this);

    }
}
