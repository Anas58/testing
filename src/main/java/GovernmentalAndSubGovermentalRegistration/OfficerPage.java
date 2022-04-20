package GovernmentalAndSubGovermentalRegistration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OfficerPage {

    WebDriver driver;

    private By user = By.id("logonuidfield");

    private By password = By.id("logonpassfield");

    private By loginButton = By.name("uidPasswordLogon");

    private By inboxButton = By.id("inbox-content");

    private By filterButton = By.id("filterBtn-img");

    private By clearButton = By.id("bRefresh");

    private By fbNumberField = By.id("fFilterFbnum");

    private By applyAndCloseButton = By.id("bOkRes-icon");

    private By taxpayersRequest = By.id("appList-listUl");

    private By fBNumber = By.id("mAppList-appList-0-number");

    private By assignToMe = By.id("idappXML1--btnAssignMe-content");

    private By assignSuccessMsg = By.xpath("//span[contains(@class,  'sapMMsgBoxText')]");

    private By okButton = By.id("__mbox-btn-0-content");

    private By approveButton = By.id("idappXML1--btnApprove-content");

    private By govtAgencyCheckBox = By.id("idappXML1--rbGovernmentA-Button");

    private By nextButton = By.id("idappXML1--btnStepRegTxTypComp-content");

    private By outletsNextStep = By.id("idappXML1--btnStepOutltsComp-content");

    private By financialMethodDrpDwn = By.id("idappXML1--sActMet-arrow");

    private By calendarChkBox = By.id("idappXML1--rbGreDt-Button");

    private By endOfFiscalMonthDropdown = By.id("idappXML1--sFinMonth-arrow");

    private By endOfFiscalMonth = By.xpath("//div[@id = 'idappXML1--sFinMonth']");

    private By endOfFiscalDayDropdown = By.id("idappXML1--sFinDay-arrow");

    private By endOfFiscalDay = By.id("idappXML1--sFinDay");

    private By normalPeriod = By.xpath("//div[contains(@id, '--rbNrmlPeriod-Button')]");

    private By shortPeriod = By.xpath("//div[contains(@id, '--rbShortPeriod-Button')]");

    private By longPeriod = By.xpath("//div[contains(@id, '--rbLongPeriod-Button')]");

    private By businessName = By.id("idappXML1--iBusiDtlBNm-inner");

    private By financialDetailsChkbox = By.id("idappXML1--cboxfinDtlOffChck-CbBg");

    private By approvalSuccessMsg = By.xpath("//span[contains(@class,  'sapMMsgBoxText')]");

    private By approvalOkButton = By.xpath("//button[contains(@id, '__mbox-btn')]");

    private By logOut = By.xpath("//a[@id='logoff_png']");

    private By yesButton = By.xpath("//table[@id='button_button_std_yes']");

    private By dropDownOptions = By.tagName("li");


    public OfficerPage(WebDriver driver) {
        this.driver = driver;
    }

    //This enters the login details of the officer
    public void enterData() {
        driver.findElement(user).sendKeys("nrole_off");
        driver.findElement(password).sendKeys("Test@123");
    }

    //This clicks the login button
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    //This clicks the Inbox
    public void clickInboxButton() {
        driver.switchTo().frame("contentAreaFrame"); //switching the frame by ID
        driver.findElement(inboxButton).click();

    }

    //This clicks the filter
    public void clickFilterButton() {
        driver.findElement(filterButton).click();
    }

    //This inputs the application number into the reference number field
    public void enterFbNum(String Name) {
        driver.findElement(clearButton).click();
        driver.findElement(fbNumberField).sendKeys(Name);

    }

    //This clicks the Apply and Close button
    public void clickApplyAndCloseButton() {
        driver.findElement(applyAndCloseButton).click();
    }

    //This clicks to open the taxpayer request application
    public void clickTaxpayersRequest() {
        driver.findElement(taxpayersRequest).click();

    }

    //This stores the FBNumber
    public String getFbNumber() {
        return driver.findElement(fBNumber).getAttribute("value");

    }

    //This clicks the assign to me button
    public void clickAssignToMe() {
        WebElement iframe = driver.findElement(By.xpath("//div[@id = 'sap-ui-static']/following-sibling::div/descendant::iframe"));
        driver.switchTo().frame(iframe);
        driver.findElement(assignToMe).click();
    }

    //This stores the assign success message
    public String getAssignSuccessMessage() {
        return driver.findElement(assignSuccessMsg).getText();

    }

    public void clickOkButton() {
        driver.findElement(okButton).click();
    }

    //This clicks the approve button
    public void clickApproveButton() {
        driver.findElement(approveButton).click();
    }

    //This clicks the checkbox
    public void clickChkBox() {
        driver.findElement(govtAgencyCheckBox).click();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void clickOutletNextButton() {
        driver.findElement(outletsNextStep).click();

    }

    //This selects the financial method
    public boolean selectFinancialMethod(String value) {
        driver.findElement(financialMethodDrpDwn).click();
        List<WebElement> allOptions = driver.findElements(dropDownOptions);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().equals(value)) {
                allOptions.get(i).click();
                return true;
            }
        }
        return false;
    }

    //This selects the beginning of the financial month
    public boolean selectEndOfFiscalMonth(String value) {
        driver.findElement(endOfFiscalMonthDropdown).click();
        List<WebElement> allOptions = driver.findElements(dropDownOptions);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().equals(value)) {
                allOptions.get(i).click();
                return true;
            }
        }
        return false;

    }

    //This selects the beginning of the financial day
    public boolean selectEndOfFiscalDay(String value) {
        driver.findElement(endOfFiscalDayDropdown).click();
        List<WebElement> allOptions = driver.findElements(dropDownOptions);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().equals(value)) {
                allOptions.get(i).click();
                return true;
            }
        }
        return false;
    }

    //This stores the business name
    public void getBusinessName() {
        System.out.println(driver.findElement(businessName).getAttribute("value"));
    }

    public void clickCalendarChk() {
        driver.findElement(calendarChkBox).click();

    }

    //This clicks the long period radio button
    public void clickLongPeriod() {
        driver.findElement(longPeriod).click();

    }

    //This clicks the short period radio button
    public void clickShortPeriod(){
        driver.findElement(shortPeriod).click();
    }

    //This clicks the normal period radio button
    public void clickNormalPeriod(){
        driver.findElement(normalPeriod).click();
    }

    //This clicks the financial details radio button
    public void clickFinancialDetailsChkbox() {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(financialDetailsChkbox));
        driver.findElement(financialDetailsChkbox).click();

    }

    //This stores the approval success message
    public String getApprovalSuccessMessage() {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(ExpectedConditions.visibilityOfElementLocated(approvalSuccessMsg));
        return driver.findElement(approvalSuccessMsg).getText();

    }

    public void clickApprovalOkButton() {
        driver.findElement(approvalOkButton).click();
    }

    public void clickLogOut() {
        driver.switchTo().defaultContent();
        driver.findElement(logOut).click();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
}


