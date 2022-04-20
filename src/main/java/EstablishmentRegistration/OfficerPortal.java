package EstablishmentRegistration;
 import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class OfficerPortal {
    WebDriver driver;
    String successMessage;
    Acknowledgement acknowledgementPage;


    public OfficerPortal(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By userName = By.xpath("//input[@class='username']");
    By password = By.xpath("//input[@class='password']");
    By loginButton = By.name("uidPasswordLogon");
    By inbox = By.xpath("//div[@id='inbox']");
    By filterButton = By.xpath("//button[@id='filterBtn']");
    By referenceNumber = By.xpath("//input[@id='fFilterFbnum']");
    By applyAndClose = By.xpath("//button[@id='bOkRes']");
    By openApplication = By.xpath("//ul[@id='appList-listUl']");
    By assignToMe = By.xpath("//button[@id='idappXML1--btnAssignMe']");
    By assignSuccessOkButton = By.xpath("//button[@id='__mbox-btn-0']");
    By nextButtonRegistrationTPType = By.xpath("//button[@id='idappXML1--btnStepRegTxTypComp']");
    By nextButtonTPDetails = By.xpath("//button[@id='idappXML1--btnStepTxpyrDtlComp']");
    By nextButtonOutlet = By.xpath("//button[@id='idappXML1--btnStepOutltsComp']");
    By financialDetailsCheckBox = By.xpath("//div[@id='idappXML1--cboxfinDtlOffChck-CbBg']");
    By nextButtonFinancialDetails = By.xpath("//button[@id='idappXML1--btnStepFinDtlComp']");
    By approveButton = By.xpath("//button[@id='idappXML1--btnApprove']");
    By rejectButton = By.xpath("//button[@id='idappXML1--btnReject']");
    By voidButton = By.xpath("//button[@id='idappXML1--btnVoid']");
    By notesButton = By.xpath("//button[@id='idappXML1--btnNotes']");
    By notesTxtArea = By.xpath("//textarea[@id='idappXML1--shNotesTA-inner']");
    By okNotesButton = By.xpath("//button[@id='__button9']");
    By cancelNotesButton = By.xpath("//button[@id='__button10']");
    By approveOkButton = By.xpath("//button[contains(@id,\"__mbox-btn\")]");
    By approveSuccessMessage = By.xpath("//span[@class='sapMMsgBoxText sapMText sapMTextMaxWidth sapUiSelectable']");
    By logOutIcon = By.xpath("//a[@id='logoff_png']");
    By yesButton = By.xpath("//table[@id='button_button_std_yes']");

    //Method To Approve Application from Officer side
    public String officerApproval(Map<String, String> data) {
        if (data.get("user_name") != null) {
            driver.findElement(userName).sendKeys(data.get("user_name"));
        }
        if ((data.get("password") != null)) {
            driver.findElement(password).sendKeys(data.get("password"));
        }
        driver.findElement(loginButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("contentAreaFrame"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inbox)).click();

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(filterButton).click();
        acknowledgementPage = new Acknowledgement(driver);



        //acknowledgementPage.storeApplicationNumber();
       driver.findElement(referenceNumber).sendKeys(acknowledgementPage.getApplicationNumber());
        //driver.findElement(referenceNumber).sendKeys("10001128570");
        driver.findElement(applyAndClose).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(openApplication)).click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("__html36"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(assignToMe)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(assignSuccessOkButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextButtonRegistrationTPType)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextButtonTPDetails)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextButtonOutlet)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(financialDetailsCheckBox)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextButtonFinancialDetails)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(approveButton)).click();
        for (int i = 0; i <= 9; i++) {
            try {
                successMessage = driver.findElement(approveSuccessMessage).getText();
                break;

            } catch (Exception e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                  //  ex.printStackTrace();
                }
               // e.printStackTrace();
            }
        }
        //String successMessage = driver.findElement(approveSuccessMessage).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(approveOkButton)).click();
        return successMessage;

    }

    public void logOut() {
        driver.switchTo().defaultContent();
        driver.findElement(logOutIcon).click();
        driver.findElement(yesButton).click();
    }

}
