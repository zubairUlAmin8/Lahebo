package web_elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import path_repo.SignInPR;

public class SingInPageElements {
    @FindBy(css = SignInPR.userName)
    public WebElement userName;
    @FindBy(css = SignInPR.password)
    public WebElement password;
    @FindBy(css = SignInPR.signInBtn)
    public WebElement signInBtn;


    WebDriver driver;

    public SingInPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
