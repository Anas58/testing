package GovernmentalAndSubGovermentalRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SupervisorPage {

    WebDriver driver;

    private By user = By.id("logonuidfield");

    private By password = By.id("logonpassfield");

    private By loginButton = By.name("uidPasswordLogon");

    private By inboxButton = By.xpath("//div[@id = '__content3-value-scr']");

    private By filterButton = By.id("filterBtn-img");

    private By clearButton = By.id("bRefresh");

    private By fbNumberField = By.id("fFilterFbnum");

    private By applyAndCloseButton = By.id("bOkRes-icon");

    private By taxpayersRequest = By.id("appList-listUl");

    private By nextButton = By.id("idappXML1--btnStepRegTxTypComp-content");

    private By outletsNextStep = By.id("idappXML1--btnStepOutltsComp-content");

    private By assignToMe = By.id("idappXML1--btnAssignMe-content");

    private By assignSuccessMsg = By.xpath("//span[contains(@class,  'sapMMsgBoxText')]");

    private By okButton = By.id("__mbox-btn-0-content");

    private By approveButton = By.id("idappXML1--btnApprove-content");

    private By approvalSuccessMsg = By.xpath("//span[contains(@class,  'sapMMsgBoxText')]");

    private By successOkButton = By.id("__mbox-btn-1-content");

    private By logOut = By.xpath("//a[@id='logoff_png']");

    private By yesButton = By.xpath("//table[@id='button_button_std_yes']");



    public SupervisorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterData() {
        driver.findElement(user).sendKeys("nrole_sup");
        driver.findElement(password).sendKeys("Test@123");
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();

    }

    public void clickInboxButton() {
        driver.switchTo().frame("contentAreaFrame"); //switching the frame by ID
        driver.findElement(inboxButton).click();

    }


    public void clickFilterButton() {
        driver.findElement(filterButton).click();

    }

    public void enterFbNum(String Name) {
        driver.findElement(clearButton).click();
        driver.findElement(fbNumberField).sendKeys(Name);

    }

    public void clickApplyAndCloseButton() {
        driver.findElement(applyAndCloseButton).click();

    }

    public void clickTaxpayersRequest() {
        driver.findElement(taxpayersRequest).click();

    }

    public void clickNextButton() {
        WebElement iframe = driver.findElement(By.xpath("//div[@id = 'sap-ui-static']/following-sibling::div/descendant::iframe"));
        driver.switchTo().frame(iframe);
        driver.findElement(nextButton).click();

    }

    public void clickOutletNextButton() {
        driver.findElement(outletsNextStep).click();

    }

    public void clickAssignToMe() {
        driver.findElement(assignToMe).click();
    }

    public String getAssignSuccessMessage() {
        return driver.findElement(assignSuccessMsg).getText();

    }

    public void clickOkButton() {
        driver.findElement(okButton).click();

    }

    public void clickApproveButton() {
        driver.findElement(approveButton).click();
    }

    public String getApprovalSuccessMessage() {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(ExpectedConditions.visibilityOfElementLocated(approvalSuccessMsg));
        String ApprovalMsg = driver.findElement(approvalSuccessMsg).getText();
        System.out.println(ApprovalMsg);
        System.out.println("The Taxpayer Identification Number is " + ApprovalMsg.substring(17 ,27));
        return ApprovalMsg;

    }

    public void clickApprovalOkButton() {
        driver.findElement(successOkButton).click();
    }

    public void clickLogOut() {
        driver.switchTo().defaultContent();
        driver.findElement(logOut).click();

    }

    public void clickYesButton(){
        driver.findElement(yesButton).click();
    }

}
