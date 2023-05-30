package web_pages;

import Utils.DriverSetup;
import org.openqa.selenium.WebDriver;
public class BasePage {
    static protected WebDriver driver;
    int a=1;
    public WebDriver startDriver(){

        driver= DriverSetup.initDriver();

        return this.driver;

    }
    public void loadPage(WebDriver driver, String base_url){
        driver.get(base_url);
    }
}
