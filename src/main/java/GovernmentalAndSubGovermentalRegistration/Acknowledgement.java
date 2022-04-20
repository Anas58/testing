package GovernmentalAndSubGovermentalRegistration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Acknowledgement {
    WebDriver driver;


    //Locators
    private By ratingPopUp = By.xpath("//img[@class = 'close_s2c_survey']");
    By name = By.xpath("(//i[contains(@class,'ackScreenIcon')]/ancestor::mat-card//span)[2]");
    By applicationNumber = By.xpath("(//i[contains(@class,'ackScreenIcon')]/ancestor::mat-card//span)[4]");
    By date = By.xpath("(//i[contains(@class,'ackScreenIcon')]/ancestor::mat-card//span)[6]");


    public Acknowledgement(WebDriver driver) {
        this.driver = driver;
    }

    //This closes the rating pop up
    public void closePopUp() {
        WebElement wait = new WebDriverWait(driver, 360).until(ExpectedConditions.visibilityOfElementLocated(ratingPopUp));
        driver.findElement(ratingPopUp).click();

    }

    //Method to save Name Application Number and date.
    public String getApplicationNumber() {
        return driver.findElement(applicationNumber).getText();

    }

    //Method Get System Date
    public void getSystemDateAndCompare() {
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Date systemDate = new Date();
        String date = dateFormat.format(systemDate);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getRegistrationDate(), date);
        softAssert.assertAll();

    }

    //Method Read Date from Website
    public String getRegistrationDate() {
        Date registrationDate = null;
        String tpRegistrationDate = driver.findElement(date).getText();
        return tpRegistrationDate;

    }

    //Store Tp name on Acknowledgement page
    public String getTpName() {
        String tpName = driver.findElement(name).getText();
        System.out.println(tpName);
        return tpName;

    }

    //This gets the agency name from excel
    public String getTpNameFromExcel(Map<String, String> data) {
        if (data.get("government_agency_name") != null) {
            String name = data.get("government_agency_name");
            return name;

        }
       return null;
    }


}