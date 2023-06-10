package lahebo.web_elements;

import lahebo.path_repo.CardPaymentPR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardPaymentElements {
    @FindBy(css = CardPaymentPR.cardNumberInputField)
    public WebElement cardNumberInputField;

    @FindBy(css = CardPaymentPR.cardExpiryInputField)
    public WebElement cardExpiryInputField;
    @FindBy(css = CardPaymentPR.cardCVCInputField)
    public WebElement cardCVCInputField;
    @FindBy(css = CardPaymentPR.cardOwnerNameInputField)
    public WebElement cardOwnerNameInputField;
    @FindBy(css = CardPaymentPR.billingCountryList)
    public WebElement billingCountryList;
    @FindBy(css = CardPaymentPR.subscribeBtn)
    public WebElement subscribeBtn;



    WebDriver driver;

    public CardPaymentElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
