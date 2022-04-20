package NonResidentCompanyRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OTP {

    WebDriver driver;

    public OTP(WebDriver driver){
        this.driver = driver;
    }

    private By OTPField = By.id("one");

    public void enterOTP(){
        driver.findElement(OTPField).sendKeys("0106");
    }
    public boolean verifyOTP(){
        return driver.findElement(OTPField).isDisplayed();
    }
}
