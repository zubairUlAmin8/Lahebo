package lahebo.web_pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
public class BasePage {

    public static WebDriver driver;



    public static WebDriver getDriver(){
        return DriverManager.getDriver();
    }

    public static void setBrowser(WebDriver mDriver) {
        if (driver==null) {
            driver = mDriver;
        }
    }
    public void loadPage(WebDriver driver, String base_url){
        driver.get(base_url);
    }
}
