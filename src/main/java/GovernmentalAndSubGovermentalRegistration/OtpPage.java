package GovernmentalAndSubGovermentalRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OtpPage {

    WebDriver driver;

    //OTP Success Message
    private By otpMessage = By.xpath("//p[contains(@class, 'notifier__notification-message')]");

    //Resend Verification Code
    private By resendOtpCode = By.xpath("//button[@class = 'h6 resend']");

    //OTP
    private By otp1 = By.id("one");

    //Close button for page notification messages
    private By otpMessageCloseButton = By.xpath("//button[contains(@class,'notifier__notification-button')]");


    public OtpPage(WebDriver driver) {
        this.driver = driver;
    }

    //This clicks the resend OTP link
    public void clickResendOtpCode(){
        WebElement wait = new WebDriverWait(driver , 140)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(resendOtpCode)));

        driver.findElement(resendOtpCode).click();
    }

    //This inputs the OTP
    public void enterOtp() {
        driver.findElement(otp1).sendKeys("0106");

    }

    //This gets the Otp success or error message
    public String getOtpMessage(){
        List<WebElement> elements = driver.findElements(otpMessage);
        if(elements.size() > 0){
            return elements.get(0).getText();
        }
        return null;
    }

    //This closes the Otp success or error message
    public void closeOtpMessage(){
        driver.findElement(otpMessageCloseButton).click();
    }

}
