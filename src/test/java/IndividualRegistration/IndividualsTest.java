package IndividualRegistration;

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
public class IndividualsTest {

    WebDriver driver;
    IndividualRegisterPage individualRegisterPage;
    ReviewInformationPage reviewInformationPage;
    public String code;
    public String codeAfterRenew;
    OtpPage otpPage;
    PasswordPage passwordPage;
    AcknowledgementPage acknowledgementPage;
    File file = new File("../resources/ExcelData/IndividualRegistrationData.xlsx");

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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup?lang=en");
        driver.manage().window().maximize();

        individualRegisterPage = new IndividualRegisterPage(driver);
        reviewInformationPage = new ReviewInformationPage(driver);
        otpPage = new OtpPage(driver);
        passwordPage = new PasswordPage(driver);
        acknowledgementPage = new AcknowledgementPage(driver);
        individualRegisterPage.clickIndividualsButton();
    }


    @DataProvider(name = "positive_individualRegisterPage")
    public Object[][] dataRetriever1() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "IndividualRegistration", "english", "positive");
        return data;
    }

    @DataProvider(name = "negative_individualRegisterPage")
    public Object[][] dataRetriever2() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "IndividualRegistration", "english", "negative");
        return data;

    }

    @DataProvider(name = "individualRegisterLabels_english")
    public Object[][] individualRegisterLabels() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "IndividualLabels", "english", null);
        return data;

    }

    @DataProvider(name = "reviewInfoLabels_english")
    public Object[][] reviewInfoLabels() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "ReviewInformationLabels", "english", null);
        return data;

    }

    @DataProvider(name = "positive_reviewInformationPage")
    public Object[][] dataRetriever5() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "ReviewInformationPage", "english", "positive");
        return data;
    }

    @DataProvider(name = "negative_reviewInformationPage")
    public Object[][] dataRetriever3() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "ReviewInformationPage", "English", "negative");
        return data;
    }

    @DataProvider(name = "negative_passwordPage")
    public Object[][] dataRetriever4() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "PasswordPage", "english", "negative");
        return data;
    }

    @DataProvider(name = "passwordPageLabels_english")
    public Object[][] passwordPageLabelsEnglish() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "PasswordPageLabels", "english", null);
        return data;

    }

    @DataProvider(name = "positive_passwordPage")
    public Object[][] dataRetriever6() {
        Object[][] data = ExcelParser.passToDataProvider(file.getAbsolutePath(), "PasswordPage", "english", "positive");
        return data;
    }


    @Test(enabled = true, dataProvider = "individualRegisterLabels_english", groups={"regression"}, priority = 0)
    public void IndividualRegistrationEngLabels(Map<String, String> labels) {
        String url = driver.getCurrentUrl();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(url.contains("angular/#/signup/individual"));
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String t = individualRegisterPage.getFieldsLabels(entry.getKey());
            softAssert.assertEquals(t, entry.getValue());
        }
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "negative_individualRegisterPage", groups={"regression"}, priority = 1)
    public void IndividualRegisterNegative(Map<String, String> data) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssert = new SoftAssert();
        individualRegisterPage.fillData(data);
        individualRegisterPage.clickContinueButton();
        if (data.get("page_error_message") != null) {
            softAssert.assertEquals(individualRegisterPage.getPageLevelError(), data.get("page_error_message"));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertFalse(reviewInformationPage.isReviewPageVisible(),"Verify page hasn't changed");

        if(reviewInformationPage.isReviewPageVisible()) reviewInformationPage.clickIndividualButton();

        if (data.get("field_error_message") != null) {
            softAssert.assertEquals(individualRegisterPage.getFieldLevelError(data), data.get("field_error_message"));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertAll();

    }

    @Test(enabled = true, dataProvider = "positive_individualRegisterPage", groups={"regression", "smoke"}, priority = 2)
    public void IndividualRegisterPositive(Map<String, String> data) {
        SoftAssert softAssert = new SoftAssert();

        individualRegisterPage.fillData(data);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        individualRegisterPage.clickContinueButton();
        String url = driver.getCurrentUrl();
        softAssert.assertTrue(url.contains("angular/#/signup/individual"));

//        if (reviewInformationPage.verifyReviewPageIsVisible()) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            reviewInformationPage.clickIndividualRegisterIcon();
//        }
        softAssert.assertTrue(reviewInformationPage.verifyReviewPageIsVisible(), "Verify page has changed");

        if(!reviewInformationPage.verifyReviewPageIsVisible()) individualRegisterPage.clickBackButton();

        softAssert.assertAll();

    }

    @Test(priority = 3 ,groups={"regression", "smoke"})
    public void continueToReviewPage() {
        individualRegisterPage.clickContinueButton();
    }


    @Test(enabled = true, dataProvider = "reviewInfoLabels_english", groups={"regression"}, priority = 4)
    public void reviewInformationEngLabels(Map<String, String> labels) {
        SoftAssert softAssert = new SoftAssert();
        String url = driver.getCurrentUrl();
        softAssert.assertTrue(url.contains("angular/#/signup/individual"));
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String t = reviewInformationPage.getFieldsLabels(entry.getKey());
            softAssert.assertEquals(t, entry.getValue());
        }
        softAssert.assertAll();

    }

    @Test(enabled = true, dataProvider = "negative_reviewInformationPage", groups={"regression"}, priority = 5)
    public void ReviewInformationNegativeTest(Map<String, String> data) {
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reviewInformationPage.fillData(data);
        code = reviewInformationPage.getCaptchatext();
        reviewInformationPage.clickRenewVerificationCode();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        codeAfterRenew = reviewInformationPage.getCaptchatext();
        Assert.assertNotEquals(code, codeAfterRenew);
        //testRenewVerificationCode();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reviewInformationPage.enterVerificationCode(codeAfterRenew);
        reviewInformationPage.clickContinueButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertFalse(otpPage.verifyOTP(), "Verify page hasn't changed");

        if(data.get("error_message") != null) {
            softAssert.assertEquals(reviewInformationPage.getFieldLevelError(data), data.get("error_message"));
        }

        softAssert.assertAll();

    }


    @Test(enabled = true, dataProvider = "positive_reviewInformationPage", groups={"regression", "smoke"}, priority = 6)
    public void ReviewInformationPositiveTest(Map<String, String> data) {
        //System.out.println(reviewInformationPage.getPageSuccessMessage());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reviewInformationPage.fillData(data);
        code = reviewInformationPage.getCaptchatext();
        reviewInformationPage.clickRenewVerificationCode();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        codeAfterRenew = reviewInformationPage.getCaptchatext();
        Assert.assertNotEquals(code, codeAfterRenew);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reviewInformationPage.enterVerificationCode(codeAfterRenew);
        reviewInformationPage.clickContinueButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(otpPage.verifyOTP(), "Verify Page has Changed");

        softAssert.assertAll();
    }

    @Test(priority = 7, groups={"regression", "smoke"})
    public void testOtpPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(otpPage.getOtpSuccessMessage(), "OTP Sent. Please Enter OTP");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // otpPage.clickResendOtpCode();
        otpPage.enterOTP();
        softAssert.assertEquals(otpPage.getOtpErrorMessage(), "The entered one-time password is not correct. Kindly re-enter the correct one-time password, or click on (Resend Verification Code) button");

    }

    @Test(priority = 8, dataProvider = "passwordPageLabels_english", groups={"regression"})
    public void passwordPageEngLabels(Map<String, String> labels) {
        SoftAssert softAssert = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String t = passwordPage.getFieldsLabels(entry.getKey());
            softAssert.assertEquals(t, entry.getValue());
        }
        softAssert.assertAll();
    }


    @Test(enabled = true, dataProvider = "negative_passwordPage", groups={"regression"}, priority = 9)
    public void PasswordPageNegativeTest(Map<String, String> data) {
        passwordPage.clickHidePassword();
        passwordPage.clickHideConfirmPassword();
        passwordPage.fillData(data);
        passwordPage.clickHidePassword();
        passwordPage.clickHideConfirmPassword();
        passwordPage.clickConfirmButton();
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(passwordPage.checkPasswordPageIsDisplayed(), "Verify page hasn't changed");
        softAssertion.assertAll();

    }

    @Test(enabled = true, dataProvider = "positive_passwordPage", groups={"regression", "smoke"}, priority = 10)
    public void PasswordPagePositiveTest(Map<String, String> data) {
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

    }

    @Test(priority = 11, groups={"regression", "smoke"})
    public void testAcknowledgementPage() {
        acknowledgementPage.closeRatingPopUp();
        System.out.println(acknowledgementPage.getMainSuccessMessage());
        System.out.println(acknowledgementPage.getSubSuccessMessage());
        acknowledgementPage.clickGoToLoginPage();
    }

}