package lahebo.web_elements;

import lahebo.path_repo.SignUpPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPageElements {
    @FindBy(css = SignUpPR.firstName)
    public WebElement firstName;

    @FindBy(css = SignUpPR.LastName)
    public WebElement LastName;
    @FindBy(css = SignUpPR.orgName)
    public WebElement orgName;
    @FindBy(css = SignUpPR.email)
    public WebElement email;
    @FindBy(css = SignUpPR.userName)
    public WebElement userName;
    @FindBy(css = SignUpPR.phoneNumber)
    public WebElement phoneNumber;
    @FindBy(css = SignUpPR.password)
    public WebElement password;
    @FindBy(css = SignUpPR.confirmPassword)
    public WebElement confirmPassword;
    @FindBy(css = SignUpPR.singUpBtn)
    public WebElement singUpBtn;
    @FindBy(css = SignUpPR.valueRequired)
    public WebElement valueRequired;
   @FindBy(css = SignUpPR.popUpError)
    public WebElement popUpError;



    WebDriver driver;

    public SignUpPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
