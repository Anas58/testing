package VATEligiblePersonsRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class VerificationCode {
    WebDriver driver;

    public VerificationCode(WebDriver driver){this.driver = driver;}


    private By firstDigit = By.xpath("//input[@id='one'] [@class = 'Verif-box ash']");
    private By resendVerificationCodeButton = By.xpath("//button[@class='h6 resend']");
    private By pageMessage = By.xpath("//p[@class='notifier__notification-message ng-star-inserted']");
    private By pageMessage_closeButton = By.xpath("//button[@title='dismiss']//*[name()='svg']");
    private By backButton = By.xpath("//div[@class='pointer-hand text-left']");
    private By passwordPage = By.xpath("//div[@class= 'titleCssS']");

    // enter Verification Code
    public void enterVerificationCode(Map<String, String> dataRow) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        driver.findElement(firstDigit).sendKeys(selectAll + Keys.BACK_SPACE);
        if (dataRow.get("verification_code") != null) {
            driver.findElement(firstDigit).sendKeys(dataRow.get("verification_code"));
        }
    }
    //     enter Verification Code
    public void enterCode() {
        driver.findElement(firstDigit).sendKeys("0106");
    }

    // click on Resend Verification Code Button
    public void clickOnResendVerificationCodeButton() {
        driver.findElement(resendVerificationCodeButton).click();
    }

    //get Page Messages
    public String getPageMessage(Map<String, String> rowOfData){

        List<WebElement> Messages = driver.findElements(pageMessage);
        if (Messages.size() > 0){
            // get first message
            String Message = Messages.get(0).getText();
            // close messages
            List <WebElement> errorMessagesCloseButton = driver.findElements(pageMessage_closeButton);
            for (int index = errorMessagesCloseButton.size()-1; index >= 0; index--){
                errorMessagesCloseButton.get(index).click();
            }
            return Message;
        }
        return null;
    }

    // check The Current Page
    public boolean checkVerificationCodePage() {
        return driver.findElement(firstDigit).isDisplayed();
    }

    // check The Next page
    public boolean checkPasswordPageIsDisplayed() {
        return driver.findElement(passwordPage).isDisplayed();
    }


    // click on Back Button
    public void clickOnBackButton() {
        driver.findElement(backButton).click();
    }
}

