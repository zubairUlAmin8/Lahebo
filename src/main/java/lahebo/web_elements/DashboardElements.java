package lahebo.web_elements;

import lahebo.path_repo.DashboardPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardElements {
    @FindBy(css = DashboardPR.profileIcon)
    public WebElement profileIcon;


    WebDriver driver;

    public DashboardElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
