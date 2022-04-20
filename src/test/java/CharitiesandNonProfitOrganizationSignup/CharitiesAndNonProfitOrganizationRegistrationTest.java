package CharitiesandNonProfitOrganizationSignup;

import CompanyRegistrationPages.ExcelParser;
import NaturalGasCompanyRegistration.AddNewShareholders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import utilities.TestListener;

import java.io.File;
import java.time.Duration;
import java.util.Map;

@Listeners({TestListener.class})
public class CharitiesAndNonProfitOrganizationRegistrationTest {

    WebDriver driver;
    OrganizationInformation organizationInformation;
    VerificationCode verificationCode;
    AddNewBranch addNewBranch;
    FinancialDetails financialDetails;
    CreateNewPassword createNewPassword;
    Acknowledgement acknowledgement;
    boolean negativeActivityDetailsRun;
    File file = new File("../resources/ExcelData/CharitiesAndNonProfitOrganization.xlsx");

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
        driver.get("https://angularqa.gazt.gov.sa/angular/#/signup/others/npoCharity");
        driver.manage().window().maximize();
        organizationInformation = new OrganizationInformation(driver);
    }


    @DataProvider(name = "organization_information_labels")
    public Object[][] organizationInformationLabels() {
        Object[][] labels = ExcelParser.passToDataProvider(file.getAbsolutePath(), "Labels1", "english", null);
        return labels;
    }


    @DataProvider(name = "OrganizationInformationPage_negative")
    public Object[][] OrganizationInformationPage_negativeScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "OrganizationInformation", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "OrganizationInformationPage_positive_scenario")
    public Object[][] OrganizationInformationPage_positiveScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "OrganizationInformation", "english", "positive");
        return rowOfData;
    }


    @DataProvider(name = "VerificationCodePage_negative_scenario")
    public Object[][] verificationCodePage_negativeScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "VerificationCode", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "VerificationCodePage_positive_scenario")
    public Object[][] verificationCodePage_positiveScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "verification_code", "english", "positive");
        return rowOfData;
    }

    @DataProvider(name = "branchDetails_negative_scenario")
    public Object[][] branchDetails_negativeScenario() {
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "branchDetails", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "branchDetails_positive_scenario")
    public Object[][] branchDetails_positiveScenario(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "branchDetails", "english", "positive");
        return rowOfData;
    }


    @DataProvider(name = "activityDetails_negative_scenario")
    public Object[][] activityDetails_negativeScenario(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "activityDetails", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "activityDetails_positive_scenario")
    public Object[][] activityDetails_positiveScenario(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "activityDetails", "english", "positive");
        return rowOfData;
    }


    @DataProvider(name = "addressDetails_negative_scenario")
    public Object[][] addressDetails_negativeScenario(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "addressDetails", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "addressDetails_positive_scenario")
    public Object[][] addressDetails_positiveScenario(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "addressDetails", "english", "positive");
        return rowOfData;
    }


    @DataProvider(name = "contactPerson_negative_scenario")
    public Object[][] contactPerson_negativeScenario(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "contactPerson", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "contactPerson_positive_scenario")
    public Object[][] contactPerson_positiveScenario(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "contactPerson", "english", "positive");
        return rowOfData;
    }


    @DataProvider(name = "financialDetails_negative_scenario")
    public Object[][] negative_financialDetails(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "financialDetails", "english", "negative");
        return rowOfData;
    }


    @DataProvider(name = "financialDetails_positive_scenario")
    public Object[][] positive_financialDetails(){
        Object[][] rowOfData = ExcelParser.passToDataProvider(file.getAbsolutePath(), "financialDetails", "english", "positive");
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
    public void verifyLabels(Map<String, String> labels) {
        SoftAssert softAssertion = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String A = organizationInformation.getFields_Labels(entry.getKey());
            softAssertion.assertEquals(A, entry.getValue());
        }
        softAssertion.assertAll();
    }


    @Test(enabled = true, dataProvider = "OrganizationInformationPage_negative", groups = {"regression"}, priority = 1)
    public void OrganizationInformationPage_negativeScenario(Map<String, String> rowOfData) {
        SoftAssert softAssertion = new SoftAssert();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        organizationInformation.fillForm(rowOfData);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        organizationInformation.clickOnContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssertion.assertTrue(organizationInformation.checkOrganizationInformationPage(), "Verify page has not changed");
        if (!organizationInformation.checkOrganizationInformationPage()) {
            organizationInformation.clickOnBackButton();
        }
        if (rowOfData.get("page_error") != null) {
            softAssertion.assertEquals(organizationInformation.getPage_ErrorMessage(), rowOfData.get("page_error"));
        }
        if (rowOfData.get("error_message") != null) {
            softAssertion.assertEquals(organizationInformation.getFields_ErrorMessages(rowOfData), rowOfData.get("error_message"));
        }
        softAssertion.assertAll();

        driver.navigate().refresh();
    }


    @Test(enabled = true, groups = {"regression"})
    public void renewIcon() {
        String A = organizationInformation.getCaptchaText();
        organizationInformation.renewCaptch();

        Assert.assertNotEquals(A, organizationInformation.getCaptchaText());
    }


    @Test(enabled = true, dataProvider = "OrganizationInformationPage_positive_scenario", groups = {"regression", "smoke"}, priority = 2)
    public void OrganizationInformationPage_positiveScenario(Map<String, String> rowOfData) {
        SoftAssert softAssertion = new SoftAssert();
        // fill Form
        organizationInformation.fillForm(rowOfData);
        // click Continue
        organizationInformation.clickOnContinueButton();
        // wait for next page navigation (if any)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verificationCode = new VerificationCode(driver);
        // verify page has changed
        softAssertion.assertTrue(verificationCode.checkVerificationCodePage(), "Verify page has changed");

        softAssertion.assertAll();
    }



    @Test(enabled = false, dataProvider = "VerificationCodePage_negative_scenario", groups = {"regression"}, priority = 3)
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


    @Test(enabled = true, dataProvider = "VerificationCodePage_positive_scenario", groups={"regression", "smoke"}, priority = 4)
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
        Assert.assertTrue(verificationCode.checkCompleteInfoPageIsDisplayed());
        addNewBranch = new AddNewBranch(driver);
        addNewBranch.clickAddNewBranch();
    }

    @Test(enabled = true, dataProvider = "branchDetails_negative_scenario", groups = {"regression"}, priority = 5)
    public void branchDetailsNegativeTest(Map<String, String> data){
        addNewBranch.fillBranchDetails(data);
        addNewBranch.clickActivityDetailsTab();
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewBranch.getBranchDetailsErrorMessages(data), data.get("error_message"));
        }
        if(addNewBranch.checkActivityPageIsDisplayed()) addNewBranch.clickBranchDetailsTab();
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "branchDetails_positive_scenario" , groups={"regression", "smoke"}, priority = 6)
    public void branchDetailsPositiveTest(Map<String, String> data){
        addNewBranch.fillBranchDetails(data);
        addNewBranch.clickActivityDetailsTab();
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertTrue(addNewBranch.checkActivityPageIsDisplayed(), "Verify Page has changed");
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "activityDetails_negative_scenario", groups = {"regression"}, priority = 7)
    public void activityDetailsNegativeTest(Map<String, String> data){
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.fillActivityDetails(data);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!addNewBranch.checkActivityDetailsButtonsClicked()) {
            addNewBranch.clickSaveButton();
            if(data.get("popup_errors") != null) {
                softAssert.assertEquals(addNewBranch.getActivityDetailsPopupErrors(data.get("popup_errors")), data.get("popup_errors"));
            }
            addNewBranch.clickClosePopupMessage();
        }
        addNewBranch.clickAddressDetailsTab();
        if(data.get("error_message") != null) {
            softAssert.assertEquals(addNewBranch.getActivityDetailsErrorMessages(data), data.get("error_message"));
        }
        negativeActivityDetailsRun = true;
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "activityDetails_positive_scenario", groups={"regression", "smoke"}, priority = 8)
    public void activityDetailsPositiveTest(Map<String, String> data){
        if(addNewBranch.checkAddressDetailsPageIsDisplayed()) addNewBranch.clickActivityDetailsTab();

        if(negativeActivityDetailsRun){
            addNewBranch.resetFlags(data);
        }
        addNewBranch.fillActivityDetails(data);

        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickAddressDetailsTab();
        if(addNewBranch.addressCardVisible()){
            addNewBranch.clickAddressCard();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        softAssert.assertTrue(addNewBranch.checkAddressDetailsPageIsDisplayed(), "Verify Page has changed");
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "addressDetails_negative_scenario", groups = {"regression"}, priority = 9)
    public void addressDetailsNegativeTest(Map<String, String> data){
        addNewBranch.fillAddressDetails(data);
        addNewBranch.clickContactPersonTab();
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertFalse(addNewBranch.checkContactPageIsDisplayed(),"Verify page hasn't changed");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(addNewBranch.checkContactPageIsDisplayed()) addNewBranch.clickAddressDetailsTab();
        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewBranch.getAddressDetailsErrorMessages(data), data.get("error_message"));
        }
        if(data.get("page_error") != null){
            addNewBranch.clickSaveButton();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            softAssert.assertEquals(addNewBranch.getAddressDetailsPageErrorMessage(), data.get("page_error"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(addNewBranch.checkContactPageIsDisplayed()) addNewBranch.clickAddressDetailsTab();
        }
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "addressDetails_positive_scenario", groups={"regression", "smoke"}, priority = 10)
    public void addressDetailsPositiveTest(Map<String, String> data){
        addNewBranch.fillAddressDetails(data);
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickContactPersonTab();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        softAssert.assertTrue(addNewBranch.checkContactPageIsDisplayed(), "Verify Page has changed");
        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "contactPerson_negative_scenario", groups = {"regression"}, priority = 11)
    public void contactPersonNegativeTest(Map<String, String> data){
        addNewBranch.fillContactPerson(data);
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.clickSaveButton();

        if(data.get("error_message") != null){
            softAssert.assertEquals(addNewBranch.getContactPersonErrorMessages(data), data.get("error_message"));
        }
        if(data.get("page_error") != null){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            softAssert.assertEquals(addNewBranch.getContactPersonPageErrorMessage(), data.get("page_error"));
        }

        if(addNewBranch.contactPageIsClosed()){
            softAssert.assertTrue(addNewBranch.checkContactPageIsDisplayed(),"Verify page hasn't changed");
        }
        if(!addNewBranch.contactPageIsClosed()){ //if a test case closes add branch, reopen it and navigate back to contact person
            addNewBranch.clickEditAddNewBranch();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNewBranch.clickActivityDetailsTab();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNewBranch.clickAddressDetailsTab();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNewBranch.clickContactPersonTab();
        }
        softAssert.assertAll();
    }
    @Test(enabled = true, dataProvider = "contactPerson_positive_scenario", groups={"regression", "smoke"}, priority = 12)
    public void contactPersonPositiveTest(Map<String, String> data){
        SoftAssert softAssert = new SoftAssert();
        addNewBranch.fillContactPerson(data);
        addNewBranch.clickSaveButton();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addNewBranch.clickContinueButton();
        financialDetails = new FinancialDetails(driver);
        softAssert.assertTrue(financialDetails.checkFinancialPageIsDisplayed(), "Verify Page has Changed");

        softAssert.assertAll();
    }

    @Test(enabled = true, dataProvider = "financialDetails_negative_scenario", groups={"regression"}, priority = 13)
    public void financialDetailsNegativeTest(Map<String, String> data) {
        financialDetails.fillFinancialData(data);
        financialDetails.clickOnContinueButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        if (data.get("error_messages") != null) {
            softAssertion.assertEquals(financialDetails.fieldValidateErrorMessage(data), data.get("error_messages"));
        }
        softAssertion.assertTrue(financialDetails.checkFinancialPageIsDisplayed(), "Verify page hasn't changed");
        softAssertion.assertAll();
    }


    @Test(enabled = true, dataProvider = "financialDetails_positive_scenario", groups={"regression", "smoke"}, priority = 14)
    public void financialDetails_positiveScenario(Map<String, String> rowOfData){
        financialDetails.fillFinancialData(rowOfData);
        financialDetails.clickOnContinueButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssert = new SoftAssert();
        createNewPassword = new CreateNewPassword(driver);

        softAssert.assertTrue(createNewPassword.checkPasswordPageIsDisplayed(), "Verify page has changed");

        softAssert.assertAll();

    }


    @Test(enabled = true, dataProvider = "password_labels", groups = {"regression"}, priority = 15)
    public void verifyLabels2(Map<String, String> labels) {
        SoftAssert softAssertion = new SoftAssert();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String A = createNewPassword.fieldsLabel(entry.getKey());
            softAssertion.assertEquals(A, entry.getValue());
        }
        softAssertion.assertAll();
    }


    @Test (enabled = true, dataProvider = "password_negative_scenario", groups = {"regression"}, priority = 16)
    public void password_negativeScenario(Map<String, String> rowOfData) {
        createNewPassword.fillData(rowOfData);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SoftAssert softAssertion = new SoftAssert();
        if (rowOfData.get("error_messages") != null) {
            softAssertion.assertEquals(createNewPassword.fieldValidateErrorMessage(rowOfData), rowOfData.get("error_messages"));
        }
        softAssertion.assertTrue(createNewPassword.checkPasswordPageIsDisplayed(), "Verify page hasn't changed");
        softAssertion.assertAll();
    }


    @Test(enabled = true, dataProvider = "password_positive_scenario", groups={"regression", "smoke"}, priority = 17)
    public void password_positiveScenario(Map<String, String> rowOfData){
        createNewPassword.fillData(rowOfData);
        createNewPassword.checkAgreeCheckBox();
        createNewPassword.openTermsAndConditionPopup();
        createNewPassword.confirmButton();
        createNewPassword.closeSurvey();
    }


    @Test(enabled = true, groups={"regression", "smoke"}, priority = 18)
    public void acknowledgmentTest(){
        acknowledgement = new Acknowledgement(driver);
        acknowledgement.getApplicationNumber();
        acknowledgement.getTpName();
        acknowledgement.getSystemDateAndCompare();
    }

}

