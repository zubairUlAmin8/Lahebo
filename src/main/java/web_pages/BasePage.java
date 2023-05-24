package web_pages;

import Utils.DriverSetup;
import org.openqa.selenium.WebDriver;
public class BasePage {
    static protected WebDriver driver;
    int a=1;
    public WebDriver startDriver(){
        System.out.println("Base Page startDriver: "+a);
        ++a;
        driver= DriverSetup.initDriver();
//        if (this.driver == null) {
//            System.out.println("Driver is null in pagepage");
//        } else {
//            System.out.println("Driver is start and value is assigned");
//            System.out.println(driver.getPageSource());
//        }
        return this.driver;

    }
//    public WebDriver getDriver(){
//
//        if(driver!=null){
//
//            System.out.println("Base Page getDriver: "+a);
//            ++a;
//        }
//        return driver;
//    }

    public void loadPage(WebDriver driver, String base_url){
        driver.get(base_url);
    }
}
