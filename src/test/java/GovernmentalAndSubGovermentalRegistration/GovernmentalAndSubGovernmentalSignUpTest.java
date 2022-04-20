package GovernmentalAndSubGovermentalRegistration;

import CompanyRegistrationPages.ExcelParser;
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
public class GovernmentalAndSubGovernmentalSignUpTest {

    WebDriver driver;
    OrganizationInformationPage organizationInformationPage;
    public String code;
    public String codeAfterRenew;
    OtpPage otpPage;
    PasswordPage passwordPage;
    Acknowledgement acknowledgement;
    OfficerPage officerPage;
    SupervisorPage supervisorPage;
    public String tpApplicationNumber;
    File file = new File("../resources/ExcelData/GovernmentalAndSubGovernmentalData.xlsx");

    static {
        System.setProperty("extent.reporter.spark.start", "true");
        System.setProperty("extent.reporter.spark.config", "../resources/avent-config.xml");
        System.setProperty("extent.reporter.spark.out", "../resources/");
    }

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "../resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get("https://loginqa.gazt.gov.sa/irj/portal?ume.logon.locale=en&login=angular");
        driver.manage().window().maximize();


        organizationInformationPage = new OrganizationInformationPage(driver);
        otpPage = new OtpPage(driver);
        passwordPage = new PasswordPage(driver);
        acknowledgement = new Acknowledgement(driver);
        officerPage = new OfficerPage(driver);
        supervisorPage = new SupervisorPage(driver);


        organizationInformationPage.clickSignUpButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        organizationInformationPage.clickOtherButton();
        organizationInformationPage.clickGovtAndSubGovernmentalButton();

    }


    @DataProvider(name = "OrganizationInfoLabels_english")
    public Object[][] OrganizationInfoLabels_english() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "OrganizationInfoLabels", "english", null);
        return data;

    }

    @DataProvider(name = "positive_organizationInformationPage")
    public Object[][] dataRetriever1() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "OrganizationInformationPage", "Eng", "positive");
        return data;

    }

    @DataProvider(name = "negative_organizationInformationPage")
    public Object[][] dataReceiver2() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "OrganizationInformationPage", "Eng", "negative");
        return data;
    }

    @DataProvider(name = "PasswordPageLabels_english")
    public Object[][] PasswordPageLabels_english() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "PasswordPageLabels", "english", null);
        return data;

    }

    @DataProvider(name = "negative_passwordPage")
    public Object[][] dataRetriever4() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "PasswordPage", "english", "negative");
        return data;
    }

    @DataProvider(name = "positive_passwordPage")
    public Object[][] dataRetriever6() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "PasswordPage", "english", "positive");
        return data;
    }

    //This checks for the labels on the organization information page
    @Test(enabled = true, dataProvider = "OrganizationInfoLabels_english", groups={"regression"}, priority = 0)
    public void OrganizationInfoLabelsEng(Map<String, String> labels) {
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String t = organizationInformationPage.getFieldsLabels(entry.getKey());
            softAssert.assertEquals(t, entry.getValue());
        }
        softAssert.assertAll();
    }


    //This tests the organization page with negative scenarios
    @Test(priority = 1, enabled = true, dataProvider = "negative_organizationInformationPage", groups={"regression"})
    public void testOrganizationPageNeg(Map<String, String> data) {
        SoftAssert softAssert = new SoftAssert();
        organizationInformationPage.fillData(data);
/*
        code = organizationInformationPage.getCaptchatext();
        organizationInformationPage.clickRenewVerificationCode();
        codeAfterRenew = organizationInformationPage.getCaptchatext();
        Assert.assertNotEquals(code, codeAfterRenew);
        organizationInformationPage.enterVerificationCode(codeAfterRenew);
*/
        organizationInformationPage.clickContinueButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (data.get("page_error_message") != null) {
            softAssert.assertEquals(organizationInformationPage.getPageErrorMessage(), data.get("page_error_message"));
        }

        if (data.get("field_error_message") != null) {
            softAssert.assertEquals(organizationInformationPage.getFieldErrorMsg(data), data.get("field_error_message"));

        }

        softAssert.assertAll();

    }

    //This tests the organization page with positive scenarios
    @Test(priority = 2, enabled = true, dataProvider = "positive_organizationInformationPage", groups={"regression", "smoke"})
    public void testOrganizationPagePos(Map<String, String> data) {
        SoftAssert softAssert = new SoftAssert();
        organizationInformationPage.fillData(data);
        code = organizationInformationPage.getCaptchatext();
        organizationInformationPage.clickRenewVerificationCode();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        codeAfterRenew = organizationInformationPage.getCaptchatext();
        Assert.assertNotEquals(code, codeAfterRenew);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        organizationInformationPage.enterVerificationCode(codeAfterRenew);
        organizationInformationPage.clickContinueButton();

        softAssert.assertAll();

    }


    //This test the OTP page
    @Test(priority = 3, enabled = true, groups={"regression", "smoke"})
    public void testOtpPage() {
        SoftAssert softAssert = new SoftAssert();
        //softAssert.assertEquals(otpPage.getOtpMessage(), "OTP Sent. Please Enter OTP");
        //otpPage.closeOtpMessage();

        //otpPage.clickResendOtpCode();
        otpPage.enterOtp();
        //softAssert.assertEquals(otpPage.getOtpMessage(), "The entered one-time password is not correct. Kindly re-enter the correct one-time password, or click on (Resend Verification Code) button");

        softAssert.assertAll();
    }

    //This asserts the password page labels
    @Test(enabled = true, dataProvider = "PasswordPageLabels_english", priority = 4, groups={"regression"})
    public void passwordPageLabelsEng(Map<String, String> labels) {
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String t = passwordPage.getFieldsLabels(entry.getKey());
            softAssert.assertEquals(t, entry.getValue());
        }
        softAssert.assertAll();

    }


    //This tests the password page with wrong password and wrong password combinations
    @Test(priority = 5, enabled = true, dataProvider = "negative_passwordPage", groups={"regression"})
    public void testPasswordPage(Map<String, String> data) {
        passwordPage.clickHidePassword();
        passwordPage.clickHideConfirmPassword();
        passwordPage.fillData(data);
        passwordPage.clickHidePassword();
        passwordPage.clickHideConfirmPassword();
        System.out.println(passwordPage.getPasswordStrengthErrorMessage());
        System.out.println(passwordPage.getPasswordStrengthColour());
        passwordPage.clickConfirmButton();


    }



    //This is to input the correct password
    @Test(priority = 6, enabled = true, dataProvider = "positive_passwordPage", groups={"regression", "smoke"})
    public void testPasswordPageP(Map<String, String> data) {
        passwordPage.clickHidePassword();
        passwordPage.clickHideConfirmPassword();
        passwordPage.fillData(data);
        passwordPage.clickHidePassword();
        passwordPage.clickHideConfirmPassword();
        passwordPage.clickTermsAndConditionsLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        passwordPage.closeTermsAndConditionsPopUp();
        passwordPage.checkTermsAndCondition();
        passwordPage.clickConfirmButton();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    //This is the acknowledgement of registration
    @Test(priority = 7, enabled = true, dataProvider = "positive_organizationInformationPage", groups={"regression", "smoke"})
    public void testAcknowledgementPage(Map<String, String> data) {
        SoftAssert softAssert = new SoftAssert();
        acknowledgement.closePopUp();
        tpApplicationNumber = acknowledgement.getApplicationNumber();
        System.out.println(tpApplicationNumber);
        acknowledgement.getTpName();
        acknowledgement.getRegistrationDate();
        acknowledgement.getSystemDateAndCompare();
        softAssert.assertEquals(acknowledgement.getTpName(), acknowledgement.getTpNameFromExcel(data));
        softAssert.assertAll();

    }

    //Officer checks the registration application details and selects financial details
    @Test(priority = 8, enabled = false)
    public void testOfficerPage() {
        SoftAssert softAssert = new SoftAssert();
        driver.navigate().to("https://toportalqa.gazt.gov.sa/irj/portal?ume.logon.locale=en");
        officerPage.enterData();
        officerPage.clickLoginButton();

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        officerPage.clickInboxButton();

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        officerPage.clickFilterButton();
        officerPage.enterFbNum(tpApplicationNumber);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        officerPage.clickApplyAndCloseButton();
        officerPage.clickTaxpayersRequest();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        officerPage.clickAssignToMe();
        String Msg = officerPage.getAssignSuccessMessage();
        softAssert.assertTrue(Msg.contains("Assigned to you successfully!"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        officerPage.clickOkButton();
        officerPage.clickChkBox();
        officerPage.clickNextButton();
        officerPage.clickOutletNextButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        officerPage.selectFinancialMethod("Accounting Method");
        officerPage.clickCalendarChk();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Short period, this selects the short period
    @Test(priority = 9, enabled = false)
    public void testShortPeriod() {
        officerPage.selectEndOfFiscalMonth("1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        officerPage.selectEndOfFiscalDay("01");
        officerPage.clickShortPeriod();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Long Period, this selects the long period
    @Test(priority = 10, enabled = false)
    public void testLongPeriod() {
        officerPage.selectEndOfFiscalMonth("1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        officerPage.selectEndOfFiscalDay("01");

        officerPage.clickLongPeriod();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Normal Period, this selects the normal period
    @Test(priority = 11, enabled = false)
    public void testNormalPeriod() {
        officerPage.selectEndOfFiscalMonth("12");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        officerPage.selectEndOfFiscalDay("Last Day");
        officerPage.clickNormalPeriod();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Officer approves the registration application
    @Test(priority = 12, enabled = false)
    public void officerApproval() {
        SoftAssert softAssert = new SoftAssert();
        officerPage.getBusinessName();
        officerPage.clickFinancialDetailsChkbox();
        officerPage.clickApproveButton();
        String ApprovalMsg = officerPage.getApprovalSuccessMessage();
        softAssert.assertTrue(ApprovalMsg.contains("Approved Successfully!"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        officerPage.clickApprovalOkButton();
        officerPage.clickLogOut();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        officerPage.clickYesButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Supervisor approves the registration application
    @Test(priority = 13, enabled = false)
    public void testSupervisorPage() {
        driver.navigate().to("https://toportalqa.gazt.gov.sa/irj/portal?ume.logon.locale=en");
        SoftAssert softAssert = new SoftAssert();
        supervisorPage.enterData();
        supervisorPage.clickLoginButton();

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        supervisorPage.clickInboxButton();

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        supervisorPage.clickFilterButton();
        supervisorPage.enterFbNum(tpApplicationNumber);
        supervisorPage.clickApplyAndCloseButton();
        supervisorPage.clickTaxpayersRequest();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        supervisorPage.clickNextButton();
        supervisorPage.clickOutletNextButton();
        supervisorPage.clickAssignToMe();
        String Msg = supervisorPage.getAssignSuccessMessage();
        softAssert.assertTrue(Msg.contains("Assigned to you successfully!"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        supervisorPage.clickOkButton();
        supervisorPage.clickApproveButton();
        supervisorPage.getApprovalSuccessMessage();
        supervisorPage.clickApprovalOkButton();
        supervisorPage.clickLogOut();
        supervisorPage.clickYesButton();

    }

}
