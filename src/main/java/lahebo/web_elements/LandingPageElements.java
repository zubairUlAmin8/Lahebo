package lahebo.web_elements;

import lahebo.path_repo.LandingPagePR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPageElements {
    @FindBy(css = LandingPagePR.LagislationLibraryTab)
    public WebElement LagislationLibraryTab;
    @FindBy(css = LandingPagePR.loaderMain)
    public WebElement loaderMain;
    @FindBy(css = LandingPagePR.profileIcon)
    public WebElement profileIcon;
    @FindBy(css = LandingPagePR.signOutBtn)
    public WebElement signOutBtn;
    @FindBy(css = LandingPagePR.organizationSettingTab)
    public WebElement organizationSettingTab;
    @FindBy(css = LandingPagePR.legislationLibraryTab)
    public WebElement legislationLibraryTab;
    @FindBy(css = LandingPagePR.riskRegisterTab)
    public WebElement riskRegisterTab;
    @FindBy(css = LandingPagePR.actionCenterTab)
    public WebElement actionCenterTab;
    @FindBy(css = LandingPagePR.reportTab)
    public WebElement reportTab;
    @FindBy(css = LandingPagePR.organizationDetailsST)
    public WebElement organizationDetailsST;
    @FindBy(css = LandingPagePR.departmentsST)
    public WebElement departmentsST;
    @FindBy(css = LandingPagePR.locationST)
    public WebElement locationST;
    @FindBy(css = LandingPagePR.jobFunctionST)
    public WebElement jobFunctionST;
    @FindBy(css = LandingPagePR.userSetupST)
    public WebElement userSetupST;
    @FindBy(css = LandingPagePR.manageSubscriptionST)
    public WebElement manageSubscriptionST;
    @FindBy(css = LandingPagePR.riskMatrixST)
    public WebElement riskMatrixST;



    WebDriver driver;

    public LandingPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
