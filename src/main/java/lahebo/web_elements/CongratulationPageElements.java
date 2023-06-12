package lahebo.web_elements;

import lahebo.path_repo.CongratulationPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CongratulationPageElements {
    @FindBy(css = CongratulationPR.continueBtn)
    public WebElement continueBtn;


    WebDriver driver;

    public CongratulationPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
