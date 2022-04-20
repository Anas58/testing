package EstablishmentRegistration;

import CompanyRegistrationPages.ExcelParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Map;

public class OfficerPortalTest {
    WebDriver driver;
    OfficerPortal officerPortalPage;
    String tpApplicationNumber;

    public OfficerPortalTest(String tpApplicationNumber) {
        this.tpApplicationNumber = tpApplicationNumber;
    }

    @BeforeTest
    public void openOfficer(){
        System.setProperty("webdriver.chrome.driver", "C://Users//mmuhammad-c//Desktop//Driver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://toportalqa.gazt.gov.sa/irj/portal?ume.logon.locale=en");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
    }
    @DataProvider(name = "Positive_Officer_portal")
    public Object[][] testData() {
        Object[][] data = ExcelParser.passToDataProvider("C:\\Users\\mmuhammad-c\\Desktop\\establishment_test_data.xlsx", "officer", "english", "positive");
        return data;
    }
    @Test(enabled = true,dataProvider ="Positive_Officer_portal",priority = 0)
    public void officerPortalApprove(Map<String,String>data){
        officerPortalPage=new OfficerPortal(driver);
        officerPortalPage.officerApproval(data);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(officerPortalPage.officerApproval(data),"Form " + "'" + tpApplicationNumber + "' " + "Approved Successfully!");
         officerPortalPage.logOut();
    }

}