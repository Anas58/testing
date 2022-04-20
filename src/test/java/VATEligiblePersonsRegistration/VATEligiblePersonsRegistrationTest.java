package VATEligiblePersonsRegistration;

import CompanyRegistrationPages.ExcelParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class VATEligiblePersonsRegistrationTest {
    WebDriver driver;
    OrganizationInformation organizationInformation;
    VerificationCode verificationCode;
    CreateNewPassword createNewPassword;
    File file = new File("../resources/ExcelData/VATEligiblePersonsRegistration.xlsx");


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
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup/others/vatEligiblePerson");
        driver.manage().window().maximize();
        organizationInformation = new OrganizationInformation(driver);
        verificationCode = new VerificationCode(driver);
        createNewPassword = new CreateNewPassword(driver);
    }

    @DataProvider(name = "organization_information_labels")
    public Object[][] organizationInformationLabels() {
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationLabels", "english", null);
        return labels;
    }


    @DataProvider(name = "OrganizationInformationPage_negative_scenario")
    public Object[][] OrganizationInformationPage_negativeScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationinformation", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "OrganizationInformationPage_positive_scenario")
    public Object[][] OrganizationInformationPage_positveScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "organizationinformation", "english", "positive");
        return rowOfData;
    }


    @DataProvider(name = "VerificationCodePage_negative_scenario")
    public Object[][] verificationCodePage_negativeScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "verification_code", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "VerificationCodePage_positive_scenario")
    public Object[][] verificationCodePage_positiveScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "verification_code", "english", "positive");
        return rowOfData;
    }


    @DataProvider(name = "password_labels")
    public Object[][] passwordLabels() {
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "labels2", "english", null);
        return labels;
    }

    @DataProvider(name = "password_negative_scenario")
    public Object[][] password_negative_scenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "password", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "password_positive_scenario")
    public Object[][] password_positive_scenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "password", "english", "positive");
        return rowOfData;
    }


    @Test(enabled = true, dataProvider = "organization_information_labels", groups = {"regression"}, priority = 0)
    public void organizationLabelsEnglish(Map<String, String> labels) {
        System.out.println(labels);
        SoftAssert softAssertion = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String A = organizationInformation.getFields_Labels(entry.getKey());
            softAssertion.assertEquals(A, entry.getValue());
        }
        softAssertion.assertAll();
    }

    @Test(enabled = true, priority = 1, groups = {"regression"})
    public void renewIcon() {
        String A = organizationInformation.getCaptchaText();
        organizationInformation.renewCaptch();
        SoftAssert softAssertion = new SoftAssert();

        softAssertion.assertNotEquals(A, organizationInformation.getCaptchaText());
    }



    @Test(enabled = true, dataProvider = "OrganizationInformationPage_negative_scenario", groups = {"regression"}, priority = 2)
    public void OrganizationInformationPage_negativeScenario(Map<String, String> rowOfData) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // fill Form
        organizationInformation.fillForm(rowOfData);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // click on Continue
        organizationInformation.clickOnContinueButton();
        // wait for next page navigation (if any)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        // verify page has not changed
        softAssertion.assertTrue(organizationInformation.checkOrganizationInformationPage(), "Verify page has not changed");
        // go Back if page has changed
        if (!organizationInformation.checkOrganizationInformationPage()) organizationInformation.clickOnBackButton();
        // verify Fields Error Messages
        softAssertion.assertEquals(organizationInformation.getFields_ErrorMessages(rowOfData), rowOfData.get("error_message"));
        // verify Page Error Message if any
        if (rowOfData.get("page_error") != null) {
            softAssertion.assertEquals(organizationInformation.getPage_ErrorMessage(), rowOfData.get("page_error"));
        }
        softAssertion.assertAll();
    }


    @Test(enabled = true, dataProvider = "OrganizationInformationPage_positive_scenario", groups = {"regression", "smoke"}, priority = 3)
    public void OrganizationInformationPage_positiveScenario(Map<String, String> rowOfData) {
        // fill Form
        organizationInformation.fillForm(rowOfData);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // click Continue
        organizationInformation.clickOnContinueButton();
        // wait for next page navigation (if any)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // verify page has changed
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(verificationCode.checkVerificationCodePage());
    }


    @Test(enabled = false, dataProvider = "VerificationCodePage_negative_scenario", groups = {"regression"}, priority = 5)
    public void verificationCodePage_negativeScenario(Map<String, String> rowOfData) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //enter verification code
        verificationCode.enterVerificationCode(rowOfData);

        SoftAssert softAssertion = new SoftAssert();
        // verify page has not changed
        softAssertion.assertTrue(verificationCode.checkVerificationCodePage(), "Verify page has not changed");

        // go Back if page has changed
        if (!verificationCode.checkVerificationCodePage()) verificationCode.clickOnBackButton();

        // verify Page Messages
        softAssertion.assertEquals(verificationCode.getPageMessage(rowOfData), rowOfData.get("page_message"));

        // wait for verification code countdown
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // click on Resend Verification Code Button
        verificationCode.clickOnResendVerificationCodeButton();

        softAssertion.assertAll();
    }


    @Test(enabled = true, dataProvider = "VerificationCodePage_positive_scenario", groups = {"regression"}, priority = 4)
    public void verificationCodePage_positiveScenario(Map<String, String> rowOfData) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //enter verification code
        verificationCode.enterCode();

        // wait for next page navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // verify page has changed
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(verificationCode.checkPasswordPageIsDisplayed());
    }

    @Test(enabled = true, dataProvider = "password_labels", groups = {"regression"}, priority = 5)
    public void verifyLabels2(Map<String, String> labels) {
        SoftAssert softAssertion = new SoftAssert();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();}
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String A = createNewPassword.fieldsLabel(entry.getKey());
            softAssertion.assertEquals(A, entry.getValue());
        }
        softAssertion.assertAll();
    }


    @Test (enabled = true, dataProvider = "password_negative_scenario", priority = 6)
    public void password_negativeScenario(Map<String, String> rowOfData) {
        createNewPassword.fillData(rowOfData);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        if (rowOfData.get("error_messages") != null) {
            softAssertion.assertEquals(createNewPassword.fieldValidateErrorMessage(rowOfData), rowOfData.get("error_messages"));
        }
        softAssertion.assertFalse(createNewPassword.isContinueButtonEnabled(), "Assert confirm button is Enabled");
        softAssertion.assertAll();
    }


    @Test(enabled = true, dataProvider = "password_positive_scenario", priority = 7)
    public void password_positiveScenario(Map<String, String> rowOfData){
        createNewPassword.fillData(rowOfData);
        createNewPassword.checkAgreeCheckBox();
        createNewPassword.openTermsAndConditionPopup();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createNewPassword.checkDeclarationCheckBox();
        createNewPassword.checkAuthorizationCheckBox();
        createNewPassword.confirmButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createNewPassword.closeSurvey();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // verify page has changed
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(createNewPassword.checkRegistrationSuccessfulPagesDisplayed());
    }


    @Test(enabled = false, priority = 8)
    public void registrationSuccessfulTest(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createNewPassword.clickOnGoToLoginButton();
    }

}
