package CharitiesandNonProfitOrganizationSignup;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Acknowledgement {
    WebDriver driver;

    public Acknowledgement(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    By name=By.xpath("(//img[contains(@src,\"success\")]/ancestor::mat-card-content//h6)[3]");
    By applicationNumber=By.xpath("(//img[contains(@src,\"success\")]/ancestor::mat-card-content//h6)[5]");
    By date=By.xpath("(//img[contains(@src,\"success\")]/ancestor::mat-card-content//h6)[7]");

    //Method to save Name Application Number and date.
    public String getApplicationNumber(){
        String tpApplicationNumber=driver.findElement(applicationNumber).getText();
        System.out.println(tpApplicationNumber);
        return tpApplicationNumber;
    }
    //Method Get System Date
    public void getSystemDateAndCompare(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date systemDate = new Date();
        String date=dateFormat.format(systemDate);
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(getRegistrationDate(),date);
        softAssertion.assertAll();

    }
//Method Read Date from Website
    public String getRegistrationDate(){
        Date registrationDate = null;
        String tpRegistrationDate=driver.findElement(date).getText();
        return tpRegistrationDate;
    }
    //Store Tp name on Acknowledgement page
    public String getTpName(){
    String tpName=driver.findElement(name).getText();
        System.out.println(tpName);
        return tpName;
}
}


