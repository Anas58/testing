package IndividualRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class OtpPage {

    WebDriver driver;

    //OTP Error Messages
    private By otpSuccessMessage = By.xpath("//p[contains(@class, 'notifier__notification-message')]");

    //OTP Success Message
    private By otpErrorMessage = By.xpath("//p[contains(@class, 'notifier__notification-message')]");

    //Resend Verification Code
    private By resendOtpCode = By.xpath("//button[@class = 'h6 resend']");

    //OTP
    private By OTPField = By.id("one");

    public OtpPage(WebDriver driver) {
        this.driver = driver;

    }
//    public void clickResendOtpCode(){
//        WebElement wait = new WebDriverWait(driver , 140)
//                .until(ExpectedConditions.elementToBeClickable(resendOtpCode));
//
//        driver.findElement(resendOtpCode).click();
//    }
public boolean existsElement(By element) {
    try {
        driver.findElement(element);
    } catch (NoSuchElementException e) {
        return false;
    }
    return true;
}

    public void enterOTP(){
        driver.findElement(OTPField).sendKeys("0106");
    }

    public boolean verifyOTP(){
        boolean result = false;
        if(existsElement(OTPField)) {
            result = driver.findElement(OTPField).isDisplayed();
        }
        return result;
    }

    public String getOtpSuccessMessage(){
        List<WebElement> elements = driver.findElements(otpSuccessMessage);
        if(elements.size() > 0){
            return elements.get(0).getText();
        }
        return null;
    }

    public String getOtpErrorMessage(){
        List<WebElement> elements = driver.findElements(otpErrorMessage);
        if (elements.size() > 0){
            return elements.get(0).getText();
        }
        return null;
    }


}
