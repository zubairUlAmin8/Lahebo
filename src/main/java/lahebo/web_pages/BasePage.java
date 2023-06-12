package lahebo.web_pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
public class BasePage {
    public void loadPage(WebDriver driver, String base_url){
        driver.get(base_url);
    }
}
