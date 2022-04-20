package EstablishmentRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Otp {
    WebDriver driver;
    public Otp(WebDriver driver) {
        this.driver = driver;
    }

    /* Locators */
    By otp = By.xpath("//signup-otp//input[1]");

    public void enterOTP() {
        driver.findElement(otp).sendKeys("0106");
    }

    public boolean verifyOTP(){
        return driver.findElement(otp).isDisplayed();
    }
}