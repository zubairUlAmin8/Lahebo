package LoginPageElm;

import org.openqa.selenium.By;

public interface loginpage {
    public static By userName = By.cssSelector("input[type=\"text\"]");
    public static By password = By.cssSelector("input[type=\"password\"]");
    public static By submitButton = By.cssSelector("input[type=\"submit\"]");
}
