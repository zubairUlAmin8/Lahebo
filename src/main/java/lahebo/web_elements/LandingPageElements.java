package lahebo.web_elements;

import lahebo.path_repo.LandingPagePR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPageElements {
    @FindBy(css = LandingPagePR.LagislationLibraryTab)
    public WebElement LagislationLibraryTab;
    @FindBy(css = LandingPagePR.loaderMain)
    public WebElement loaderMain;


    WebDriver driver;

    public LandingPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
