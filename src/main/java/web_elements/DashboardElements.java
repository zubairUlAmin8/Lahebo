package web_elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import path_repo.DashboardPR;

public class DashboardElements {
    @FindBy(css = DashboardPR.profileIcon)
    public WebElement profileIcon;


    WebDriver driver;

    public DashboardElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
