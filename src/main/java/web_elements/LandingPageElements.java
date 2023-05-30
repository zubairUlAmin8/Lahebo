package web_elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import path_repo.LandingPagePR;

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
