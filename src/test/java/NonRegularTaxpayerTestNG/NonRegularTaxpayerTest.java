package NonRegularTaxpayerTestNG;

import CompanyRegistrationPages.ExcelParser;
import NonRegularTaxpayer.Acknowledgement;
import NonRegularTaxpayer.CreateNewPassword;
import NonRegularTaxpayer.OTP;
import NonRegularTaxpayer.OrganizationInformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestListener;

import java.io.File;
import java.time.Duration;
import java.util.Map;

@Listeners({TestListener.class})
public class NonRegularTaxpayerTest {

    WebDriver driver;
    OrganizationInformation organizationInformation;
    OTP otpPage;
    CreateNewPassword createNewPassword;
    Acknowledgement acknowledgement;
    File file = new File("../resources/ExcelData/NonRegularTaxpayer.xlsx");

    static {
        System.setProperty("extent.reporter.spark.start", "true");
        System.setProperty("extent.reporter.spark.config", "../resources/avent-config.xml");
        System.setProperty("extent.reporter.spark.out", "../resources/");
    }

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "../resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup/others/nonRegTP");
        driver.manage().window().maximize();
        organizationInformation = new OrganizationInformation(driver);
    }

    @DataProvider(name = "positive_organizationInfo")
    public Object[][] positiveCompanyRegistrationData() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationInfo", "english", "positive");
        return data;
    }
    @DataProvider(name = "negative_organizationInfo")
    public Object[][] negativeCompanyRegistrationData() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationInfo", "english", "negative");
        return data;
    }

    @DataProvider(name = "positive_password")
    public Object[][] positivePasswordData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "password", "english", "positive");
        return data;
    }

    @DataProvider(name = "acknowledgement")
    public Object[][] acknowledgementMessage(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "acknowledgement", "english", null);
        return data;
    }



    @Test(enabled = true, dataProvider = "negative_organizationInfo", groups={"regression"}, priority = 0)
    public void negativeOrganizationTest(Map<String, String> data){
        organizationInformation.fillData(data);
        SoftAssert softAssertion = new SoftAssert();
        if(data.get("page_error") != null) {
            softAssertion.assertEquals(organizationInformation.getStorePopupMessage(), data.get("page_error"));
        }
        if(data.get("error_message") != null){
            softAssertion.assertEquals(organizationInformation.getErrorMessage(data), data.get("error_message"));
        }
        organizationInformation.clickContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssertion.assertTrue(organizationInformation.checkPage(),"Verify page hasn't changed");

        if(!organizationInformation.checkPage()) organizationInformation.clickBackButton();

        softAssertion.assertAll();

        driver.navigate().refresh();
    }

    @Test(enabled = true, dataProvider = "positive_organizationInfo", groups={"regression", "smoke"}, priority = 1)
    public void positiveOrganizationTest(Map<String, String> data){
        organizationInformation.fillData(data);
        organizationInformation.clickContinueButton();
        SoftAssert softAssertion = new SoftAssert();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // verify page has changed
        otpPage = new OTP(driver);
        softAssertion.assertTrue(otpPage.verifyOTP(), "Verify page has changed");
        softAssertion.assertAll();
        otpPage.enterOTP();
    }

    @Test(enabled = true, dataProvider = "positive_password", groups={"regression", "smoke"}, priority = 2)
    public void positivePasswordTest(Map<String, String> data){
        createNewPassword = new CreateNewPassword(driver);
        createNewPassword.fillData(data);
        createNewPassword.checkAgreeCheckBox();
        createNewPassword.checkAgreeCheckBox2();
        createNewPassword.confirmButton();

    }
    @Test(enabled = true, dataProvider = "acknowledgement", groups={"regression", "smoke"}, priority = 3)
    public void acknowledgmentTest(Map<String, String> data){
        acknowledgement = new Acknowledgement(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(acknowledgement.verifySuccessMessage(), data.get("success_message"));
        softAssert.assertAll();
    }
}