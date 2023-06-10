package lahebo.web_pages;

import Utils.waits;
import lahebo.web_elements.CardPaymentElements;
import lahebo.web_elements.DashboardElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CardPaymentPage {
    CardPaymentElements cardPaymentElements;
    WebDriver driver;
    public CardPaymentPage(WebDriver driver) {
        this.driver = driver;
        cardPaymentElements = new CardPaymentElements(driver);
    }

    public void fillCardDetails() {
        waits.waitForElements(driver, cardPaymentElements.cardCVCInputField, 10);
        cardPaymentElements.cardNumberInputField.sendKeys("4242 4242 4242 4242");
        cardPaymentElements.cardExpiryInputField.sendKeys("05 / 25");
        cardPaymentElements.cardCVCInputField.sendKeys("123");
        cardPaymentElements.cardOwnerNameInputField.sendKeys("Zubair Ul Amin");
        Select countryList = new Select(cardPaymentElements.billingCountryList);
        countryList.selectByValue("PK");
        cardPaymentElements.subscribeBtn.click();
    }
}
