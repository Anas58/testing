package NonRegularTaxpayer;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Acknowledgement {
    WebDriver driver;

    public Acknowledgement(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    private final By successMessage=By.xpath("(//mat-card-content//p)[1]");


    public String verifySuccessMessage(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(300));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}


