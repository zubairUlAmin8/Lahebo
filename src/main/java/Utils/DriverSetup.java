package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class DriverSetup {
     private  static WebDriver driver;
    private DriverSetup() {

    }
    public static WebDriver initDriver()
    {
        if(driver == null || driver.toString().contains("(null)"))
        {

            WebDriverManager.chromedriver().setup();
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(ops);
//            driver = new ChromeDriver();
            driver.manage().window().maximize();

        }
        return driver;
}
    public static WebDriver getDriver() {
        return driver;
    }
}
