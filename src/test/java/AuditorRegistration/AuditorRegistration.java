package AuditorRegistration;

import CompanyRegistrationPages.ExcelParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.TestListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;


@Listeners({TestListener.class})
public class AuditorRegistration {

    WebDriver driver;
    OrganizationInformation organizationInformation;
    OTP otpPage;
    CreateNewPassword createNewPassword;
    Acknowledgement acknowledgement;
    File file = new File("./resources/ExcelData/AuditorRegistration.xlsx");

    static {
        System.setProperty("extent.reporter.spark.start", "true");
        System.setProperty("extent.reporter.spark.config", "./resources/avent-config.xml");
        System.setProperty("extent.reporter.spark.out", "./resources/");
    }

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup/others/auditor");
        driver.manage().window().maximize();
        organizationInformation = new OrganizationInformation(driver);
    }

    @DataProvider(name = "negative_organizationInfo")
    public Object[][] negativeOrganizationInfo(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationInfo", "english", "negative");
        return data;
    }
    @DataProvider(name = "positive_organizationInfo")
    public Object[][] positiveCompanyRegistrationData() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationInfo", "english", "positive");
        return data;
    }
    @DataProvider(name = "positive_password")
    public Object[][] positivePasswordData(){
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "password", "english", "positive");
        return data;
    }


    @Test(enabled = false, dataProvider = "negative_organizationInfo", groups={"regression"}, priority = 0)
    public void negativeOrganizationInfoTest(Map<String, String> data){
        organizationInformation.fillData(data);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        organizationInformation.clickContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(organizationInformation.checkPage(),"Verify page hasn't changed");

        if(!organizationInformation.checkPage()) organizationInformation.clickBackButton();

        if(data.get("error_message") != null){
            softAssertion.assertEquals(organizationInformation.getErrorMessage(data), data.get("error_message"));
        }
        if(data.get("page_error") != null) {
            softAssertion.assertEquals(organizationInformation.getPageErrorMessage(), data.get("page_error"));
        }
        softAssertion.assertAll();
    }

    @Test(enabled = true, dataProvider = "positive_organizationInfo", groups={"regression", "smoke"}, priority = 1)
    public void positiveOrganizationInfoTest(Map<String, String> data, ITestContext context) {
        String testParam = context.getCurrentXmlTest().getParameter("tin");
        if(testParam != null) {
            data.put("TIN", testParam);
        }
        // fill form
        organizationInformation.fillData(data);
        // click continue
        organizationInformation.clickContinueButton();
        // wait for possible navigation to the next page
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        // verify page has changed
        otpPage = new OTP(driver);
        softAssertion.assertTrue(otpPage.verifyOTP());
        otpPage.enterOTP();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssertion.assertAll();
    }

    @Test(enabled = false, dataProvider = "positive_password", groups={"regression", "smoke"}, priority = 2)
    public void positivePasswordTest(Map<String, String> data){
       createNewPassword = new CreateNewPassword(driver);
        createNewPassword.fillData(data);
        createNewPassword.checkAgreeCheckBox();
        createNewPassword.confirmButton();
        createNewPassword.closeSurvey();
    }

    @Test(enabled = true, groups={"regression", "smoke"}, priority = 3)
    public void acknowledgmentTest(){
        acknowledgement = new Acknowledgement(driver);
        acknowledgement.getApplicationNumber();
        acknowledgement.getTpName();
//        acknowledgement.getSystemDateAndCompare();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date systemDate = new Date();
        String date=dateFormat.format(systemDate);
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertEquals(acknowledgement.getRegistrationDate(),date);
        softAssertion.assertAll();
    }

}